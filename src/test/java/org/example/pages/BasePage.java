package org.example.pages;

import io.qameta.allure.Step;
import org.example.data.Ad;
import org.example.data.AdCard;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

import static org.example.utils.EnvConfig.EXPLICIT_TIMEOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    protected final By inputName = By.cssSelector("[placeholder='Название']");
    protected final By categoryDropdown = By.xpath("//input[@name='category']/following-sibling::button");
    protected final By cityDropdown = By.xpath("//input[@name='city']/following-sibling::button");
    protected final By inputDescription = By.cssSelector("[placeholder='Описание товара']");
    protected final By inputCost = By.cssSelector("[placeholder='Стоимость']");
    protected final By radioButton = By.className("createListing_inputRadioMobile__Oit+g");
    protected final By bar = By.xpath("//p[@class='spanGlobal']");
    protected final By arrowButton = By.xpath("//button[contains(@class,'arrowButton--right') and contains(@class,'undefined')]");
    protected final By adsList = By.xpath("//div[@class='grid_twoColumns__HwA+w']//h2");

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLocator(By locator) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitURL(String url) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.urlContains(url));
    }

    public void reloadPage(By locator, String oldValue) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, oldValue)));
    }

    public WebElement presenceLocator(By locator) {
        return new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitClickable(By locator) {
        return new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, 0);");
    }
    public void scrollToLocator(By locator) {
        WebElement element = new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void enterNewValue(By locator, String newValue) {
        waitClickable(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(newValue);
    }

    public void  inputValueOnly(By locator, String newValue) {
        driver.findElement(locator).sendKeys(newValue);
    }


    public void checkValue(By locator, String item) {
            new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.attributeToBe(locator, "value", item));
    }

    public void setInput(By locator, String value) {
        waitLocator(locator);
        enterNewValue(locator, value);
    }

    public void checkImg(AdCard card, Ad ad) {
        By imageLocator = By.xpath(".//img");
        //String actualSrc = card.findElement(imageLocator).getAttribute("src");
        String actualSrc = card.getImgSrc();
        String expectedImg = null;

        if (ad.getImg1() != null && !ad.getImg1().isBlank()) {
            expectedImg = ad.getImg1();
        } else if (ad.getImg2() != null && !ad.getImg2().isBlank()) {
            expectedImg = ad.getImg2();
        } else if (ad.getImg3() != null && !ad.getImg3().isBlank()) {
            expectedImg = ad.getImg3();
        }
        if (expectedImg == null) {
            assertTrue(actualSrc.contains("notstirng"));
        } else {
            String fileName = expectedImg.substring(
                    expectedImg.lastIndexOf('/') + 1
            );
            assertTrue(actualSrc.contains(fileName));
        }
    }

    public void checkCost(AdCard card, Ad ad) {
       assertEquals(String.valueOf(Integer.parseInt(ad.getCost())),card.getCost());
    }

    public void checkEdit(AdCard card) {
        assertTrue(card.getButton().getAttribute("class").contains("editButton"));
    }


    protected By getCardLocator(Ad ad, boolean auth) {
        if (!auth) {
            return By.xpath(
                    "//div[contains(@class,'card')]" +
                            "[.//div[contains(@class,'about')]/h2[text()='" + ad.getName() + "']]" +
                            "[.//div[contains(@class,'about')]/h3[text()='" + ad.getCity() + "']]"
            );
        }
        else if (ad.getName() == null || ad.getName().isBlank()) {
            return By.xpath(
                    "//div[contains(@class,'card')]" +
                            "[.//div[contains(@class,'about')]/h3[text()='" + ad.getCity() + "']]" +
                            "[.//button[contains(@class,'editButton')]]"
            );
        }
        return By.xpath(
                "//div[contains(@class,'card')]" +
                        "[.//div[contains(@class,'about')]/h2[text()='" + ad.getName() + "']]" +
                        "[.//div[contains(@class,'about')]/h3[text()='" + ad.getCity() + "']]" +
                        "[.//button[contains(@class,'editButton')]]"
        );
    }

    @Step("Enter ad's data")
    public void enterData(Ad ad) {
        scrollToLocator(inputName);
        enterName(ad.getName(), inputName);

        scrollToLocator(categoryDropdown);
        selectDropdown("category", ad.getCategory());

        scrollToLocator(radioButton);
        selectRadioButton(ad.getCondition());

        scrollToLocator(cityDropdown);
        selectDropdown("city", ad.getCity());

        scrollToLocator(inputDescription);
        enterDescription(ad.getDescription(), inputDescription);

        scrollToLocator(inputCost);
        enterCost(ad.getCost(), inputCost);
    }

    public void enterImg(String elImg, String path) {
        By inputImg = By.cssSelector("input[name='" + elImg + "']");
        String filePath = Paths.get(path).toAbsolutePath().toString();
        scrollToLocator(inputImg);
        inputValueOnly(inputImg, filePath);
    }

    public void enterName(String value, By locator) {
        enterNewValue(locator, value);
    }

    public void enterDescription(String value, By locator) {
        enterNewValue(locator, value);
    }

    public void enterCost(String value, By locator) {
        enterNewValue(locator, value);
    }

    public void selectRadioButton(String value) {
        By inputRadioButton = By.cssSelector("input[value='" + value + "']");
        By radioButton = By.xpath("//input[@value='" + value +
                "']/parent::div/div");
        WebElement el = presenceLocator(inputRadioButton);
        waitLocator(radioButton);
        WebElement elDiv = waitClickable(radioButton);
        if (elDiv.getAttribute("class").equals("radioUnput_inputRegular__FbVbr")) {
            elDiv.click();
        }

    }

    public void selectDropdown(String name, String dropdown) {
        By itemDropdown = By.xpath("//span[text()='" + dropdown + "']");
        By nameDropdown = By.xpath("//input[@name='" + name + "']/following-sibling::button");
        By input = By.xpath("//input[@name='" + name + "']");

        waitLocator(nameDropdown);
        waitClickable(nameDropdown).click();

        waitLocator(itemDropdown);
        waitClickable(itemDropdown).click();

        checkValue(input, dropdown);
    }


    public int getMaxAttempts(By locator) {
        WebElement el = presenceLocator(locator);
        String text = el.getText();
        return Integer.parseInt(text.split(" ")[2]);
    }

}
