package org.example.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ActionsUser {
    @Step("Create user")
    public Response createUser(Object user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("Create user and get accessToken")
    public String createUserGetToken(Object user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .extract()
                .path("access_token.access_token");
    }

    @Step("Login user")
    public Response loginUser(Object user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/signin")
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("Delete user by accessToken")
    public Response deleteUser(String accessToken, int id) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .pathParam("id", id)
                .post("/api/delete-user/{id}")
                .then()
                .log().all()
                .extract()
                .response();
    }

}
