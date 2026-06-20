package org.example.steps.ad;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.data.Ad;
import org.example.tests.TestContext;
import org.example.utils.Hooks;

import java.util.function.Consumer;

public class ChangeSteps {
    protected TestContext context = Hooks.context;

    @When("User clicks edit button")
    public void clickEditButton() throws InterruptedException {
        context.editListingPage = context.homeAuthPage.clickEdit();
        context.adId = context.editListingPage.getAdIdFromURL();
    }

    @When("User changes fields and deleting one of the {string}")
    public void changeFields(String image) {
        Ad newAd = Ad.adNewData();
        changeAdFields(newAd);
        context.homeAuthPage = context.editListingPage.enterNewData(newAd, image);
    }

    @Then("Ad should be changed and the {string} is deleted")
    public void checkChangeFields(String image) throws InterruptedException {
        System.out.println("Ad should be changed");
        context.homeAuthPage.checkAd(context.ad, false);
        context.editListingPage = context.homeAuthPage.clickEdit();
        context.editListingPage.checkNewAdImgIsDeleted(image);
        context.homePage = context.homeAuthPage.logoutUser();
        context.adPage = context.homePage.openAdPage(context.ad, context.adId);
        context.adPage.checkNewAdWithoutImg(context.ad);
    }

    public void changeAdFields(Ad newAd) {
        updateIfNotNull(newAd.getName(), context.ad::setName);
        updateIfNotNull(newAd.getCategory(), context.ad::setCategory);
        updateIfNotNull(newAd.getCondition(), context.ad::setCondition);
        updateIfNotNull(newAd.getCity(), context.ad::setCity);
        updateIfNotNull(newAd.getDescription(), context.ad::setDescription);
        updateIfNotNull(newAd.getCost(), context.ad::setCost);
        updateIfNotNull(newAd.getImg1(), context.ad::setImg1);
        updateIfNotNull(newAd.getImg2(), context.ad::setImg2);
        updateIfNotNull(newAd.getImg3(), context.ad::setImg3);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
