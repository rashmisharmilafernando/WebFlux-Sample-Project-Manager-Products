package com.rashi.webfluxproject.service;

import com.rashi.webfluxproject.dao.ProduceDAO;
import com.rashi.webfluxproject.dto.ProductDTO;
import com.rashi.webfluxproject.util.DataConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
    //---------------Save----------------------------
    Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono);
    //---------------Update---------------------------
    Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDTOMono,String id);
    //---------------Delete---------------------------
    Mono<Void> deleteProduct(String id);
    //--------------Get All Products------------------
     Flux<ProductDTO> getProducts();
    //-----------Search product-----------------------
     Mono<ProductDTO> getProduct(String id);
    //--------------Filter price---------------------
     Flux<ProductDTO> getProductsRange(double min,double max);

}
