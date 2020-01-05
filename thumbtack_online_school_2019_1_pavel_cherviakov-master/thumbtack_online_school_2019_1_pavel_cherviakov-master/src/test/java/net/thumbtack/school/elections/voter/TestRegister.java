package net.thumbtack.school.elections.voter;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.voter.RegisterVoterErrorCode;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Иван1","Иванов1",
                "Иванович1","улица1","дом1", "561","logpass1","passlogpass1");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Иван2","Иванов2",
                "Иванович2","улица2","дом2", "562","logpass2","passlogpass2");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResponse1, RegisterVoterDtoResponse.class);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResponse2 = Server.registerVoter(jsonRequest1);
        assertNotNull(result1.getToken());
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResponse2, RegisterVoterDtoResponse.class);
        assertNotNull(result2.getToken());
        assertNotEquals(result1.getToken(), result2.getToken());
    }

    @Test
    public void testRegisterVoterWithEmptyFirstName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("","Иванов",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullFirstName());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyLastName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullLastName());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyStreet(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullStreet());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyHouse(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullHouse());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyLogin(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullLogin());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithEmptyPassword(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","pass","");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getNullPassword());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthFirstName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("qwertyuiopaskfghjklzxcvbnmqwert","Иванов",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthFirstName());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthLastName(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","qwertyuiopaskfghjklzxcvbnmqwert",
                "Иванович","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthLastName());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthPatronymic(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "qwertyuiopaskfghjklzxcvbnmqwert","улица","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthPatronymic());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthStreet(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","qwertyuiopaskfghjklzxcvbnmqwert","дом", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthStreet());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthHouse(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","qwertyuiopaskfghjklzxcvbnmqwert", "56","pass","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthHouse());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthApartment(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "qwertyuiopaskfghjklzxcvbnmqwert","password","password");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthApartment());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthLogin(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","qwertyuiopaskfghjklzxcvbnmqwert","pass");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthLogin());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }

    @Test
    public void testRegisterVoterWithInadmissibleLengthPassword(){
        RegisterVoterDtoRequest request = new RegisterVoterDtoRequest("Иван","Иванов",
                "Иванович","улица","дом", "56","password","qwertyuiopaskfghjklzxcvbnmqwert");
        String jsonRequest = new Gson().toJson(request);
        String jsonResponse = Server.registerVoter(jsonRequest);
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getLengthPassword());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
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
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getSameLogin());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
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
        RegisterVoterErrorCode expected = new RegisterVoterErrorCode();
        expected.setErrorString(expected.getSameVoter());
        RegisterVoterErrorCode result = new Gson().fromJson(jsonResponse, RegisterVoterErrorCode.class);
        assertEquals(expected.getErrorString(), result.getErrorString());
    }
}
