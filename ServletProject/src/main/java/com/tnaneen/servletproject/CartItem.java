/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tnaneen.servletproject;

/**
 *
 * @author mohamed
 */
public class CartItem {
    
    private int userId, productId, quantity, bought;

    public CartItem() {
    }

    public CartItem(int userId, int productId, int quantity, int bought) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.bought = bought;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }
    
    
    
}
