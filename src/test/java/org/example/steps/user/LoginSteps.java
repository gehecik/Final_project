package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.HomePage;
import org.example.tests.TestContext;
import org.example.utils.Hooks;

public class LoginSteps {
    protected TestContext context = Hooks.context;

    @When("User opens login form")
    public void openLoginForm() {
        context.homePage.clickAccountButton();
    }

    @When("User clicks Enter button")
    public void clickEnterButton() {
        context.homeAuthPage = context.homePage.clickEnter();
    }

    @When("User clicks Enter button without going to AuthPage")
    public void clickEnterButtonAndStay() {
        context.homePage.clickEnter();
    }

    @When("User enters email and wrong password")
    public void enterUserEmailAndWrongPassword() {
        context.homePage.enterEmailAndWrongPassword(context.user);
    }

    @Then("User should see login error")
    public void checkUserLoginError() {
        context.homePage.checkLoginError();
    }
}
