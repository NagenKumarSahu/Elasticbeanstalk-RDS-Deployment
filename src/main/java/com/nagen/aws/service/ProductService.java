package com.nagen.aws.service;

import com.nagen.aws.entity.ProductEntity;
import com.nagen.aws.mapper.ProductMapper;
import com.nagen.aws.model.ProductRequest;
import com.nagen.aws.model.ProductResponse;
import com.nagen.aws.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /*@Autowired
    private ProductMapper productMapper;*/

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                //.map(productMapper::toResponse)
                .map(this::entityToResponse)
                .toList();
    }

    public ProductResponse getProductDetails(long productId) {
        return productRepository.findById(productId)
                //.map(productMapper::toResponse)
                .map(this::entityToResponse)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        //ProductEntity productEntity = productMapper.toEntity(productRequest);
        ProductEntity productEntity = requestToEntity(productRequest);
        productEntity.setActive(true);
        ProductEntity savedProduct = productRepository.save(productEntity);
        //return productMapper.toResponse(savedProduct);
        return entityToResponse(savedProduct);
    }

    public ProductResponse updateProduct(long productId, ProductRequest productRequest) {
        ProductEntity existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        //ProductEntity updatedProduct = productMapper.toEntity(productRequest);
        ProductEntity updatedProduct = requestToEntity(productRequest);
        updatedProduct.setId(existingProduct.getId());
        updatedProduct.setActive(existingProduct.isActive());

        updatedProduct = productRepository.save(updatedProduct);
        //return productMapper.toResponse(updatedProduct);
        return entityToResponse(updatedProduct);
    }

    public Void deleteProduct(long productId) {
        ProductEntity existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        existingProduct.setActive(false);
        productRepository.save(existingProduct);

        return null;
    }

    private ProductResponse entityToResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .prodName(productEntity.getProdName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .active(productEntity.isActive())
                .build();
    }

    private ProductEntity requestToEntity(ProductRequest productRequest) {
        return ProductEntity.builder()
                .prodName(productRequest.getProdName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
    }
}
