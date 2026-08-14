package com.example.product.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product.DTO.ProductRequestDTO;
import com.example.product.DTO.ProductResponseDTO;
import com.example.product.DTO.ResponseDTO;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import com.example.product.enums.Status;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;
import com.example.product.security.JwtService;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;
    @Autowired
    JwtService jwtService;
    @Autowired
    CategoryRepository categoryRepository;

     
    public void roleCheck(String token){
        List<String> roles= jwtService.extractRole(token.substring(7));
        if(!roles.contains("admin")){
            throw new RuntimeException("Only admins can add products");
        }
    }

   public ResponseDTO addProduct(ProductRequestDTO dto,String token){

    roleCheck(token);
    
    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7));

    Category category=categoryRepository.findById(dto.getCategory()).orElseThrow(()-> new RuntimeException("Category not found"));

    Product product=new Product();
    product.setProductName(dto.getProductName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setQuantity(dto.getQuantity());
    product.setCategory(category);
    product.setStatus(Status.AVAILABLE);
    product.setAdminId(referenceId);
    Product save=repo.save(product);
    return new ResponseDTO("Product added successfully",save.getId());

   }

   public Page<ProductResponseDTO> getAllProducts(String token,int page,int size){
    
    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7));         // no need to show added admin's name,so simple get

       Pageable pageable= PageRequest.of(page,size);
       Page<Product>list=repo.findAll(pageable);

    List<ProductResponseDTO>listDto= new ArrayList<>();

    for(Product i:list.getContent()){
        listDto.add(new ProductResponseDTO(i.getId(),i.getProductName(),i.getDescription(),i.getPrice(),i.getQuantity(),i.getCategory().getCategoryName(),i.getStatus(),i.getCreatedAt()));
    }

    return new PageImpl<>(listDto,pageable,list.getTotalElements());

   }


   public ProductResponseDTO getProductById(Long id,String token){
      String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7));    
      Product product=repo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
      return new ProductResponseDTO(product.getId(),product.getProductName(),product.getDescription(),product.getPrice(),product.getQuantity(),product.getCategory().getCategoryName(),product.getStatus(),product.getCreatedAt());
   }


   
   public ResponseDTO updateProduct(Long id,ProductRequestDTO dto,String token){
 
    roleCheck(token);

    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7));     

    Category category=categoryRepository.findById(dto.getCategory()).orElseThrow(()-> new RuntimeException("Category not found"));

    Product product=repo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    product.setProductName(dto.getProductName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setQuantity(dto.getQuantity());
    product.setCategory(category);
    product.setStatus(Status.AVAILABLE);
    product.setAdminId(referenceId);
    Product save=repo.save(product);
    return new ResponseDTO("Product updated successfully",save.getId());

   }


   public ResponseDTO deleteProduct(Long id,String token){

    roleCheck(token);

    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7));    
   Product product=repo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
   repo.delete(product);
   return new ResponseDTO("Product deleted successfully", product.getId());
}

public List <ProductResponseDTO> filterByproductName(String token,String productname){
    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7)); 

   List< Product>list=repo.findProductByName(productname);
   List<ProductResponseDTO>listDTO= new ArrayList<>();
   for(Product i:list){
       listDTO.add(new ProductResponseDTO(i.getId(),i.getProductName(),i.getDescription(),i.getPrice(),i.getQuantity(),i.getCategory().getCategoryName(),i.getStatus(),i.getCreatedAt()));
   }
   return listDTO;
   
   
      
}

public List <ProductResponseDTO> filterByCategory(String categoryName,String token){
    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7)); 

   List< Product>list=repo.findProductBYCategory(categoryName);
   List<ProductResponseDTO>listDTO= new ArrayList<>();
   for(Product i:list){
       listDTO.add(new ProductResponseDTO(i.getId(),i.getProductName(),i.getDescription(),i.getPrice(),i.getQuantity(),i.getCategory().getCategoryName(),i.getStatus(),i.getCreatedAt()));
   }
   return listDTO;
   
   
      
}



public List <ProductResponseDTO> filterByPriceRange(Long min,Long max,String token){
    String email=jwtService.extractEmail(token.substring(7));
    List<String> roles= jwtService.extractRole(token.substring(7));
    Long referenceId=jwtService.extractReferenceId(token.substring(7)); 

   List< Product>list=repo.findProductByPriceBetween(min,max);
   List<ProductResponseDTO>listDTO= new ArrayList<>();
   for(Product i:list){
       listDTO.add(new ProductResponseDTO(i.getId(),i.getProductName(),i.getDescription(),i.getPrice(),i.getQuantity(),i.getCategory().getCategoryName(),i.getStatus(),i.getCreatedAt()));
   }
   return listDTO;
   
   
      
}

public ProductResponseDTO  getInternalById(Long id){
    Product product=repo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
      return new ProductResponseDTO(product.getId(),product.getProductName(),product.getDescription(),product.getPrice(),product.getQuantity(),product.getCategory().getCategoryName(),product.getStatus(),product.getCreatedAt());
}


    

}
