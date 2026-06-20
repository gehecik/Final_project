package org.example.tests.user;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.example.api.BaseApiSteps;
import org.example.data.User;
import org.example.pages.HomePage;
import org.example.api.ActionsUser;
import org.example.utils.DriverExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import java.net.HttpURLConnection;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static org.example.utils.EnvConfig.BASE_URL;

@Slf4j
public class RegisterSeleniumTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();
    ActionsUser actionsUser = new ActionsUser();


//    @Test
//    public void createUser1() {
//        RestAssured.baseURI = BASE_URL;
//        User user = User.userWithRandomField();
//        Response response = actionsUser.createUser(user);
//        BaseApiSteps.checkStatusCode(response, HttpURLConnection.HTTP_CREATED);
//        int id = (int) BaseApiSteps.getValue(response, "user.id");
//        String token = (String) BaseApiSteps.getValue(response, "access_token.access_token");
//        Response response2 = actionsUser.loginUser(user);
//        String token2 = (String) BaseApiSteps.getValue(response2, "token.access_token");
//        System.out.println(token);
//        System.out.println(token2);
//        //actionsUser.deleteUser(token, id);
//    }
//
//    @Test
//    @DisplayName("Successful registration")
//    @Description("Successful registration")
//    public void createUser() throws InterruptedException {
//        WebDriver driver = extension.getDriver();
//        User user = User.userWithRandomField();
//
//        var homePage = new HomePage(driver);
//
//        homePage.openPage();
//        homePage.clickAccountButton();
//        homePage.clickNoAccount();
//        homePage.enterUserData(user);
//        var homeAuthPage = homePage.clickCreateAccountButton();
//        homeAuthPage.checkContent();
//    }
//
//    @Test
//    @DisplayName("Registration with exist user")
//    @Description("Registration with exist user")
//    public void createExistUser() throws InterruptedException {
//        WebDriver driver = extension.getDriver();
//        User user = User.userWithRandomField();
//
//        var homePage = new HomePage(driver);
//
//        homePage.openPage();
//        homePage.clickAccountButton();
//        homePage.clickNoAccount();
//        homePage.enterUserData(user);
//        var homeAuthPage = homePage.clickCreateAccountButton();
//        homeAuthPage.logoutUser();
//
//        homePage.clickAccountButton();
//        homePage.clickNoAccount();
//        homePage.enterUserData(user);
//        homePage.clickCreateAccountButton();
//        homePage.checkError();
//    }
//
//    @Test
//    @DisplayName("Registration without password")
//    @Description("Registration without password")
//    public void createUserWithoutPassword() throws InterruptedException {
//        WebDriver driver = extension.getDriver();
//        User user = User.userWithRandomField();
//
//        var homePage = new HomePage(driver);
//
//        homePage.openPage();
//        homePage.clickAccountButton();
//        homePage.clickNoAccount();
//        homePage.enterEmail(user);
//        var homeAuthPage = homePage.clickCreateAccountButton();
//        homeAuthPage.checkContent();
//    }
//
//    @Test
//    @DisplayName("Registration without repeat password")
//    @Description("Registration without repeat password")
//    public void createUserWithoutRepeatPassword() throws InterruptedException {
//        WebDriver driver = extension.getDriver();
//        User user = User.userWithRandomField();
//
//        var homePage = new HomePage(driver);
//
//        homePage.openPage();
//        homePage.clickAccountButton();
//        homePage.clickNoAccount();
//        homePage.enterEmailAndPassword(user);
//        homePage.clickCreateAccountButton();
//        homePage.checkPasswordsAreDifferent();
//    }
//
//    @Test
//    @DisplayName("Registration with wrong repeat password")
//    @Description("Registration with wrong repeat password")
//    public void createUserWithWrongRepeatPassword() throws InterruptedException {
//        WebDriver driver = extension.getDriver();
//        User user = User.userWithRandomField();
//
//        var homePage = new HomePage(driver);
//
//        homePage.openPage();
//        homePage.clickAccountButton();
//        homePage.clickNoAccount();
//        homePage.enterWrongRepeatPassword(user);
//        homePage.clickCreateAccountButton();
//        homePage.checkPasswordsAreDifferent();
//    }
}
