package org.example.pages.ad;

import io.qameta.allure.Step;
import org.example.data.Ad;
import org.example.pages.BasePage;
import org.example.pages.user.HomeAuthPage;
import org.example.tests.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditListingPage extends BasePage {
    protected final By changeButton = By.xpath("//button[text()='Сохранить изменения']");

    public EditListingPage(WebDriver driver) {
        super(driver);
    }

    public int getAdIdFromURL() {
        String curURL = driver.getCurrentUrl();
        int curAdId = Integer.parseInt(curURL.substring(curURL.lastIndexOf('/') + 1));

        return curAdId;
    }

    @Step("Enter full data for ad")
    public HomeAuthPage enterNewData(Ad newAd, String imgField){
        System.out.println("Enter full data for ad");
        deleteImg(imgField);
        //enterImg(imgField, newAd.getImg1());
        enterData(newAd);
        scrollToLocator(changeButton);
        waitClickable(changeButton).click();

        return new HomeAuthPage(driver);
    }
    @Step("Change one image for ad")
    public HomeAuthPage enterOneImage(String image) throws InterruptedException {
        inputImg(image);
        scrollToLocator(changeButton);
        waitClickable(changeButton).click();

        return new HomeAuthPage(driver);
    }

    public void deleteImg(String imgField) {
        scrollToTop();
        By divImg =  By.xpath("//div[contains(@class,'upload_editContainer__NMV1M')]" +
                        "[.//input[contains(@name,'" + imgField + "')]]");
        scrollToLocator(divImg);


        WebElement el = presenceLocator(divImg);
        By deleteButton = By.className("trashButtonNoHover");
        el.findElement(deleteButton).click();
    }

    public void inputImg(String imgField) {
        scrollToTop();
        By divImg =  By.xpath("//div[contains(@class,'upload_editContainer__NMV1M')]" +
                "[.//input[contains(@name,'" + imgField + "')]]");
        scrollToLocator(divImg);


        WebElement el = presenceLocator(divImg);
        enterImg(imgField, "src/test/resources/images/img_4.jpg");
    }

    public void checkNewAdImgIsDeleted(String imgField) {
        scrollToTop();
        By buttonImg =  By.xpath("//button[contains(@class,'upload_upload__skfWd')]" +
                "[.//input[contains(@name,'" + imgField + "')]]");
        scrollToLocator(buttonImg);

        WebElement el = presenceLocator(buttonImg);
        WebElement spanEl = el.findElement(
                By.xpath(".//span[contains(text(),'Добавить фото')]"));

        assertTrue(spanEl.getText().contains("Добавить фото"));
    }
    public void checkNewImg(String imgField) {
        scrollToTop();
        By divImg =  By.xpath("//div[contains(@class,'upload_editContainer__NMV1M')]" +
                "[.//input[contains(@name,'" + imgField + "')]]" +
                "/div[contains(@class,'upload_edit__YJ8yi')]");
        System.out.println(divImg);
        scrollToLocator(divImg);
        WebElement el = presenceLocator(divImg);
        System.out.println(el);
        String actualSrc = el.findElement(divImg).getAttribute("style");
        System.out.println(actualSrc);

        assertTrue(actualSrc.contains("img_4.jpg"));
    }

}
