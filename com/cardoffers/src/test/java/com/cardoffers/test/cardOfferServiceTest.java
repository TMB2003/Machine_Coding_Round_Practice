package com.cardoffers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cardoffers.Service.CardOffersService;
import com.cardoffers.Service.CardOffersServiceImpl;
import com.cardoffers.enums.Issuer;
import com.cardoffers.enums.OfferStatus;
import com.cardoffers.enums.SearchField;
import com.cardoffers.enums.SearchType;
import com.cardoffers.models.CardOffer;
import com.cardoffers.models.SearchRequest;
import com.cardoffers.models.SearchResponse;
import com.cardoffers.models.User;
import com.cardoffers.models.UserCard;

class CardOffersServiceTest {

    private static CardOffersService cardOffersService;

    private final List<String> randomNames = Arrays.asList(
            "Gunjit", "Harsha", "Jas", "Vipul", "Sanjay"
    );

    private final List<String> randomCardNumbers = Arrays.asList(
            "1234-1234-1234-1234",
            "2345-2345-2345-2345",
            "3456-3456-3456-3456",
            "4567-4567-4567-4567",
            "5678-5678-5678-5678"
    );

    @BeforeEach
    public void init() {
        cardOffersService = new CardOffersServiceImpl();
    }

    // ---------------- USER TESTS ----------------

    @Test
    public void testCreateUser() {
        User user = getRandomUser();
        assertTrue(cardOffersService.createUser(user));
    }

    @Test
    public void testAddDuplicateUser() {
        User user = getRandomUser();
        assertTrue(cardOffersService.createUser(user));
        assertFalse(cardOffersService.createUser(user));
        assertFalse(cardOffersService.createUser(getUser(user.getUserId())));
    }

    @Test
    public void testAddInvalidUser() {
        assertFalse(cardOffersService.createUser(new User(null, null, null)));
        assertFalse(cardOffersService.createUser(new User(null, getRandomName(), null)));
        assertFalse(cardOffersService.createUser(new User(getRandomName(), null, null)));
        assertFalse(cardOffersService.createUser(new User(null, null, getRandomName())));
    }

    // ---------------- USER CARD TESTS ----------------

    @Test
    public void testAddUserCard() {
        User user = getRandomUser();
        assertTrue(cardOffersService.createUser(user));

        UserCard userCard = new UserCard(
                user.getUserId(),
                getRandomCardNumber(),
                getRandomIssuer()
        );

        assertTrue(cardOffersService.addCard(userCard));
    }

    @Test
    public void testAddUserCardDuplicate() {
        User user = getRandomUser();
        cardOffersService.createUser(user);

        UserCard userCard = new UserCard(
                user.getUserId(),
                getRandomCardNumber(),
                getRandomIssuer()
        );

        assertTrue(cardOffersService.addCard(userCard));
        assertFalse(cardOffersService.addCard(userCard));
    }

    @Test
    public void testAddUserCardInvalid() {
        User user = getRandomUser();
        UserCard userCard = new UserCard(user.getUserId(), null, null);
        assertFalse(cardOffersService.addCard(userCard));
    }

    // ---------------- OFFER CREATE TESTS ----------------

