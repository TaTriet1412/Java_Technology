package com.example.triet.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Lob
    private byte[] image;
}
