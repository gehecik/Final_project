package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.data.User;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static org.example.utils.EnvConfig.BASE_URL;

public class RegisterTest {
    ActionsUser actionsUser = new ActionsUser();
    @Test
    public void createUser() {
        RestAssured.baseURI = BASE_URL;
        User user = User.userWithRandomField();
        Response response = actionsUser.createUser(user);
        checkStatusCode(response, HttpURLConnection.HTTP_CREATED);
        int id = (int) getValue(response, "user.id");
        String token = (String) getValue(response, "access_token.access_token");
        Response response2 = actionsUser.loginUser(user);
        String token2 = (String) getValue(response2, "token.access_token");
        System.out.println(token);
        System.out.println(token2);
        //actionsUser.deleteUser(token, id);
    }
    public void checkStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }
    public Object getValue(Response response, String name) {
        return response.then().extract().path(name);
    }

}
