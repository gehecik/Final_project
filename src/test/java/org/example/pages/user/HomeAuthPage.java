package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    protected final By exitButton = By.xpath("//button[text()='Выйти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the button (exit)")
    public void checkStatusContent() {
        waitLocator(exitButton);
    }

}
