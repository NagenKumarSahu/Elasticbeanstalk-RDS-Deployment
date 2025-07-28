package com.nagen.aws.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String prodName;
    private String description;
    private double price;
    private int quantity;
    private boolean active;
}
