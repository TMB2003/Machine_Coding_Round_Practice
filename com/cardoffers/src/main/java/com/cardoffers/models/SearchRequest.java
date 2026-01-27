package com.cardoffers.models;

import com.cardoffers.enums.SearchField;
import com.cardoffers.enums.SearchType;
import com.cardoffers.enums.OfferStatus;
import com.cardoffers.enums.Issuer;

public class SearchRequest {
    private String searchTerm;
    private SearchField searchField;
    private SearchType searchType;
    private OfferStatus offerStatus;
    private Issuer issuer;

    public SearchRequest(String searchTerm, SearchField searchField, SearchType searchType, OfferStatus offerStatus, Issuer issuer) {
        this.searchTerm = searchTerm;
        this.searchField = searchField;
        this.searchType = searchType;
        this.offerStatus = offerStatus;
        this.issuer = issuer;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SearchField getSearchField() {
        return searchField;
    }

    public void setSearchField(SearchField searchField) {
        this.searchField = searchField;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }
}
