package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.data.Ad;
import org.example.pages.BasePage;
import org.example.pages.HomePage;
import org.example.pages.ad.CreateListingPage;
import org.example.pages.ad.EditListingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomeAuthPage extends BasePage {
    protected final By exitButton = By.xpath("//button[text()='Выйти']");
    protected final By adButton = By.xpath("//button[text()='Разместить объявление']");
    protected final By editLocator = By.className("editButton");

    WebElement card;

    public HomeAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the button (exit)")
    public void checkContent() {
        waitLocator(exitButton);
    }

    @Step("Click the button (exit)")
    public HomePage logoutUser() {
        waitLocator(exitButton);
        waitClickable(exitButton).click();

        return new HomePage(driver);
    }

    @Step("Click on the button (place an ad)")
    public CreateListingPage clickCreateAdButton() {
        waitLocator(adButton);
        WebElement el = waitClickable(adButton);
        el.click();

        return new CreateListingPage(driver);
    }

    @Step("Check the ad")
    public void checkAd(Ad ad, boolean checkImage) {
            By cardLocator = getCardLocator(ad, true);
            System.out.println(cardLocator);

            int attempts = 1;
            int maxAttempts = getMaxAttempts(bar);

            while (attempts <= maxAttempts) {
                if (!driver.findElements(cardLocator).isEmpty()) {
                    card = waitClickable(cardLocator);
                    if (checkImage) {
                        checkImg(card, ad);
                    }
                    checkCost(card, ad);
                    checkEdit(card, ad, editLocator);

                    return;
                }
                presenceLocator(bar);
                scrollToLocator(bar);
                if (driver.findElements(arrowButton).isEmpty()) {
                    throw new AssertionError("Кнопка переключения не найдена");
                }

                WebElement next = presenceLocator(arrowButton);
                String oldPage = presenceLocator(bar).getText();
                String oldAd = presenceLocator(adsList).getText();

                if (next.isEnabled()) {
                    next.click();
                }
                reloadPage(bar, oldPage);
                reloadPage(adsList, oldAd);

                attempts++;
            }
            throw new AssertionError("Объявление '" + ad.getName() + "' отсутствует");
    }

    @Step("Click the button (edit)")
    public EditListingPage clickEdit() throws InterruptedException {
        System.out.println("Click the button (edit)");
        System.out.println(card);
        Thread.sleep(10000);
        WebElement el = card.findElement(editLocator);
        System.out.println(el);
        el.click();

        return new EditListingPage(driver);
    }
}
