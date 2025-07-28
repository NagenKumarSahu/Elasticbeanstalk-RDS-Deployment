package com.nagen.aws.controller;

import com.nagen.aws.model.ProductRequest;
import com.nagen.aws.model.ProductResponse;
import com.nagen.aws.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductDetails(@PathVariable long id) {
        return new ResponseEntity<>(productService.getProductDetails(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.updateProduct(id, productRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.NO_CONTENT);
    }
}
