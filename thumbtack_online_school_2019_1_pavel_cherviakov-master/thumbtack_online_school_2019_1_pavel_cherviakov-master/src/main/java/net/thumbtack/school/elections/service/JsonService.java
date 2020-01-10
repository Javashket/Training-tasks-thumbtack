package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.elections.errors.json.SyntaxJsonErrorCode;

public class JsonService {

    private JsonService() {

    }

    public static String checkIsValidJson(String json) {
        try {
            JsonParser parser = new JsonParser();
            parser.parse(json);
            if (!json.contains("{")) {
                throw new JsonSyntaxException(json);
            }
        } catch(JsonSyntaxException ex) {
            System.out.println("df");
            SyntaxJsonErrorCode syntaxJsonErrorCode = new SyntaxJsonErrorCode();
            return new Gson().toJson(syntaxJsonErrorCode.setErrorString(syntaxJsonErrorCode.getErrorSyntax()));
        }
        return "";
    }
}

