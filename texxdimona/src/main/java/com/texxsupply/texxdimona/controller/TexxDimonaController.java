package com.texxsupply.texxdimona.controller;
import com.texxsupply.texxdimona.model.ResponseModel;
import com.texxsupply.texxdimona.service.TexxDimonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description: Controlador responsável por iniciar a ação de integração  entre Woocommerce e Dimona.
 *
 *  @author @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@RestController
public class TexxDimonaController {

    /**
     * @Description: Atributo responsável por gerar uma instancia singleton, gerenciada pelo Spring, referente a minha classe de Serviço, para ser usada dentro dos end-points.
     * */
    @Autowired
    private TexxDimonaService texxDimonaService;

    /**
    * @Description: Método responsável por criar o end-point que fará a integração dos pedidos do Woocommerce para o sistema da Dimona. Esse método se comunica com a camada de serviço.
    * */
    @PostMapping("/texxsupply/api/integration/ordersToDimona")
    public ResponseEntity getProductWordpress(){
        try {
            return texxDimonaService.insertOrder();
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(500,"Não foi possível integrar os Pedidos da Texx Supply com a Dimona!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
