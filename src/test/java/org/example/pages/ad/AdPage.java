package org.example.pages.ad;

import org.example.data.Ad;
import org.example.pages.BasePage;
import org.example.pages.user.HomeAuthPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdPage extends BasePage {
    protected final By categoryCondition = By.className("listing_categoryColor__Zj1m4");
    protected final By name = By.className("h1Mobile");
    protected final By description = By.xpath("//p[@class='spanGlobal']");
    protected final By city = By.xpath("//h3[@class='subtitle']");
    protected final By editAd = By.xpath("//button[text()='Редактировать объявление']");
    protected final By deleteAd = By.xpath("//button[text()='Удалить']");

    public AdPage(WebDriver driver) {
        super(driver);
    }

    public void checkNewAd(Ad ad) {
        scrollToTop();
        checker(categoryCondition, ad.getCategory());
        checker(categoryCondition, ad.getCondition());
        checker(name, ad.getName());
        checker(description, ad.getDescription());
        checker(city, ad.getCity());
        checkerForImg(ad);
    }

    public EditListingPage openEditPageFromCard() {
        waitLocator(editAd);
        waitClickable(editAd).click();

        return new EditListingPage(driver);
    }

    public HomeAuthPage deleteAdFromCard() {
        waitLocator(deleteAd);
        waitClickable(deleteAd).click();
        waitInvisibility(deleteAd);

        return new HomeAuthPage(driver);
    }

    public void checker(By locator, String subs) {
        WebElement el = presenceLocator(locator);
        String text = el.getText();
        assertTrue(text.contains(subs));
    }

    public void checkerForImg(Ad ad) {
        if (ad.getImg1() == null || ad.getImg1().isBlank()) {
            By noImage = By.xpath("//div[contains(@class,'pictures_sDesktop')]//h2[text()='Нет изображения']");

            assertTrue(driver.findElements(noImage).size() > 0);
        } else {
            By pic1 = By.cssSelector("img[alt='pic1']");

            WebElement img1 = presenceLocator(pic1);
            String fileName = Paths.get(ad.getImg1()).getFileName().toString();

            assertTrue(img1.getAttribute("src").contains(fileName));
        }

        checkOptionalImage(ad.getImg2(), "pic2");
        checkOptionalImage(ad.getImg3(), "pic3");
    }

    private void checkOptionalImage(String imagePath, String alt) {
        By locator = By.cssSelector("img[alt='" + alt + "']");

        if (imagePath == null || imagePath.isBlank()) {
            assertTrue(driver.findElements(locator).isEmpty());
        } else {
            WebElement img = presenceLocator(locator);
            String fileName = Paths.get(imagePath).getFileName().toString();

            assertTrue(img.getAttribute("src").contains(fileName));
        }
    }
}
