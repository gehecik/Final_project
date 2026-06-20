package org.example.pages.ad;

import org.example.data.Ad;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdPage extends BasePage {
    protected final By categoryCondition = By.className("listing_categoryColor__Zj1m4");
    protected final By name = By.className("h1Mobile");
    protected final By description = By.xpath("//p[@class='spanGlobal']");
    protected final By city = By.xpath("//h3[@class='subtitle']");


    public AdPage(WebDriver driver) {
        super(driver);
    }

    public void checkNewAdWithoutImg(Ad ad) {
        checker(categoryCondition, ad.getCategory());
        checker(categoryCondition, ad.getCondition());
        checker(name, ad.getName());
        checker(description, ad.getDescription());
        checker(city, ad.getCity());

    }

    public void checker(By locator, String subs) {
        WebElement el = presenceLocator(locator);
        String text = el.getText();
        assertTrue(text.contains(subs));
    }

}
