package com.springlearning.productservice.controller;

import com.springlearning.productservice.dto.ProductRequest;
import com.springlearning.productservice.model.Product;
import com.springlearning.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    public void getProducts(){

    }

    public void getProductByName(String name){

    }

    public void updateProduct(Product product){

    }

    public void deleteProduct(String id){

    }

}
