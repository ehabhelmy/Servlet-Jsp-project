/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tnaneen.servletproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ehabm
 */
public class DataBaseHandler {

    private final String URL = "jdbc:mysql://localhost/ecommerce";
    private final String userName = "ehab";
    private final String password = "ehab";
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    private void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, userName, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            pst.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    ///////=1=/////// Ramadan :: PRODUCTS Processing functionalities ////////////////////////
    
    
    ///// ---- insert new product 
    public boolean insertNewProduct( Product product ) {
       
            try {
                openConnection();
       
                pst = conn.prepareStatement("INSERT INTO ecommerce.product (name, price, available, category,description) VALUES (?,?,?,?,?)");
                pst.setString(1, product.getName());
                pst.setInt(2, product.getPrice());
                pst.setInt(3, product.getAvailable());
                pst.setString(4, product.getCategory());
                pst.setString(5, product.getDescription());
             
                int queryResult = pst.executeUpdate();
                
                if (queryResult != 0)
                {
                    System.out.println("New Product Added Successfully");
                    return true;
                }
              
            } catch (SQLException ex) {
                
                System.err.println("ERROR:: DatabaseHandler.insertNewProduct()" + ex.toString());
                
            } finally {
                closeConnection();
            }
            
            System.out.println("New Product is NOT added!");
            return false;
              
    }


     ///// ---- remove a product 
    public boolean removeProduct( Product product ) {
       
            try {
                openConnection();
       
                pst = conn.prepareStatement("DELETE from ecommerce.product WHERE id = ?");
                pst.setInt(1, product.getId());
                
                int queryResult = pst.executeUpdate();
                
                if (queryResult != 0)
                {
                    System.out.println(" Product deleted  Successfully");
                    return true;
                }
              
            } catch (SQLException ex) {
                
                System.err.println("ERROR:: DatabaseHandler.deleteProduct()" + ex.toString());
                
            } finally {
                closeConnection();
            }
            
            System.out.println("New Product is NOT deleted!");
            return false;
              
    }

    
     ///// ---- update a product 
    public boolean updateProduct( Product product ) {
       
            try {
                openConnection();
       
                pst = conn.prepareStatement("UPDATE ecommerce.product SET name=? , price=? , available=? , category=? , description=? WHERE id = ?");
                pst.setString(1, product.getName());
                pst.setInt(2, product.getPrice());
                pst.setInt(3, product.getAvailable());
                pst.setString(4, product.getCategory());
                pst.setString(5, product.getDescription());
                pst.setInt(6, product.getId());
                
                int queryResult = pst.executeUpdate();
                
                if (queryResult != 0)
                {
                    System.out.println(" Product Updated  Successfully");
                    return true;
                }
              
            } catch (SQLException ex) {
                
                System.err.println("ERROR:: DatabaseHandler.updateProduct()" + ex.toString());
                
            } finally {
                closeConnection();
            }
            
            System.out.println("New Product is NOT updated!");
            return false;
              
    }
 
    
    
    ///// ---- get a certain product 
    public Product getProduct( int id ) {
       
            Product product = null;
        
            try {
                openConnection();
       
                pst = conn.prepareStatement("SELECT * FROM ecommerce.product WHERE id = ?");
                pst.setInt(1, id);
                
                rs = pst.executeQuery();
                
                while (rs.next()) 
                {                    
                     product = new Product(id, rs.getString("name"),rs.getInt("price") , rs.getInt("available"), rs.getString("category"), rs.getString("description"), "C:23456");
                }
              
            } catch (SQLException ex) {
                
                System.err.println("ERROR:: DatabaseHandler.getProduct()" + ex.toString());
                ex.printStackTrace();
                
            } finally {
                closeConnection();
            }
            
           
            return product;
              
    }
 
    
     ////////// Get All products
    public ArrayList<Product> getAllProducts (String category)
    {
        ArrayList<Product> products = new ArrayList();
        int sum =0;
        try {
            openConnection();
            
            pst = conn.prepareStatement("select * from ecommerce.product WHERE category = ?");
            pst.setString(1,category);
             
            rs = pst.executeQuery();
           
            while(rs.next())
             {
                 sum++;
                 //////////////// check which column contains my Email to get friends data
                  Product product = new Product(rs.getInt("id"), rs.getString("name"),rs.getInt("price") , rs.getInt("available"), rs.getString("category"), rs.getString("description"), "C:23456");
                  products.add(product);
               
             }
             System.out.println("sum: "+sum);
        } catch (SQLException ex) {
            
            System.out.println("ERROR :: DatabaseHandler.getAllproducts()" + ex.toString());
            
        }
          
        return products;
    }
    
    
    
