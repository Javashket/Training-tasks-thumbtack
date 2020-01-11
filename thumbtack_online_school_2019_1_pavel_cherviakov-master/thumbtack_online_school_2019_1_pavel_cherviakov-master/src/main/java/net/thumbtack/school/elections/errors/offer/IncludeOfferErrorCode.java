package net.thumbtack.school.elections.errors.offer;

public class IncludeOfferErrorCode {

    private String notFoundOffer = "Предложение не найдено.";
    private String errorString;

    public IncludeOfferErrorCode() {

    }

    public String getNotFoundOffer() {
        return notFoundOffer;
    }

    public void setNotFoundOffer(String notFoundOffer) {
        this.notFoundOffer = notFoundOffer;
    }

    public String getErrorString() {
        return errorString;
    }

    public IncludeOfferErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
