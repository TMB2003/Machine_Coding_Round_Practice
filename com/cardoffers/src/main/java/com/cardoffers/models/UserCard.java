package com.cardoffers.models;

import com.cardoffers.enums.Issuer;

public class UserCard {
    private String userId;
    private String cardNumber;
    private Issuer issuer;

    public UserCard(String userId, String cardNumber, Issuer issuer) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.issuer = issuer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }
}
