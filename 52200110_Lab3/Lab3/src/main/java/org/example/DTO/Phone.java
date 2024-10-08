package org.example.DTO;

import javax.persistence.*;
import lombok.Data;
import lombok.Generated;


@Data
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long manuId;

    @Column
    private String name;

    @Column
    private int price;


    @Column
    private String color;

    @Column
    private String country;

    @Column
    private int quantity;



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManuId() {
        return manuId;
    }

    public void setManuId(Long manuId) {
        this.manuId = manuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Phone[id = " + getId() + ", name = " + getName() + ", price = " + getPrice() + ", color = " + getColor() + ", country = " + getCountry() + ", quantity = " + getQuantity() + "]";
    }
}