    @Test
    public void testCreateOffer() {
        CardOffer offer = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.ACTIVE);
        assertTrue(cardOffersService.createOffer(offer));
    }

    @Test
    public void testCreateOfferDuplicate() {
        CardOffer offer1 = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.ACTIVE);
        CardOffer offer2 = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.ACTIVE);

        assertTrue(cardOffersService.createOffer(offer1));
        assertFalse(cardOffersService.createOffer(offer2));
    }

    @Test
    public void testCreateOfferInvalid() {
        assertFalse(cardOffersService.createOffer(
                new CardOffer("1", null, "CRED", "Shopping", "Desc", Issuer.SBI, OfferStatus.ACTIVE)
        ));

        assertFalse(cardOffersService.createOffer(
                new CardOffer("1", "WIN20", null, "Shopping", "Desc", Issuer.SBI, OfferStatus.ACTIVE)
        ));

        assertFalse(cardOffersService.createOffer(
                new CardOffer("1", "WIN20", "CRED", null, "Desc", Issuer.SBI, OfferStatus.ACTIVE)
        ));

        assertFalse(cardOffersService.createOffer(
                new CardOffer("1", "WIN20", "CRED", "Shopping", null, Issuer.SBI, OfferStatus.ACTIVE)
        ));

        assertFalse(cardOffersService.createOffer(
                new CardOffer("1", "WIN20", "CRED", "Shopping", "Desc", null, OfferStatus.ACTIVE)
        ));

        assertFalse(cardOffersService.createOffer(
                new CardOffer("1", "WIN20", "CRED", "Shopping", "Desc", Issuer.SBI, null)
        ));
    }

    // ---------------- OFFER UPDATE TESTS ----------------

    @Test
    public void testUpdateOfferSuccess() {
        CardOffer active = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.ACTIVE);
        CardOffer inactive = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.INACTIVE);

        cardOffersService.createOffer(active);
        assertTrue(cardOffersService.updateOffer(inactive));
    }

    @Test
    public void testUpdateOfferNonExistent() {
        CardOffer offer = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.INACTIVE);
        assertFalse(cardOffersService.updateOffer(offer));
    }

    @Test
    public void testUpdateOfferFailureInvalid() {
        cardOffersService.createOffer(createOffer("1", "WIN20", Issuer.SBI, OfferStatus.ACTIVE));

        assertFalse(cardOffersService.updateOffer(
                new CardOffer("1", "WIN20", null, "Shopping", "Desc", Issuer.SBI, OfferStatus.ACTIVE)
        ));
    }

    // ---------------- DELETE TESTS ----------------

    @Test
    public void testDeleteOfferSuccess() {
        CardOffer offer = createOffer("1", "WIN28", Issuer.SBI, OfferStatus.ACTIVE);
        cardOffersService.createOffer(offer);

        assertTrue(cardOffersService.deleteOffer("1"));
    }

    @Test
    public void testDeleteOfferNonExistent() {
        assertFalse(cardOffersService.deleteOffer("999"));
    }

    // ---------------- SEARCH TESTS ----------------

    @Test
    public void testSearchAllOffersFullText() {
        CardOffer o1 = createOffer("1", "WIN20", Issuer.SBI, OfferStatus.ACTIVE);
        CardOffer o2 = createOffer("2", "TRAVEL30", Issuer.SBI, OfferStatus.ACTIVE);
        CardOffer o3 = createOffer("3", "PIZZA10", Issuer.ICICI, OfferStatus.ACTIVE);

        cardOffersService.createOffer(o1);
        cardOffersService.createOffer(o2);
        cardOffersService.createOffer(o3);

        SearchRequest request = new SearchRequest(
                "CRED",
                SearchField.MERCHANT,
                SearchType.COMPLETE,
                OfferStatus.ACTIVE,
                Issuer.SBI
        );

        SearchResponse response = cardOffersService.searchAllOffers(request);

        assertEquals(2, response.getTotalCount());
    }
    @Test
    public void testCreateOfferDuplicateBusinessKey() {
        CardOffer offer1 = new CardOffer(
                "1", "WIN20", "CRED", "Shopping",
                "Get 20% off", Issuer.SBI, OfferStatus.ACTIVE
        );

        CardOffer offer2 = new CardOffer(
                "2", "WIN20", "CRED", "Shopping",
                "Another description", Issuer.SBI, OfferStatus.ACTIVE
        );

        assertTrue(cardOffersService.createOffer(offer1));
        assertFalse(cardOffersService.createOffer(offer2));
    }

    @Test
    public void testUpdateOfferFailureSameOfferExists() {

        CardOffer offer1 = new CardOffer(
                "1", "WIN20", "CRED", "Shopping",
                "Get 20% off", Issuer.SBI, OfferStatus.ACTIVE
        );

        CardOffer offer2 = new CardOffer(
                "2", "TREE20", "CRED", "Shopping",
                "Plant offer", Issuer.SBI, OfferStatus.ACTIVE
        );

        cardOffersService.createOffer(offer1);
        cardOffersService.createOffer(offer2);

        // updating offer2 to conflict with offer1
        CardOffer updatedOffer2 = new CardOffer(
                "2", "WIN20", "CRED", "Shopping",
                "Conflict update", Issuer.SBI, OfferStatus.ACTIVE
        );

        assertFalse(cardOffersService.updateOffer(updatedOffer2));
    }

    @Test
    public void testSearchAllOffersInvalidRequest() {
        SearchResponse response = cardOffersService.searchAllOffers(null);
        assertEquals(0, response.getTotalCount());
        assertTrue(response.getOffers().isEmpty());
    }

    @Test
    public void testSearchOffersForUserNoCards() {
        User user = new User("U1", "Taha", "taha@gmail.com");
        cardOffersService.createUser(user);

        SearchResponse response = cardOffersService.searchOffersForUser(user.getUserId());

        assertEquals(0, response.getTotalCount());
        assertTrue(response.getOffers().isEmpty());
    }

    // ---------------- HELPER METHODS ----------------

    private CardOffer createOffer(String id, String code, Issuer issuer, OfferStatus status) {
        return new CardOffer(
                id,
                code,
                "CRED",
                "Shopping",
                "Get discount",
                issuer,
                status
        );
    }

    private User getUser(String userId) {
        String name = getRandomName();
        return new User(userId, name, name + "@gmail.com");
    }

    private String getRandomCardNumber() {
        return randomCardNumbers.get(getRandomNumber(randomCardNumbers.size()));
    }

    private String getRandomName() {
        return randomNames.get(getRandomNumber(randomNames.size()));
    }

    private Issuer getRandomIssuer() {
        return Issuer.values()[getRandomNumber(Issuer.values().length)];
    }

    private User getRandomUser() {
        String name = getRandomName();
        return new User(UUID.randomUUID().toString(), name, name + "@gmail.com");
    }

    private int getRandomNumber(int bound) {
        return (int) (Math.random() * bound);
    }
}
