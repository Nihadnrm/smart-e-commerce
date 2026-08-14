package com.example.order.webclient;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order.DTO.ProductResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductClient {

    @Autowired
    WebClient webClient;

    @CircuitBreaker(name ="productService",fallbackMethod = "fallbackproduct")
    public ProductResponseDTO getProduct(Long id){
        return webClient.get().uri("http://localhost:8081/product/internal/{id}",id).retrieve().bodyToMono(ProductResponseDTO.class).block();
     
    }
    public ProductResponseDTO fallbackproduct(Long id ,Exception ex){
       return new ProductResponseDTO(0L,"product not available","product not available",BigDecimal.ZERO,0,"product not available");
    }

}
