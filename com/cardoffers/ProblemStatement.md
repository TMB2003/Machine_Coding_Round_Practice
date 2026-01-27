Card Offers System (Java)
📌 Problem Statement

Many users hesitate to complete online purchases due to high prices. This system helps users discover credit card–specific offers applicable to them, enabling savings during purchases.

The system supports:

User management

Card management

Offer lifecycle management

Offer search (prefix & complete)

Personalized offer discovery for users

✅ Functionalities Supported

Add user details (name, email, etc.)

Add card(s) for a user

Create a card offer

Update an existing offer

Delete an offer

Search all offers based on:

Prefix search

Complete search
using search_term and search_field (merchant/category)

Search active offers applicable to a specific user

🧩 Example Offer
Get 10% off on CRED store on all HDFC credit cards
Coupon Code: CRED10

offerCode   = CRED10
merchant    = CRED
category    = Shopping
description = Get 10% off on CRED store
issuer      = HDFC
status      = ACTIVE

🧠 Design Decisions

In-memory storage using HashMap and List

SOLID principles

Clear separation of:

Models

Enums

Service interfaces

Service implementation

Easily extensible for:

New search fields

New offer attributes

Persistence layer in future

🚫 Constraints Followed

❌ No REST APIs

❌ No database

✅ Unit tested using JUnit

✅ Template interfaces respected

✅ Clean & modular code