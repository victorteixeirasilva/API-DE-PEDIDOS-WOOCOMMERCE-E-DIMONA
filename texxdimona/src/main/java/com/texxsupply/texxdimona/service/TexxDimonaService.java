package com.texxsupply.texxdimona.service;
import com.texxsupply.texxdimona.model.ResponseModel;
import com.texxsupply.texxdimona.model.dimona.OrderDimona;
import com.texxsupply.texxdimona.model.wordpress.LineItem;
import com.texxsupply.texxdimona.model.wordpress.OrderWorpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: Classe responsável por tratar todas as regras de negócio e pedir para que o <b>OpenFeign</b>, consuma as API quando se fizer necessário. No exemplo do negócio Texx Supply, pela complexidade de ter múltiplos fornecedores, essa classe precisou tratar regras como: 1 - Caso o pedido tenha um item com SKU, direcionado para Dimona, se sim o mesmo pode ser exportado para plataforma da Dimona. 2 - Caso o pedido não tenha nenhum item com esse SkU para Dimona, ignorar o pedido e não fazer a exportação. 3 - Exportar para a plataforma da Dimona apenas os pedidos com o status processando, que no caso, são os pedidos pagos. 4 - Caso o pedido tenha, um item da Dimona e um Item de outro fornecedor, dentro da API, tratar o pedido para que o mesmo se torne um pedido apenas da Dimona, seguindo todas as regras anteriores.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://api.camisadimona.com.br/">Dimona-API</a>
 * @see <a href="https://www.texxsupply.com>Texx Supply</a>
 *
 * @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@Service
public class TexxDimonaService {

    @Autowired
    private WordpressService wordpressService;

    @Autowired
    private DimonaService dimonaService;

    private String API_KEY = "43868c550c8530bd955160b5cf97cadb";
    private String ACCEPT = "application/json";
    private String CONTENT_TYPE = "application/json";

    /**
     *
     * @Description: Método responsável por receber os perdidos, e enquadra-los nas regras de negócio e exportar os mesmos para Dimona.
     *
     * */
    public ResponseEntity insertOrder(){

         //Criando lista de pedidos para receber os 10 da API da Woocommerce.
        List<OrderWorpress> orders = wordpressService.getOrders();

        //Ciando lista de pedidos para receber apenas os pedidos que se enquadraram para ser exportados
        List<OrderWorpress> ordersToDimona = new ArrayList<>();

        for (OrderWorpress order: orders) {
            //Dentro do pedido criando uma lista de linha de itens para receber e manipular a mesma
            List<LineItem> lineItems = order.getLine_items();

            //Testado se a lista criada anteriormente não está vazia, e se o pedido está com status processando.
            if (!lineItems.isEmpty()&&order.getStatus().equals("processing")&&order.getCreated_via().equals("checkout")){
                for (LineItem lineItem: lineItems) {
                    //Dentro da lista de itens, vamos verificar se o mesmo tem algum produto
                    // que não se encaixa na regra de sku Dimona, cujo qual fefinimos.
                    if (lineItem.getSku()==""||lineItem.getSku()==null){
                        lineItems.remove(lineItem);
                        // Testando se após remover os itens que não se encaixam, se o pedido não ficou vazio.
                    }  else if (!lineItems.isEmpty()){
                        ordersToDimona.add(order);
                    }
                }
            }
        }

        //Testando se a Lista de pedidos qualificados para exportação, não está vazia.
        if (!ordersToDimona.isEmpty()){
            for (int i = 0; i < ordersToDimona.size(); i++) {
                OrderWorpress orderWorpressAux = ordersToDimona.get(i);
                if (orderWorpressAux.getStatus().equals("processing")){
                    //Pedindo para fazer a conversão do objeto base para o JSON do Wooccomerce, se converter
                    //no objeto base para o JSON Dimona.
                   OrderDimona orderDimona = new OrderDimona(orderWorpressAux);
                   if(!orderDimona.getItems().isEmpty()){
                       try {
                           //Fazando um POST do pedido na API-DIMONA
                           dimonaService.insertOrder(orderDimona);
                       } catch (Exception e){
                           ResponseModel responseModel = new ResponseModel(500, "Pedidos não foram capturados pelo Dimona!");
                           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                       }
                   }
               }
            }
                try {
                    //Retornando, todos os pedidos que foram exportados!
                    return ResponseEntity.ok(ordersToDimona);
                } catch (Exception e) {
                    ResponseModel responseModel = new ResponseModel(500, "Pedidos já integrados a Dimona!");
                    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi encontrado nenhum pedido da Texx que se enquadrase, nos pedidos Dimona para ser integrado!");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

}
