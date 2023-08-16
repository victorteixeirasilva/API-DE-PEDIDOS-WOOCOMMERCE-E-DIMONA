package com.texxsupply.texxdimona.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
/**
 * @Description: Classe responsável por gerar um modelo de Resposta para as Requisições HTTP.
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
public class ResponseModel {

    private Integer statusCode;
    private String mensage;
}
