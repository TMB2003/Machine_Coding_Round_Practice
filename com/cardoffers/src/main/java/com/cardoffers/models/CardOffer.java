package com.cardoffers.models;

import com.cardoffers.enums.Issuer;
import com.cardoffers.enums.OfferStatus;

public class CardOffer {
    private String id;
    private String offerCode;
    private String merchant;
    private String category;
    private String description;
    private Issuer issuer;
    private OfferStatus status;

    public CardOffer(String id, String offerCode, String merchant, String category, String description, Issuer issuer, OfferStatus status) {
        this.id = id;
        this.offerCode = offerCode;
        this.merchant = merchant;
        this.category = category;
        this.description = description;
        this.issuer = issuer;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
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

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }
}
