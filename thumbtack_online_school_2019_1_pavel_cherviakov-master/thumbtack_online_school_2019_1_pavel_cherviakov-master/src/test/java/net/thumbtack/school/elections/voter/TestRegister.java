package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.Init;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.errors.voter.RegisterVoterErrorCode;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestRegister extends Init {

    @Test
    public void testRegisterVoter(){
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        String jsonResponseRegister1 = Server.registerVoter(jsonRequestRegister1);
        RegisterVoterDtoResponse resultRegister1 = new Gson().fromJson(jsonResponseRegister1, RegisterVoterDtoResponse.class);
        assertNotNull(resultRegister1.getToken());

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);
        RegisterVoterDtoResponse resultRegister2 = new Gson().fromJson(jsonResponseRegister2, RegisterVoterDtoResponse.class);
        assertNotNull(resultRegister2.getToken());

        assertNotEquals(resultRegister1.getToken(), resultRegister2.getToken());
    }

    @Test
    public void testRegisterVoterWithEmptyFirstName(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullFirstName());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyLastName(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","",
                "Иванович","улица","дом", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullLastName());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyStreet(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","","дом", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullStreet());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyHouse(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullHouse());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyLogin(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullLogin());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyPassword(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass2","");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullPassword());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthFirstName(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("qwertyuiopaskfghjklzxcvbnmqwert","Иванов",
                "Иванович","улица","дом", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthFirstName());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthLastName(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","qwertyuiopaskfghjklzxcvbnmqwert",
                "Иванович","улица","дом", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthLastName());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthPatronymic(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "qwertyuiopaskfghjklzxcvbnmqwert","улица","дом", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthPatronymic());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthStreet(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","qwertyuiopaskfghjklzxcvbnmqwert","дом", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthStreet());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthHouse(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","qwertyuiopaskfghjklzxcvbnmqwert", "56","logpass2","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthHouse());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthApartment(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "qwertyuiopaskfghjklzxcvbnmqwert","password","password");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthApartment());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthLogin(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","qwertyuiopaskfghjklzxcvbnmqwert","passlogpass1");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthLogin());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthPassword(){
        RegisterVoterDtoRequest requestRegister = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password","qwertyuiopaskfghjklzxcvbnmqwert");
        String jsonRequestRegister = new Gson().toJson(requestRegister);
        String jsonResponseRegister = Server.registerVoter(jsonRequestRegister);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthPassword());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterVoterWithSameLogin() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password","password");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        Server.registerVoter(jsonRequestRegister1);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Роман","Иванов",
                "Иванович","улица","дом", "56","password","password");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);

        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getSameLogin());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister2, RegisterVoterErrorCode.class);

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }

    @Test
    public void testRegisterSameVoter() {
        RegisterVoterDtoRequest requestRegister1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password1","password");
        String jsonRequestRegister1 = new Gson().toJson(requestRegister1);
        Server.registerVoter(jsonRequestRegister1);

        RegisterVoterDtoRequest requestRegister2 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password2","password");
        String jsonRequestRegister2 = new Gson().toJson(requestRegister2);
        String jsonResponseRegister2 = Server.registerVoter(jsonRequestRegister2);

        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getSameVoter());
        RegisterVoterErrorCode actual = new Gson().fromJson(jsonResponseRegister2, RegisterVoterErrorCode.class);

        assertEquals(expected.getErrorString(), actual.getErrorString());
    }
}
