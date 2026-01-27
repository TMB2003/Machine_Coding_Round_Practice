package com.cardoffers.models;

import java.util.List;

public class SearchResponse {
    private int totalCount;
    private List<CardOffer> offers;

    public SearchResponse(int totalCount, List<CardOffer> offers) {
        this.totalCount = totalCount;
        this.offers = offers;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CardOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<CardOffer> offers) {
        this.offers = offers;
    }
}
