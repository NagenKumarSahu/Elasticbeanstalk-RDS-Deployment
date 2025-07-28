package com.nagen.aws.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private long id;
    private String prodName;
    private String description;
    private double price;
    private int quantity;
    private boolean active;
}
