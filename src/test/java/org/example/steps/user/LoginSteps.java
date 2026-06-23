package org.example.steps.user;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.tests.TestContext;
import org.example.utils.Hooks;

public class LoginSteps {
    private TestContext context() {
        return Hooks.context;
    }

    @When("User clicks Enter button and stay")
    public void clickEnterButtonAndStay() {
        context().homePage.clickEnter();
    }

    @When("User enters email and wrong password")
    public void enterUserEmailAndWrongPassword() {
        context().homePage.enterEmailAndWrongPassword(context().user);
    }

    @Then("User should see login error")
    public void checkUserLoginError() {
        context().homePage.checkLoginError();
    }
}
