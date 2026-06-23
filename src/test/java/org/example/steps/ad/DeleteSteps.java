package org.example.steps.ad;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.tests.TestContext;
import org.example.utils.Hooks;

public class DeleteSteps {
    private TestContext context() {
        return Hooks.context;
    }

    @When("User opens ad card")
    public void openCard() throws InterruptedException {
        context().homeAuthPage.checkAd(context().ad, false);
        context().adPage = context().homeAuthPage.clickCard();
    }

    @When("User clicks Delete button")
    public void deleteAd() {
        context().homeAuthPage = context().adPage.deleteAdFromCard();
    }
    @Then("Ad should be deleted")
    public void checkDeletingAd() throws InterruptedException {
        context().homeAuthPage.checkAdDeleted(context().ad);
    }
}
