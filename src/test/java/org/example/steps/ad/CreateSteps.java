package org.example.steps.ad;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.data.Ad;
import org.example.tests.TestContext;
import org.example.utils.Hooks;

public class CreateSteps {
    protected TestContext context = Hooks.context;


    @When("User enters data {string} {string} {string}")
    public void enterAdData(String category, String condition, String city) {
        context.ad = Ad.adWithDiffValue(category, condition, city);
        context.createListingPage.enterData(context.ad);
    }

    @When("User enters data with one {string}")
    public void enterAdDataWithOneImg(String image) {
        context.ad = Ad.adWithOneImg();
        context.createListingPage.enterDataWithOneImg(context.ad, image);
    }

    @Then("User should be see creating error")
    public void checkCreateError() {
        context.homePage.checkCreateError();
    }
}
