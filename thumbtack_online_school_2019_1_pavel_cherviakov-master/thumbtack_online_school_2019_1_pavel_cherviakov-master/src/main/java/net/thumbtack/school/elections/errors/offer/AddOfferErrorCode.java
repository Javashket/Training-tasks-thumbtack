package net.thumbtack.school.elections.errors.offer;

public class AddOfferErrorCode {

    private String sameOffer = "Предложение уже существует.";
    private String emptyOffer = "Пустое предложение.";

    private String errorString;

    public AddOfferErrorCode() {

    }

    public String getSameOffer() {
        return sameOffer;
    }

    public void setSameOffer(String sameOffer) {
        this.sameOffer = sameOffer;
    }

    public String getEmptyOffer() {
        return emptyOffer;
    }

    public void setEmptyOffer(String emptyOffer) {
        this.emptyOffer = emptyOffer;
    }

    public String getErrorString() {
        return errorString;
    }

    public AddOfferErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
