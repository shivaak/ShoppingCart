package com.springlearning.productservice.service;

import com.springlearning.productservice.dto.ProductRequest;
import com.springlearning.productservice.model.Product;
import com.springlearning.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepository.insert(product);
        log.info("Product {} is saved", product.getId());
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
