package com.cardoffers.Service;

import com.cardoffers.models.CardOffer;
import com.cardoffers.models.SearchRequest;
import com.cardoffers.models.SearchResponse;
import com.cardoffers.models.User;
import com.cardoffers.models.UserCard;

public interface CardOffersService {

    // Create a new user
    Boolean createUser(User user);

    // Add a card for a user
    Boolean addCard(UserCard userCard);

    // Create a new card offer
    // Offer should be unique on (offerCode + merchant + issuer)
    Boolean createOffer(CardOffer cardOffer);

    // Update existing offer by offerId
    Boolean updateOffer(CardOffer cardOffer);

    // Delete offer by offerId
    Boolean deleteOffer(String offerId);

    // Search offers based on filters
    SearchResponse searchAllOffers(SearchRequest searchRequest);

    // Search all active offers applicable for a user
    SearchResponse searchOffersForUser(String userId);
}
