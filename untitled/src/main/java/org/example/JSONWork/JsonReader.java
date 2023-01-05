package org.example.JSONWork;

import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
@Getter
public class JsonReader {
    private String name;
    private String password;
    private String url;
    private String userName;
    public JsonReader() {
        Object ob = null;
        try {
            ob = new JSONParser().parse(new FileReader("JSON.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // typecasting ob to JSONObject
        JSONObject json = (JSONObject) ob;
        this.url = (String) json.get("url");
        this.name = (String) json.get("name");
        this.userName = (String) json.get("user");
        this.password = (String) json.get("password");
    }
    public String getConnectionUrl() {
        return url + name;
    }
}
