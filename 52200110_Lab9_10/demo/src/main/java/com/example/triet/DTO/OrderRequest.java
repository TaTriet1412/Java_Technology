package com.example.triet.DTO;

import com.example.triet.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long id;
    private double total_price;
    private List<Product> product_list;
}
