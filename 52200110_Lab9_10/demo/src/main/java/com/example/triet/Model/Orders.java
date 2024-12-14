package com.example.triet.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double total_price;

    @OneToMany
    private List<Product> product_list;
}
