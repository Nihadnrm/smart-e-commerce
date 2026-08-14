package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.DTO.ProductRequestDTO;
import com.example.product.DTO.ProductResponseDTO;
import com.example.product.DTO.ResponseDTO;
import com.example.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {

@Autowired
ProductService service;

@PostMapping("/product")
public ResponseDTO addProduct(@Valid@RequestBody ProductRequestDTO dto,@RequestHeader("Authorization")String token){
    return service.addProduct(dto, token);
}
@GetMapping("/product")
public Page<ProductResponseDTO> getAllProducts(@RequestHeader("Authorization")String token, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "3")int size){
    return service.getAllProducts(token,page,size);

}

@GetMapping("/product/{id}")
public ProductResponseDTO getProductById(@PathVariable Long id,@RequestHeader("Authorization")String token){
    return service.getProductById(id, token);
}

@PutMapping("/product/{id}")
public ResponseDTO updateProduct(@Valid@PathVariable Long id,@RequestBody ProductRequestDTO dto,@RequestHeader("Authorization")String token){
    return service.updateProduct(id, dto, token);

}

@DeleteMapping("/product/{id}")
public ResponseDTO deleteProduct(@PathVariable Long id,@RequestHeader("Authorization")String token){
    return service.deleteProduct(id, token);
}

@GetMapping("/product/search")
public List<ProductResponseDTO>filterByproductName(@RequestHeader("Authorization")String token,@RequestParam String productName){
   return service.filterByproductName(token, productName);
}

@GetMapping("/product/filter/category")
public List<ProductResponseDTO>filterByCategory(@RequestParam String categoryName ,@RequestHeader("Authorization")String token){
     return service.filterByCategory(categoryName,token);
}
@GetMapping("/product/filter/priceRange")
public List<ProductResponseDTO>filterByPriceRange(@RequestParam Long min,@RequestParam Long max,@RequestHeader("Authorization")String token){
   return service.filterByPriceRange(min,max, token);
}
@GetMapping("/product/internal/{id}")
public  ProductResponseDTO getInternalById(@PathVariable Long id){
    return service.getInternalById(id);

}

}