package com.rashi.webfluxproject.service;

import com.rashi.webfluxproject.dao.ProduceDAO;
import com.rashi.webfluxproject.dto.ProductDTO;
import com.rashi.webfluxproject.util.DataConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProductServiceIMPL implements ProductService{

    @Autowired
    private ProduceDAO produceDAO;
    //---------------Save-------------------
    @Override
    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono){
        return productDTOMono.map(DataConvertor::DtoToEntity)
                .flatMap(produceDAO::insert)
                .map(DataConvertor::entityToDto);
    }
    //---------------Update-------------------
    @Override
    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDtoMono,String id){
        return produceDAO.findById(id)
                .flatMap(p -> productDtoMono.map(DataConvertor::DtoToEntity)
                .doOnNext(e -> e.setId(id)))
                .flatMap(produceDAO::save)
                .map(DataConvertor::entityToDto);

    }
    //---------------Delete-----------------------------
    @Override
    public Mono<Void> deleteProduct(String id){
        return produceDAO.deleteById(id);
    }

    //--------------Get All Products----------------
    @Override
    public Flux<ProductDTO> getProducts(){
        return produceDAO.findAll().map(DataConvertor::entityToDto);
    }

    //-----------Search product-----------------------
    @Override
    public Mono<ProductDTO> getProduct(String id){
        return produceDAO.findById(id).map(DataConvertor::entityToDto);
    }

    //--------------Filter price---------------------
    @Override
    public Flux<ProductDTO> getProductsRange(double min,double max){
        return produceDAO.findByPriceBetween(Range.closed(min,max));
    }

}
