package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeAuthPage extends BasePage {
    protected final By exitButton = By.xpath("//button[text()='Выйти']");

    public HomeAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the button (exit)")
    public void checkContent() {
        waitLocator(exitButton);
    }

    @Step("Click the button (exit)")
    public void logoutUser() {
        waitLocator(exitButton);
        waitClickable(exitButton).click();
    }

}
