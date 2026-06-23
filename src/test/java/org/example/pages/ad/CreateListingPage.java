package org.example.pages.ad;

import io.qameta.allure.Step;
import org.example.data.Ad;
import org.example.pages.BasePage;
import org.example.pages.user.HomeAuthPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateListingPage extends BasePage {
    protected final By newAd = By.xpath("//h1[text()='Новое объявление']");
    protected final By uploadPhotoButton = By.xpath("//button[.//input[@name='img1']]");
    protected final By submitButton = By.cssSelector("[type='submit']");

    public CreateListingPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the page (CreateListingPage)")
    public void checkCreateListingPage() {
        waitLocator(newAd);
    }



    @Step("Enter full data for ad")
    public void enterFullData(Ad ad){
        enterImg("img1", ad.getImg1());
        enterImg("img2", ad.getImg2());
        enterImg("img3", ad.getImg3());
        enterData(ad);
    }

    @Step("Enter data with one image for ad")
    public void enterDataWithOneImg(Ad ad, String imgField){
        enterImg(imgField, ad.getImg1());
        enterData(ad);
    }


    @Step("Click on the button (publish)")
    public HomeAuthPage clickPublishButton() {
        scrollToLocator(submitButton);
        waitLocator(submitButton);
        waitClickable(submitButton).click();

        return new HomeAuthPage(driver);
    }
}
