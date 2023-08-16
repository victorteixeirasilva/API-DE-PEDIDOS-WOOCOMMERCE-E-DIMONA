package com.texxsupply.texxdimona.service;
import com.texxsupply.texxdimona.model.wordpress.OrderWorpress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
/**
 * @Description: Cliente HTTP, criado via <b>OpenFeign</b>, para o consumo da API do <b>Woocommerce</b>.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://woocommerce.com/document/woocommerce-rest-api/">Woocommerce-API</a>
 *
 * @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@Service
@FeignClient(name = "nome-do-seu-ecommerce", url = "url-do-end-point-Woocommerce")
public interface WordpressService {

    @GetMapping
    public List<OrderWorpress> getOrders();
}
