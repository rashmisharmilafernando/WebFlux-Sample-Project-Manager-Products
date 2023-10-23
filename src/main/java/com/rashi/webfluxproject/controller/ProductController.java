package com.rashi.webfluxproject.controller;

import com.rashi.webfluxproject.dto.ProductDTO;
import com.rashi.webfluxproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDTO> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDTO> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max")double max){
        return productService.getProductsRange(min,max);
    }

    @PostMapping
    public Mono<ProductDTO> saveProduct(@RequestBody Mono<ProductDTO> productDtoMono){
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDTO> updateProduct(@RequestBody Mono<ProductDTO> productDtoMono,@PathVariable String id){
        return productService.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }

}
