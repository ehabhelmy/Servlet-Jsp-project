/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tnaneen.servletproject;

/**
 *
 * @author ehabm
 */
public class Product {
    private int id;
    private String name;
    private int price;
    private int available;
    private String category;
    private String description;
    private String image;

    public Product() {
          this.id = 9;
        this.name = "P";
        this.price = 100;
        this.available = 10;
        this.category = "cars";
        this.description = "blablabla";
        this.image = "path";
    }

    public Product(int id, String name, int price, int available, String category, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.category = category;
        this.description = description;
        this.image = image;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }  

    @Override
    public String toString() {
        return id+" >> "+name+" >> "+category+" >> "+price+" >> "+available+" >> "+description+" >> "+image;
    }
    
    
    
    
}