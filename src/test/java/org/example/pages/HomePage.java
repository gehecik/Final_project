package org.example.pages;

import io.qameta.allure.Step;
import org.example.data.Ad;
import org.example.data.User;
import org.example.pages.ad.AdPage;
import org.example.pages.ad.CreateListingPage;
import org.example.pages.user.HomeAuthPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.example.utils.EnvConfig.BASE_URL;

public class HomePage extends BasePage {
    protected final By accountButton = By.xpath("//button[text()='Вход и регистрация']");
    protected final By noAccountButton = By.xpath("//button[text()='Нет аккаунта']");
    protected final By enterButton = By.xpath("//button[text()='Войти']");
    protected final By inputEmail = By.cssSelector("[placeholder='Введите Email']");
    protected final By inputPassword = By.cssSelector("[placeholder='Пароль']");
    protected final By inputRepeatPassword = By.cssSelector("[placeholder='Повторите пароль']");
    protected final By createAccountButton = By.xpath("//button[text()='Создать аккаунт']");
    protected final By error = By.xpath("//span[@class='input_span__yWPqB' and text()='Ошибка']");
    protected final By dontMatch = By.xpath("//span[@class='input_span__yWPqB' and text()='Пароли не совпадают']");
    protected final By errorLogin = By.xpath("//span[@class='input_span__yWPqB' and text()='Логин или пароль неверны']");
    protected final By errorCreate = By.xpath("//h1[text()='Чтобы разместить объявление, авторизуйтесь']");
    protected final By adButton = By.xpath("//button[text()='Разместить объявление']");

    //WebElement card;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(BASE_URL);
    }

    @Step("Click on the button (login and registration)")
    public void clickAccountButton() {
        waitLocator(accountButton);
        waitClickable(accountButton).click();
    }

    @Step("Click on the button (no account)")
    public void clickNoAccount() {
        waitLocator(noAccountButton);
        waitClickable(noAccountButton).click();
    }

    @Step("Click on the button (enter)")
    public HomeAuthPage clickEnter() {
        waitLocator(enterButton);
        waitClickable(enterButton).click();

        return new HomeAuthPage(driver);
    }

    @Step("Input user's fields")
    public void enterUserData(User user) {
        enterNewValue(inputEmail, user.getEmail());
        enterNewValue(inputPassword, user.getPassword());
        enterNewValue(inputRepeatPassword, user.getPassword());
    }

    @Step("Input user's email")
    public void enterEmail(User user) {
        enterNewValue(inputEmail, user.getEmail());
    }

    @Step("Input user's email and password")
    public void enterEmailAndPassword(User user) {
        enterNewValue(inputEmail, user.getEmail());
        enterNewValue(inputPassword, user.getPassword());
    }

    @Step("Input user's email and wrong password")
    public void enterEmailAndWrongPassword(User user) {
        enterNewValue(inputEmail, user.getEmail());
        enterNewValue(inputPassword, "wrongPassword");
    }

    @Step("Input wrong repeat password")
    public void enterWrongRepeatPassword(User user) {
        enterNewValue(inputEmail, user.getEmail());
        enterNewValue(inputPassword, user.getPassword());
        enterNewValue(inputRepeatPassword, "wrongPassword");
    }

    @Step("Click on the button (create account)")
    public HomeAuthPage clickCreateAccountButton() {
        waitLocator(createAccountButton);
        waitClickable(createAccountButton).click();

        return new HomeAuthPage(driver);
    }


    @Step("Click on the button (create account) and stay homePage")
    public void clickCreateAccountButtonAndStay() {
        waitLocator(createAccountButton);
        waitClickable(createAccountButton).click();
    }

    public void checkRegisterError() {
        waitLocator(error);
    }

    public void checkCreateError() {
        waitLocator(errorCreate);
    }

    public void checkLoginError() {
        waitLocator(errorLogin);
    }

    public void checkPasswordsAreDifferent() {
        waitLocator(dontMatch);
    }


    @Step("Click on the button (place an ad) without login")
    public void clickCreateAdButtonHomePage() {
        waitLocator(adButton);
        WebElement el = waitClickable(adButton);
        el.click();
    }
}
