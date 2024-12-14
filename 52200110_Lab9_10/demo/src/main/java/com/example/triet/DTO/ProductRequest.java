package com.example.triet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long id;
    private String productName;
    private double price;
    private String description;
    private byte[] image;
}
