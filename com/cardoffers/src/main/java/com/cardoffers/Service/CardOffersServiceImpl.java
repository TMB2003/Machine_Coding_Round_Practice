package com.cardoffers.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cardoffers.models.CardOffer;
import com.cardoffers.models.SearchRequest;
import com.cardoffers.models.SearchResponse;
import com.cardoffers.models.User;
import com.cardoffers.models.UserCard;

public class CardOffersServiceImpl implements CardOffersService {

    private final Set<String> usersId = new HashSet<>();
    private final Set<String> userCards = new HashSet<>();
    private final Map<String, CardOffer> offers = new HashMap<>();

    public CardOffersServiceImpl() {}

    // ---------------- USER ----------------

    @Override
    public Boolean createUser(User user) {
        if(user == null || user.getUserId() == null || user.getName() == null || user.getEmail() == null) {
            return false;
        }
        if(usersId.contains(user.getUserId())){
            return false;
        }
        usersId.add(user.getUserId());
        return true;
    }

    // ---------------- USER CARD ----------------

    @Override
    public Boolean addCard(UserCard userCard) {
        if(userCard == null || userCard.getUserId() == null || userCard.getCardNumber() == null || userCard.getIssuer() == null){
            return false;
        }

        String newCard = userCard.getUserId() + " " + userCard.getCardNumber();
        if(!usersId.contains(userCard.getUserId()) || userCards.contains(newCard)){
            return false;
        }

        userCards.add(newCard);
        return true;
    }

    // ---------------- OFFER CREATE ----------------

    @Override
    public Boolean createOffer(CardOffer cardOffer) {
        if(cardOffer == null || cardOffer.getId() == null ||
            cardOffer.getOfferCode() == null || cardOffer.getMerchant() == null ||
            cardOffer.getCategory() == null || cardOffer.getDescription() == null ||
            cardOffer.getIssuer() == null || cardOffer.getStatus() == null ){
                return false;
            }

        String newCardOffer = cardOffer.getOfferCode();
        if(offers.containsKey(newCardOffer)) return false;

        offers.put(newCardOffer, cardOffer);
        return true;
    }

    // ---------------- OFFER UPDATE ----------------

    @Override
    public Boolean updateOffer(CardOffer cardOffer) {
        if(cardOffer == null || cardOffer.getId() == null ||
            cardOffer.getOfferCode() == null || cardOffer.getMerchant() == null ||
            cardOffer.getCategory() == null || cardOffer.getDescription() == null ||
            cardOffer.getIssuer() == null || cardOffer.getStatus() == null ){
                return false;
            }

        String businessKey = cardOffer.getOfferCode() + "_" + cardOffer.getMerchant() + "_" + cardOffer.getIssuer();

        // Check if the offer exists by finding it in the map
        CardOffer existingOffer = null;
        String existingKey = null;
        for(Map.Entry<String, CardOffer> entry : offers.entrySet()){
            if(entry.getValue().getId().equals(cardOffer.getId())){
                existingOffer = entry.getValue();
                existingKey = entry.getKey();
                break;
            }
        }

        if(existingOffer == null) return false;

        // Check if the new business key conflicts with any other offer
        for(Map.Entry<String, CardOffer> entry : offers.entrySet()){
            if(!entry.getKey().equals(existingKey)){
                CardOffer otherOffer = entry.getValue();
                String otherBusinessKey = otherOffer.getOfferCode() + "_" + otherOffer.getMerchant() + "_" + otherOffer.getIssuer();
                if(otherBusinessKey.equals(businessKey)){
                    return false;
                }
            }
        }

        // Remove old entry and add new one with updated business key
        offers.remove(existingKey);
        offers.put(businessKey, cardOffer);
        return true;
    }

    // ---------------- DELETE ----------------

    @Override
    public Boolean deleteOffer(String offerId) {
        if(offerId == null) return false;

        // Find the offer by ID and get its business key
        String keyToRemove = null;
        for(Map.Entry<String, CardOffer> entry : offers.entrySet()){
            if(entry.getValue().getId().equals(offerId)){
                keyToRemove = entry.getKey();
                break;
            }
        }

        if(keyToRemove == null) return false;

        offers.remove(keyToRemove);
        return true;
    }

    // ---------------- SEARCH ALL ----------------

    @Override
    public SearchResponse searchAllOffers(SearchRequest request) {
        if(request == null || request.getSearchTerm() == null || request.getSearchField() == null ||
            request.getSearchType() == null || request.getOfferStatus() == null || request.getIssuer() == null){
                return new SearchResponse(0, new ArrayList<>());
            }

        List<CardOffer> offerList = new ArrayList<>();

        for(Map.Entry<String, CardOffer> entry : offers.entrySet()){
            CardOffer currentOffer = entry.getValue();

            if (currentOffer.getIssuer() == request.getIssuer() && currentOffer.getStatus() == request.getOfferStatus()) {
                offerList.add(currentOffer);
            }
        }

        return new SearchResponse(offerList.size(), offerList);
    }

    // ---------------- SEARCH FOR USER ----------------

    @Override
    public SearchResponse searchOffersForUser(String userId) {
        if(userId == null) return new SearchResponse(0, new ArrayList<>());

        List<CardOffer> offersList = new ArrayList<>();

        for(Map.Entry<String, CardOffer> entry : offers.entrySet()){
            CardOffer currentOffer = entry.getValue();

            if (currentOffer.getId() == userId) {
                offersList.add(currentOffer);
            }
        }
        return new SearchResponse(offersList.size(), offersList);
    }
}
