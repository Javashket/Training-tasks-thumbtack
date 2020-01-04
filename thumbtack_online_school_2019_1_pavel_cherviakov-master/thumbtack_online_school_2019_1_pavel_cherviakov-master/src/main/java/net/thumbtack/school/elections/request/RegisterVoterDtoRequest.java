package net.thumbtack.school.elections.request;

import java.util.Objects;

public class RegisterVoterDtoRequest {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String street;
    private String house;
    private String apartment;
    private String login;
    private String password;

    public RegisterVoterDtoRequest() {
    }

    public RegisterVoterDtoRequest(String firstName, String lastName,
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
        if (!(o instanceof RegisterVoterDtoRequest)) return false;
        RegisterVoterDtoRequest that = (RegisterVoterDtoRequest) o;
        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPatronymic(), that.getPatronymic()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getHouse(), that.getHouse()) &&
                Objects.equals(getApartment(), that.getApartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPatronymic(), getStreet(), getHouse(), getApartment());
    }
}