    /////////////////// end of PRODUCTS ///////////////////////////
    
  ///////=2=/////// Ramadan :: SHOPPING CARTS Processing functionalities ////////////////////////
    
    
    ///////// NOTE ::: I have added a new bean called CartItem 
    ///////////////////// to Simplify sending params to/from functions
    
       
    ///// ---- insert new cart item 
    public boolean insertNewCartItem( CartItem cartItem ) {
       
            try {
                openConnection();
       
                pst = conn.prepareStatement("INSERT INTO ecommerce.shoppingcart  VALUES (?, ?, ?, ?)");
                pst.setInt(1, cartItem.getUserId());
                pst.setInt(2, cartItem.getProductId());
                pst.setInt(3, cartItem.getQuantity());
                pst.setInt(4, cartItem.getBought());
               
             
                int queryResult = pst.executeUpdate();
                
                if (queryResult != 0)
                {
                    System.out.println("New cartItem Added Successfully");
                    return true;
                }
              
            } catch (SQLException ex) {
                
                System.err.println("ERROR:: DatabaseHandler.insertNewCartItem()" + ex.toString());
                
            } finally {
                closeConnection();
            }
            
            System.out.println("cartItem is NOT added!");
            return false;
              
    }


     
     ////////// Get All products
    ///////// bought = 1 for retrieving user history
    ///////// bought = 0 for retrieving stored shopping cart
    public ArrayList<CartItem> getAllCartItems (int userId, int bought)
    {
        ArrayList<CartItem> cartItems = new ArrayList();
        int sum =0;
        try {
            openConnection();
            
            pst = conn.prepareStatement("select * from ecommerce.shoppingcart WHERE userid=? and bought=?");
            pst.setInt(1,userId);
            pst.setInt(2,bought);
             
             
            rs = pst.executeQuery();
           
            while(rs.next())
             {
                 sum++;
                 //////////////// check which column contains my Email to get friends data
                  CartItem cartItem = new CartItem(rs.getInt("userid"), rs.getInt("productid"),rs.getInt("quantity") , rs.getInt("bought"));
                  cartItems.add(cartItem);
               
             }
             System.out.println("sum: "+sum);
        } catch (SQLException ex) {
            
            System.out.println("ERROR :: DatabaseHandler.getAllcartItems()" + ex.toString());
            
        }
          
        return cartItems;
    }
    
   
    
     ///// ---- remove a cartItem 
    public boolean removeCartItem( CartItem cartItem ) {
       
        //////////// Delayed because of some future issues 3shan ana bnaaaaaaaaam :)
        /////////// will be discussed BOKRA in shaa ALLAH ... 27/2/2017
        
        
        ////////// Main key points are :
        //////////////// 1. Which to remove? bought=0 Or bought=1
        //////////////// 2. what about when a Single user orders' history
        ////////////////    "if he bought two different Orders using two differnt carts sequentaially
        ///////////////////    then they will be stored all as a sing order from a single shopping cart
        
        ////////// for example: Ehab have a cart, then he bought all cart items >> so all those items will marked as bought in db >> b=1
        ///////////   if ehab came after that and filled a new cart and tried to buy it >> then these new products also will be b=1  
        ////////////// and if he tried to view his history >> then all products will be seen as a SINGLE oreder !
        return false;
              
    }

   

    ////////////////////// End of -- CART -- processing ////////////////////////
    
    
}
