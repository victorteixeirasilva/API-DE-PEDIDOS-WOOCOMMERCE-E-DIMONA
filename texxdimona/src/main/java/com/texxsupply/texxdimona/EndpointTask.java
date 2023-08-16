package com.texxsupply.texxdimona;

import com.texxsupply.texxdimona.controller.TexxDimonaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class EndpointTask{

    @Autowired
    private TexxDimonaController texxDimonaController;

    @Scheduled(fixedRate = 43200000)
    public void runTask() {
        texxDimonaController.getProductWordpress();
        System.out.println("-----------> Integração de Pedidos Feita! <-----------");
    }
}
