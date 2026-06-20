package org.example.steps.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.tests.TestContext;
import org.example.utils.Hooks;


public class RegisterSteps {
    protected TestContext context = Hooks.context;

    @When("User opens registration form")
    public void openRegistrationForm() {
        context.homePage.clickAccountButton();
        context.homePage.clickNoAccount();
    }

    @And("User enters valid registration data")
    public void enterRegistrationData() {
        context.homePage.enterUserData(context.user);
    }

    @And("User clicks Create Account button")
    public void clickCreateAccount() throws InterruptedException {
        context.homeAuthPage = context.homePage.clickCreateAccountButton();
    }

    @And("User clicks Enter button for registration and stay")
    public void clickCreateAccountWithoutGoing() throws InterruptedException {
        context.homePage.clickCreateAccountButtonAndStay();
    }


    @When("User enters registration data with wrong repeating password")
    public void enterUserWithWrongPassword() {
        context.homePage.enterWrongRepeatPassword(context.user);
    }

    @Then("User should see registration error")
    public void checkUserError() {
        context.homePage.checkRegisterError();
    }

    @Then("User should see registration error the passwords are different")
    public void checkPasswordError() {
        context.homePage.checkPasswordsAreDifferent();
    }

}
