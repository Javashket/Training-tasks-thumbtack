package net.thumbtack.school.elections.errors.voter;

public class RegisterErrorCode {

    private String nullFirstName = "Пустое имя.";
    private String nullLastName = "Пустая фамилия.";
    private String nullStreet = "Пустая улица.";
    private String nullHouse = "Пустой дом.";
    private String nullLogin = "Пустой логин.";
    private String nullPassword = "Пустой пароль.";
    private String lengthFirstName = "Длина имени не более 30 символов.";
    private String lengthLastName = "Длина фамилии не более 30 символов.";
    private String lengthPatronymic = "Длина отчества не менее 1 не более 30 символов.";
    private String lengthStreet = "Длина улицы не более 30 символов.";
    private String lengthHouse = "Длина дома не более 30 символов.";
    private String lengthApartment = "Длина квартиры не менее 1 не более 30 символов.";
    private String lengthLogin = "Длина логина не менее 6 не более 30 символов.";
    private String lengthPassword = "Длина пароля менее 6 не более 30 символов.";
    private String sameLogin = "Такой логин уже существует";
    private String sameVoter = "Такой пользователь уже существует";
    private String errorString;

    public RegisterErrorCode() {
    }

    public String getNullStreet() {
        return nullStreet;
    }

    public String getLengthFirstName() {
        return lengthFirstName;
    }

    public String getSameVoter() {
        return sameVoter;
    }

    public String getLengthLastName() {
        return lengthLastName;
    }

    public String getSameLogin() {
        return sameLogin;
    }

    public String getLengthPatronymic() {
        return lengthPatronymic;
    }

    public String getLengthApartment() {
        return lengthApartment;
    }

    public String getLengthStreet() {
        return lengthStreet;
    }

    public String getLengthHouse() {
        return lengthHouse;
    }

    public String getLengthLogin() {
        return lengthLogin;
    }

    public String getLengthPassword() {
        return lengthPassword;
    }

    public String getNullHouse() {
        return nullHouse;
    }

    public String getNullLogin() {
        return nullLogin;
    }

    public String getNullPassword() {
        return nullPassword;
    }

    public String getNullFirstName() {
        return nullFirstName;
    }

    public String getNullLastName() {
        return nullLastName;
    }

    public String getErrorString() {
        return errorString;
    }

    public RegisterErrorCode setErrorString(String errorString) {
        this.errorString = errorString;
        return this;
    }
}
