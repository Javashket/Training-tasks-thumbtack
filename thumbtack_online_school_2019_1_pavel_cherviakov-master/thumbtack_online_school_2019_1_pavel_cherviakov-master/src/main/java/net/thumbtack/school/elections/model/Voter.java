package net.thumbtack.school.elections.model;

import java.util.Objects;
import java.util.UUID;

public class Voter {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String street;
    private String house;
    private String apartment;
    private String login;
    private String password;
    private String token;

    public Voter() {
    }

    public Voter(String firstName, String lastName,
                 String patronymic, String street, String house,
                 String apartment, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.login = login;
        this.password = password;
        this.token = UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getStreet() {
        return street;
    }

    public String getToken() {
        return token;
    }

    public void setTokenIsEmpty() {
        this.token = "";
    }

    public void setTokenIsNewValue() {
        this.token = UUID.randomUUID().toString();
    }

    public String getHouse() {
        return house;
    }

    public String getApartment() {
        return apartment;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voter)) return false;
        Voter voter = (Voter) o;
        return Objects.equals(getFirstName(), voter.getFirstName()) &&
                Objects.equals(getLastName(), voter.getLastName()) &&
                Objects.equals(getPatronymic(), voter.getPatronymic()) &&
                Objects.equals(getStreet(), voter.getStreet()) &&
                Objects.equals(getHouse(), voter.getHouse()) &&
                Objects.equals(getApartment(), voter.getApartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getStreet(), getHouse(), getApartment());
    }
}
