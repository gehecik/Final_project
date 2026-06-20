package org.example.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateAd {
    @Step("Create ad")
    public Response createAdApi(String userToken, Object ad) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + userToken)
                .body(ad)
                .post("/api/create-listing")
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Step("Get ad by accessToken")
    public Response getAd(String accessToken, int id) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .pathParam("id", id)
                .get("/api/edit-listing/{id}")
                .then()
                .log().all()
                .extract()
                .response();
    }
}
