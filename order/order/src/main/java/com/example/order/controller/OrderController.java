package com.example.order.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.order.DTO.TopSoldProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.order.DTO.DetailOrderResponse;
import com.example.order.DTO.OrderRequestDTO;
import com.example.order.DTO.ResponseDTO;
import com.example.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    OrderService service;


    @PostMapping("/order")
    public ResponseDTO placeOrder(@Valid@RequestBody OrderRequestDTO dto,@RequestHeader("Authorization")String token){
               return service.placeOrder(dto, token);
    }

    @GetMapping("/order")
    public List<ResponseDTO>getAllOrders(@RequestHeader("Authorization")String token){
        return service.getAllOrders(token);

    }

    @GetMapping("/order/{id}")
    public DetailOrderResponse getOrderDetails(@PathVariable Long id,@RequestHeader("Authorization")String token){
        return service.getOrderDetails(token,id);

    } 
    @PutMapping("/order/{id}")
    public ResponseDTO updateOrder(@Valid@PathVariable Long id,@RequestBody OrderRequestDTO dto,@RequestHeader("Authorization")String token){
        return service.updateOrder(token, dto, id);
    }

    @PutMapping("order/cancel/{id}")
    public ResponseDTO cancelOrder(@PathVariable Long id,@RequestHeader("Authorization")String token){
        return service.cancelOrder(token, id);
    }

    @GetMapping("/order/filter")
    public List<ResponseDTO>filterByAmount(@RequestHeader("Authorization")String token, @RequestParam BigDecimal amount){
        return  service.filterByAmount(token,amount);
    }

    @GetMapping("/order/topsold")
    public List<TopSoldProductResponseDTO> getTopSoldProduct(@RequestHeader("Authorization")String token){
        return  service.getTopSoldProduct(token);
    }
    @GetMapping("/order/groupbycategory")
    public Map<String,Long>groupByCategory(@RequestHeader("Authorization")String token){
        return service.groupByCategory(token);
    }
    @GetMapping("/order/revenue")
    public BigDecimal totalRevenue(@RequestHeader("Authorization")String token){
        return service.totalRevenue(token);
    }

}
