package com.ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcommercePlatformTest {

    private EcommercePlatform platform;
    private Helper helper;

    @BeforeEach
    void setup() {
        helper = new Helper();
        platform = new EcommercePlatformImpl();
        platform.init(helper, 5);

        platform.addProduct(0, "Laptop", 50000);
        platform.addProduct(1, "Phone", 30000);

        platform.addInventory(0, 10);
        platform.addInventory(1, 5);

        platform.createUser("user1");
        platform.createUser("user2");
    }

    @Test
    void testAddToCartSuccess() {
        String result = platform.addToCart("user1", 0, 2);
        assertEquals("added to cart", result);
    }

    @Test
    void testAddToCartInsufficientInventory() {
        String result = platform.addToCart("user1", 1, 10);
        assertEquals("insufficient inventory", result);
    }

    @Test
    void testRemoveFromCart() {
        platform.addToCart("user1", 0, 1);
        String result = platform.removeFromCart("user1", 0);
        assertEquals("removed", result);
    }

    @Test
    void testPlaceOrderSuccess() {
        platform.addToCart("user1", 0, 3);
        String result = platform.placeOrder("order1", "user1", "upi");

        assertEquals("order placed", result);
        assertEquals(7, platform.getInventory(0));
    }

    @Test
    void testPlaceOrderCartEmpty() {
        String result = platform.placeOrder("order2", "user1", "upi");
        assertEquals("cart empty", result);
    }

    @Test
    void testPlaceOrderInvalidPayment() {
        platform.addToCart("user1", 0, 1);
        String result = platform.placeOrder("order3", "user1", "crypto");

        assertEquals("payment mode not supported", result);
    }

    @Test
    void testConcurrentOrders() throws InterruptedException {
        platform.addToCart("user1", 0, 5);
        platform.addToCart("user2", 0, 5);

        Thread t1 = new Thread(() ->
                platform.placeOrder("order4", "user1", "upi")
        );

        Thread t2 = new Thread(() ->
                platform.placeOrder("order5", "user2", "upi")
        );

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(0, platform.getInventory(0));
    }
}
