package com.nagen.aws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String prodName;
    private String description;
    private double price;
    private int quantity;
}
