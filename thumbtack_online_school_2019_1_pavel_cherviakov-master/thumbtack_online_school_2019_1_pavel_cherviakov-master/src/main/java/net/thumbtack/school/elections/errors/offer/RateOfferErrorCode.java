package net.thumbtack.school.elections.errors.offer;

public class RateOfferErrorCode {

    private String ratingAuthorConst = "Оценка автора предложения всегда неизменна и равна 5.";
    private String errorString;

    public RateOfferErrorCode() {

    }

    public String getRatingAuthorConst() {
        return ratingAuthorConst;
    }

    public void setRatingAuthorConst(String ratingAuthorConst) {
        this.ratingAuthorConst = ratingAuthorConst;
    }

    public String getErrorString() {
        return errorString;
    }

    public RateOfferErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }

}
