package com.example.loginpage.models;

public class Producto {
    private String id;
    private String price;
    private String url;


    public Producto(){};

    public Producto(String id,String price){
        this.id = id;
        this.price = price;
    }
    public Producto(String id,String price, String url){
        this.id = id;
        this.price = price;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}

