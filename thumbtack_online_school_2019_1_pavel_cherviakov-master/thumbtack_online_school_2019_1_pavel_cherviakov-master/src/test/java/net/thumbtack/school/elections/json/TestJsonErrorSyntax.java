package net.thumbtack.school.elections.json;

import com.google.gson.Gson;
import net.thumbtack.school.elections.errors.json.SyntaxJsonErrorCode;
import net.thumbtack.school.elections.mybatis.utils.MyBatisUtils;
import net.thumbtack.school.elections.server.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJsonErrorSyntax {

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
    public void testRegisterOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.registerVoter(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.registerVoter(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.registerVoter(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testLoginOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.login(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.login(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.login(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testLogoutOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.logout(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.logout(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.logout(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

    @Test
    public void testAddOfferOnErrorSyntaxJson() {
        String jsonRequest1 = "";
        String jsonRequest2 = "{";
        String jsonRequest3 = "{'df':'df}";

        String jsonResponse1 = Server.addOffer(jsonRequest1);
        SyntaxJsonErrorCode actual1 = new Gson().fromJson(jsonResponse1, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected1 = new SyntaxJsonErrorCode();
        expected1.setErrorString(expected1.getErrorSyntax());

        String jsonResponse2 = Server.addOffer(jsonRequest2);
        SyntaxJsonErrorCode actual2 = new Gson().fromJson(jsonResponse2, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected2 = new SyntaxJsonErrorCode();
        expected2.setErrorString(expected2.getErrorSyntax());

        String jsonResponse3 = Server.addOffer(jsonRequest3);
        SyntaxJsonErrorCode actual3 = new Gson().fromJson(jsonResponse3, SyntaxJsonErrorCode.class);
        SyntaxJsonErrorCode expected3 = new SyntaxJsonErrorCode();
        expected3.setErrorString(expected3.getErrorSyntax());

        assertEquals(expected1.getErrorString(), actual1.getErrorString());
        assertEquals(expected2.getErrorString(), actual2.getErrorString());
        assertEquals(expected3.getErrorString(), actual3.getErrorString());
    }

}
