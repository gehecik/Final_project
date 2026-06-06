package org.example.api;

import io.restassured.response.Response;

public class BaseSteps {
    public static void checkStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }
    public static Object getValue(Response response, String name) {
        return response.then().extract().path(name);
    }
}
