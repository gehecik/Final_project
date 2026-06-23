package org.example.steps.ad;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.data.Ad;
import org.example.tests.TestContext;
import org.example.utils.Hooks;

import java.util.function.Consumer;

public class ChangeSteps {
    private TestContext context() {
        return Hooks.context;
    }

    @When("User clicks edit button")
    public void clickEditButton() throws InterruptedException {
        context().homeAuthPage.checkAd(context().ad, false);
        context().editListingPage = context().homeAuthPage.clickEdit();
        context().adId = context().editListingPage.getAdIdFromURL();
    }

    @When("User clicks edit button from ad card")
    public void clickEditButtonFromCard() throws InterruptedException {
        context().homeAuthPage.checkAd(context().ad, false);
        context().adPage = context().homeAuthPage.clickCard();
        context().editListingPage = context().adPage.openEditPageFromCard();
        context().adId = context().editListingPage.getAdIdFromURL();
    }

    @When("User changes fields and deleting one of the {string}")
    public void changeFields(String image) {
        Ad newAd = Ad.adNewData();
        changeAdFields(newAd);
        changeAdImage(image, null);
        context().homeAuthPage = context().editListingPage.enterNewData(newAd, image);
    }

    @Then("Ad should be changed and the {string} is deleted")
    public void checkChangeFields(String image) throws InterruptedException {
        context().homeAuthPage.checkAd(context().ad, false);
        context().adPage = context().homeAuthPage.clickCard();
        context().adPage.checkNewAd(context().ad);

        context().editListingPage = context().adPage.openEditPageFromCard();
        context().homeAuthPage = context().editListingPage.clickSaveChangeButton();
    }


    @When("User changes one of the {string}")
    public void changeOneImage(String image) throws InterruptedException {
        String pathImg = "src/test/resources/images/img_4.jpg";
        changeAdImage(image, pathImg);
        context().editListingPage.enterOneImage(image);
        context().homeAuthPage = context().editListingPage.clickSaveChangeButton();
    }
    @Then("{string} of the ad should be changed")
    public void checkOneImage(String image) throws InterruptedException {
        context().homeAuthPage.checkAd(context().ad, false);
        context().adPage = context().homeAuthPage.clickCard();
        System.out.println(context().ad);
        context().adPage.checkNewAd(context().ad);

        context().editListingPage = context().adPage.openEditPageFromCard();
        context().homeAuthPage = context().editListingPage.clickSaveChangeButton();
    }

    public void changeAdFields(Ad newAd) {
        updateIfNotNull(newAd.getName(), context().ad::setName);
        updateIfNotNull(newAd.getCategory(), context().ad::setCategory);
        updateIfNotNull(newAd.getCondition(), context().ad::setCondition);
        updateIfNotNull(newAd.getCity(), context().ad::setCity);
        updateIfNotNull(newAd.getDescription(), context().ad::setDescription);
        updateIfNotNull(newAd.getCost(), context().ad::setCost);
        updateIfNotNull(newAd.getImg1(), context().ad::setImg1);
        updateIfNotNull(newAd.getImg2(), context().ad::setImg2);
        updateIfNotNull(newAd.getImg3(), context().ad::setImg3);
    }

    public void changeAdImage(String image, String value) {
        if (image.equals("img1")) {
            context().ad.setImg1(value);
        } else if (image.equals("img2")) {
            context().ad.setImg2(value);
        } else if (image.equals("img3")) {
            context().ad.setImg3(value);
        } else {
            throw new IllegalArgumentException("Неизвестное поле картинки: " + image);
        }
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
