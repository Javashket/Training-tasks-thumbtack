package net.thumbtack.school.ttschool;

import java.util.Objects;

import static net.thumbtack.school.ttschool.TrainingErrorCode.*;

public class Trainee {

    private String firstName, lastName;
    private int rating;

    public Trainee(String firstName, String lastName, int rating) throws TrainingException {
        firstNameEqualsNullOrEmpty(firstName);
        this.firstName = firstName;
        lastNameEqualsNullOrEmpty(lastName);
        this.lastName = lastName;
        raitingEqualsBounds(rating);
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws TrainingException {
        firstNameEqualsNullOrEmpty(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) throws TrainingException {
        lastNameEqualsNullOrEmpty(lastName);
        this.lastName = lastName;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) throws TrainingException {
        raitingEqualsBounds(rating);
        this.rating = rating;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

     public static void firstNameEqualsNullOrEmpty(String firstName) throws TrainingException {
        if(firstName == null || firstName.equals("")) {
            throw new TrainingException(TRAINEE_WRONG_FIRSTNAME);
        }
    }

    public static void lastNameEqualsNullOrEmpty(String lastName) throws TrainingException {
        if(lastName == null || lastName.equals("")) {
            throw new TrainingException(TRAINEE_WRONG_LASTNAME);
        }
    }

    public static void raitingEqualsBounds(int rating) throws TrainingException {
        if( rating < 1 || rating > 5) {
            throw new TrainingException(TRAINEE_WRONG_RATING);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return rating == trainee.rating &&
                Objects.equals(firstName, trainee.firstName) &&
                Objects.equals(lastName, trainee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, rating);
    }
}
