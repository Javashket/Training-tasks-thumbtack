package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.voter.RegisterErrorCode;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRegister {

    private static boolean setUpIsDone = false;

    @BeforeAll()
    public static void setUp() {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
    }

    @BeforeEach()
    public void startServer() {
        Server.startServer(null);
    }

    @AfterEach()
    public void stopServer() {
        Server.stopServer(null);
    }

    @Test
    public void testRegisterVoter(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","logpass","passlogpass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResponse, RegisterVoterDtoResponse.class);
        assertNotNull(result.getToken());
    }

    @Test
    public void testRegisterVoterWithEmptyFirstName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("","Иванов",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getNullFirstName());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyLastName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getNullLastName());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyStreet(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getNullStreet());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyHouse(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getNullHouse());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyLogin(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getNullLogin());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyPassword(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","pass","");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getNullPassword());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthFirstName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("qwertyuiopaskfghjklzxcvbnmqwert","Иванов",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthFirstName());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthLastName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","qwertyuiopaskfghjklzxcvbnmqwert",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthLastName());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthPatronymic(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "qwertyuiopaskfghjklzxcvbnmqwert","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthPatronymic());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthStreet(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","qwertyuiopaskfghjklzxcvbnmqwert","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthStreet());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthHouse(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","qwertyuiopaskfghjklzxcvbnmqwert", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthHouse());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthApartment(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "qwertyuiopaskfghjklzxcvbnmqwert","password","password");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthApartment());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthLogin(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","qwertyuiopaskfghjklzxcvbnmqwert","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthLogin());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthPassword(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password","qwertyuiopaskfghjklzxcvbnmqwert");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getLengthPassword());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithSameLogin() {
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password","password");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Роман","Иванов",
                "Иванович","улица","дом", "56","password","password");
        String jsonRequest = new Gson().toJson(request1);
        Server.registerVoter(jsonRequest);
        jsonRequest = new Gson().toJson(request2);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getSameLogin());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterSameVoter() {
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password1","password");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password2","password");
        String jsonRequest = new Gson().toJson(request1);
        Server.registerVoter(jsonRequest);
        jsonRequest = new Gson().toJson(request2);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterErrorCode expected = new RegisterErrorCode();
        expected.setErrorString(expected.getSameVoter());
        RegisterErrorCode result = new Gson().fromJson(jsonResponse, RegisterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }
}
