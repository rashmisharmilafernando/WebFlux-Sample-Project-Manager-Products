package com.rashi.webfluxproject.util;

import com.rashi.webfluxproject.dto.ProductDTO;
import com.rashi.webfluxproject.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

public class DataConvertor {

    public static ProductDTO entityToDto(ProductEntity productEntity){
        ProductDTO productDTO=new ProductDTO();
        BeanUtils.copyProperties(productEntity,productDTO);
        return productDTO;
    }

    public static ProductEntity DtoToEntity(ProductDTO productDTO){
        ProductEntity productEntity=new ProductEntity();
        BeanUtils.copyProperties(productDTO,productEntity);
        return productEntity;
    }

}
