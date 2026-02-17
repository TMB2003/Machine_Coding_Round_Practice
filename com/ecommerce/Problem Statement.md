🛒 Problem Statement
Design a Simple E-Commerce Platform (Low Level Design)

Design and implement the low-level design (LLD) of a simple e-commerce platform.
The system should support users, products, inventory, carts, and orders, while ensuring inventory consistency during concurrent order placement.

For Java, your solution will be tested in a multi-threaded environment, so appropriate synchronization and thread-safe data structures must be used.
For Python, the solution will be tested in a single-threaded environment.

📌 System Overview

Products are sold on the platform.

Each product has limited inventory.

Multiple users can browse products and add them to their carts.

A user can place an order for all items currently present in their cart.

Inventory must be deducted atomically when an order is placed.

⚙️ Functional Requirements
1️⃣ Initialize Platform
void init(Helper helper, int productsCount)


Initializes the platform.

helper must be used for logging (logs printed without it will not be visible).

productsCount represents total products sold on the platform.

Valid product IDs range from 0 to productsCount - 1.

2️⃣ Add Product
void addProduct(int productId, String productName, int price)


Adds a product to the product catalog.

productId is unique and always valid.

Price is a positive integer.

3️⃣ Add Inventory
void addInventory(int productId, int quantity)


Adds stock for a given product.

quantity is always a positive integer.

Product ID is guaranteed to be valid.

4️⃣ Create User
void createUser(String userId)


Creates a new user on the platform.

userId is a unique, non-null, non-blank string.

5️⃣ Add Item to Cart
String addToCart(String userId, int productId, int quantity)


Adds a product to the user’s cart.

A user can add multiple products and quantities to the cart.

Returns:

"added to cart"

"product not found"

"insufficient inventory"

6️⃣ Remove Item from Cart
String removeFromCart(String userId, int productId)


Removes a product from the user’s cart.

Returns:

"removed"

"item not in cart"

7️⃣ Place Order
String placeOrder(String orderId, String userId, String paymentMode)


Places an order for all items currently in the user’s cart.

Inventory must be reduced atomically.

Cart must be cleared after successful order placement.

Supported payment modes:

"cash"

"upi"

"card"

Returns (in this order of priority):

"order placed"

"cart empty"

"payment mode not supported"

"insufficient inventory"

8️⃣ Get Inventory
int getInventory(int productId)


Returns the available inventory for the given product.

Returns 0 if the product does not exist.

⚠️ Concurrency Constraints

Multiple users may place orders concurrently.

Inventory updates must be thread-safe.

Race conditions should be prevented.

📘 Notes

Assume in-memory storage only (no database).

No UI or API layer is required.

Focus on clean object-oriented design.

Follow SOLID principles where applicable.