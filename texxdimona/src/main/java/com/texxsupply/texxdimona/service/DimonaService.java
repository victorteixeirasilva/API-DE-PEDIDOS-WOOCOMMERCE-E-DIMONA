package com.texxsupply.texxdimona.service;
import com.texxsupply.texxdimona.model.dimona.OrderDimona;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
/**
 * @Description: Cliente HTTP, criado via <b>OpenFeign</b>, para o consumo da API do <b>Dimona</b>.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://api.camisadimona.com.br/">Dimona-API</a>
 *
 * @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@Service
@FeignClient(name = "Dimona", url = "https://camisadimona.com.br/api/v2/order")
public interface DimonaService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "api-key: api-key-Dimona"
    })
    @PostMapping
    public ResponseEntity insertOrder(@RequestBody OrderDimona orderDimona);
}
