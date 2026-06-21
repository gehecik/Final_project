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
    public void clickEditButton() {
        System.out.println("User clicks edit button");
        context.editListingPage = context.homeAuthPage.clickEdit();
        context.adId = context.editListingPage.getAdIdFromURL();
    }

    @When("User changes fields and deleting one of the {string}")
    public void changeFields(String image) {
        System.out.println("User changes fields and deleting one of the");
        Ad newAd = Ad.adNewData();
        changeAdFields(newAd);
        changeAdImage(image, null);
        context.homeAuthPage = context.editListingPage.enterNewData(newAd, image);
    }

    @Then("Ad should be changed and the {string} is deleted")
    public void checkChangeFields(String image) throws InterruptedException {
        System.out.println("Ad should be changed and the is deleted");
        context.homeAuthPage.checkAd(context.ad, false);
        context.adPage = context.homeAuthPage.clickCard();
        context.adPage.checkNewAd(context.ad);
    }


    @When("User changes one of the {string}")
    public void changeOneImage(String image) throws InterruptedException {
        String pathImg = "src/test/resources/images/img_4.jpg";
        System.out.println("User changes one of the image");
        changeAdImage(image, pathImg);
        context.homeAuthPage = context.editListingPage.enterOneImage(image);

    }
    @Then("{string} of the ad should be changed")
    public void checkOneImage(String image) throws InterruptedException {
        System.out.println("Image of the ad should be changed");
        //Thread.sleep(1000);
        context.homeAuthPage.checkAd(context.ad, false);
        context.adPage = context.homeAuthPage.clickCard();
        System.out.println(context.ad);
        context.adPage.checkNewAd(context.ad);
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

    public void changeAdImage(String image, String value) {
        if (image.equals("img1")) {
            context.ad.setImg1(value);
        } else if (image.equals("img2")) {
            context.ad.setImg2(value);
        } else if (image.equals("img3")) {
            context.ad.setImg3(value);
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
