package com.ecommerce;

public interface EcommercePlatform {
    void init(Helper helper, int productsCount);
    void addProduct(int productId, String productName, int price);
    void addInventory(int productId, int quantity);
    void createUser(String userId);
    String addToCart(String userId, int productId, int quantity);
    String removeFromCart(String userId, int productId);
    String placeOrder(String orderId, String userId, String paymentMode);
    int getInventory(int productId);
}
