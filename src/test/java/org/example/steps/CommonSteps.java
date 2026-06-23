package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.ActionsUser;
import org.example.api.BaseApiSteps;
import org.example.api.CreateAd;
import org.example.data.Ad;
import org.example.data.User;
import org.example.pages.HomePage;
import org.example.tests.TestContext;
import org.example.utils.DriverManager;
import org.example.utils.Hooks;

import java.net.HttpURLConnection;

import static org.example.data.Ad.adWithOneImg;

public class CommonSteps {
    //protected TestContext context = Hooks.context;
    private TestContext context() {
        return Hooks.context;
    }
    
    private final ActionsUser actionsUser = new ActionsUser();
    private final CreateAd createAd = new CreateAd();

    @Given("User opens home page")
    public void openHomePage() {
        context().driver = DriverManager.getDriver();
        context().homePage = new HomePage(context().driver);
        context().homePage.openPage();
    }

    @Given("User exists")
    public void userExists() {
        Response response = actionsUser.createUser(context().user);
        BaseApiSteps.checkStatusCode(response, HttpURLConnection.HTTP_CREATED);
        context().userCreateToken = (String) BaseApiSteps.getValue(response, "access_token.access_token");
        context().userId = (int) BaseApiSteps.getValue(response, "user.id");

        Response loginResponse = actionsUser.loginUser(context().user);
        BaseApiSteps.checkStatusCode(loginResponse, HttpURLConnection.HTTP_CREATED);
        context().userLoginToken = (String) BaseApiSteps.getValue(loginResponse, "token.access_token");
    }

    @When("User enters email and password")
    public void enterUserEmailAndPassword() {
        context().homePage.enterEmailAndPassword(context().user);
    }

    @Then("User should be authorized")
    public void checkUserAuthorized() {
        context().homeAuthPage.checkContent();
    }

    @When("User enters email only")
    public void enterUserEmail() {
        context().homePage.enterEmail(context().user);
    }

    @When("User enters wrong email")
    public void enterWrongEmail() {
        User wrongUser = User.userWithNonExist();
        context().homePage.enterEmailAndPassword(wrongUser);
    }

    @When("User opens login form")
    public void openLoginForm() {
        context().homePage.clickAccountButton();
    }

    @When("User clicks Enter button")
    public void clickEnterButton() {
        context().homeAuthPage = context().homePage.clickEnter();
    }

    @Given("User is logged in")
    public void userLoggedIn() {
        userExists();
        openHomePage();
        openLoginForm();
        enterUserEmailAndPassword();
        clickEnterButton();
        checkUserAuthorized();
    }

    @When("User clicks Create an ad button without login")
    public void clickCreateAdButtonWithoutLogin() {
        context().homePage.clickCreateAdButtonHomePage();
    }

    @Given("User clicks Create an ad button")
    public void clickCreateAdButton() {
        context().createListingPage = context().homeAuthPage.clickCreateAdButton();
    }

    @When("User opens ad form")
    public void openAdForm() {
        context().createListingPage.checkCreateListingPage();
    }

    @When("User enters data with three image")
    public void enterAdDataWithThreeImg() {
        context().ad = Ad.adWithFullData();
        context().createListingPage.enterFullData(context().ad);
    }

    @When("User clicks Submit button")
    public void clickSubmitButton() {
        context().homeAuthPage = context().createListingPage.clickPublishButton();
    }

    @Then("Ad should be created")
    public void checkAd() throws InterruptedException {
        boolean checkImage = true;

        if (context().ad == null) {
            context().ad = Ad.adDefault();
        }

        if (context().ad != null && context().ad.getImg1() == null) {
            checkImage = false;
        }

        context().homeAuthPage.checkAd(context().ad, checkImage);
    }

    @Given("Ad is created")
    public void adExists() throws InterruptedException {
        clickCreateAdButton();
        openAdForm();
        enterAdDataWithThreeImg();
        clickSubmitButton();
    }


}
