package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Offer;

import java.util.List;

public class AllOffersDtoResponse {

    private List<Offer> offers;

    public AllOffersDtoResponse() {

    }

    public AllOffersDtoResponse(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
