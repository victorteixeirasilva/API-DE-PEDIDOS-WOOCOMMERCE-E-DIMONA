package com.texxsupply.texxdimona.model.dimona;

import com.texxsupply.texxdimona.model.wordpress.LineItem;
import com.texxsupply.texxdimona.model.wordpress.OrderWorpress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description: Classe responsável por ser o objeto de montagem do JSON, com seu construtor para converter o pedido do JSON Wooccommerce, em Pedido Dimona.
 *
 * @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderDimona {

    private String shipping_speed;
    private String order_id;
    private String customer_name;
    private String customer_document;
    private String customer_email;
    private List<Item> items;
    private Address address;

    /**
     * @Description: Método construtor que recebe um pedido Woocommerce e converte para pedido Dimona.
     * */
    public OrderDimona(OrderWorpress orderWorpress){
        this.shipping_speed = "pac";
        this.order_id = "#"+orderWorpress.getId();
        this.customer_name = orderWorpress.getShipping().getFirst_name()
                .concat(" ")
                .concat(orderWorpress.getShipping().getLast_name());

        if(orderWorpress.getBilling().getCnpj()==""){
            this.customer_document = orderWorpress.getBilling().getCpf();
        } else if (orderWorpress.getBilling().getCpf()==""){
            this.customer_document = orderWorpress.getBilling().getCnpj();
        } else {
            this.customer_document = "";
        }

        this.customer_email = orderWorpress.getBilling().getEmail();

        Address address1 = new Address(orderWorpress);
        this.address = address1;

        List<LineItem> lineItems = orderWorpress.getLine_items();
        int count = 0;
        while (count<orderWorpress.getLine_items().size()){
            LineItem lineItem = lineItems.get(count);
            Item item = new Item(lineItem);

            if(item.getName()=="Produto Não Dimona"){
                List<Item> productNotDimona = new ArrayList<>();
                this.items = productNotDimona;
                count++;
                lineItems.iterator().next();
            } else {
                List<Item> listItem = new ArrayList<>();
                listItem.add(item);

                this.items = listItem;

                count++;
                lineItems.iterator().next();
            }
        }

    }

}