package org.example.DTO;

public class Product {
    private int id;
    private String name;
    private int price;
    private String color;

    public Product(int id, String name, int price,String color) {
        setId(id);
        setName(name);
        setPrice(price);
        setColor(color);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product{id=" + getId() + ",name='" + getName() +"',price=" + getPrice() + ", color='" + getColor() + "'}";
    }
}
