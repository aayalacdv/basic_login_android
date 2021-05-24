package com.example.loginpage.models;

public class Producto {
    private String id;
    private float price;


    public Producto(){};

    public Producto(String id,float price){
        this.id = id;
        this.price = price;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", price=" + price +
               '\'' +
                '}';
    }
}
