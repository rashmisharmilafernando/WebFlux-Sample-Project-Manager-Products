package com.rashi.webfluxproject.dao;


import com.rashi.webfluxproject.dto.ProductDTO;
import com.rashi.webfluxproject.entity.ProductEntity;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProduceDAO extends ReactiveMongoRepository<ProductEntity,String> {
    Flux<ProductDTO> findByPriceBetween(Range<Double> PriceRange);
}
