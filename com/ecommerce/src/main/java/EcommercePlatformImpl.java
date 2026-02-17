package com.ecommerce;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class EcommercePlatformImpl implements EcommercePlatform {

    private static class Product {
    
    }

    private static class User {
        
    }

    private static class Order {
    }

    @Override
    public void init(Helper helper, int productsCount) {
        
    }

    @Override
    public void addProduct(int productId, String productName, int price) {
    
    }

    @Override
    public void addInventory(int productId, int quantity) {
        
    }

    @Override
    public void createUser(String userId) {
        
    }

    @Override
    public String addToCart(String userId, int productId, int quantity) {
        
    }

    @Override
    public String removeFromCart(String userId, int productId) {
        
    }

    @Override
    public String placeOrder(String orderId, String userId, String paymentMode) {
        
    }

    @Override
    public int getInventory(int productId) {
        
    }
}
