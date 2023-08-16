package com.texxsupply.texxdimona.service;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Description: Classe do <b>OpenFeign</b>, para passar configurções gerais das APIs consumidas por ele, nesse cenário ela foi usada para passar a API-KEY, necessária para fazer um post para inserção de um novo pedido.
 *
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 *
 * @author <a href="https://www.linkedin.com/in/victor-teixeira-354a131a3/">Victor Teixeira Silva</a>
 *
 * @version 1.0
 *
 * */
@Configuration
public class FeignConfig {

    private final String apiKey = "api-key-Dimona";

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("API-Key", apiKey);
            }
        };
    }
}
