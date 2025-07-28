package com.nagen.aws.mapper;

import com.nagen.aws.entity.ProductEntity;
import com.nagen.aws.model.ProductRequest;
import com.nagen.aws.model.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toEntity(ProductRequest request);
    ProductResponse toResponse(ProductEntity entity);
}
