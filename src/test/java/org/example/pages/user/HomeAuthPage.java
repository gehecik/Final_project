package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.data.Ad;
import org.example.data.AdCard;
import org.example.pages.BasePage;
import org.example.pages.HomePage;
import org.example.pages.ad.AdPage;
import org.example.pages.ad.CreateListingPage;
import org.example.pages.ad.EditListingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HomeAuthPage extends BasePage {
    protected final By exitButton = By.xpath("//button[text()='Выйти']");
    protected final By adButton = By.xpath("//button[text()='Разместить объявление']");
    protected final By editLocator = By.className("editButton");


    private final By cardsContainer = By.cssSelector("div[class*='grid_twoColumns']");
    private final By cards = By.cssSelector("div[class*='card']");
    private final By img = By.tagName("img");
    private final By about = By.cssSelector("div[class*='about']");
    private final By name = By.cssSelector("h2");
    private final By city = By.cssSelector("h3");
    private final By price = By.cssSelector("div[class*='price'] h2");
    private final By button = By.cssSelector("button");

    private AdCard currentCard;

    public HomeAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check the button (exit)")
    public void checkContent() {
        waitLocator(exitButton);
    }

    @Step("Click the button (exit)")
    public HomePage logoutUser() {
        scrollToTop();
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
        System.out.println("Check the ad");

        presenceLocator(bar);

        int attempts = 1;
        int maxAttempts = getMaxAttempts(bar);

        while (attempts <= maxAttempts) {
            AdCard foundCard = findCard(ad);
            if (foundCard != null) {

                currentCard = foundCard;

                if (checkImage) {
                    checkImg(foundCard, ad);
                }

                checkCost(foundCard, ad);
                checkEdit(foundCard);

                return;
            }

            if (isLastPage()) {
                break;
            }

            goToNextPage();

            attempts++;
        }

        throw new AssertionError(
                "Объявление '" + ad.getName() + "' отсутствует");
    }

    @Step("Click the button (edit)")
    public EditListingPage clickEdit() {
        System.out.println("Click the button (edit)");
        AdCard freshCard = findCardByNameAndCity(
                currentCard.getName(),
                currentCard.getCity());

        freshCard.getButton().click();

        return new EditListingPage(driver);
    }

    public List<AdCard> getCards() {

        WebElement container = driver.findElement(cardsContainer);

        return container.findElements(cards)
                .stream()
                .map(card -> {
                    String imgSrc = card.findElement(img).getAttribute("src");
                    WebElement aboutBlock = card.findElement(about);
                    String name = aboutBlock.findElement(By.tagName("h2")).getText();
                    String city = aboutBlock.findElement(By.tagName("h3")).getText();
                    String cost = card.findElement(price).getText().replaceAll("[^0-9]", "");
                    WebElement button = card.findElement(this.button);

                    return new AdCard(imgSrc, name, city, cost, button, card);
                }).collect(Collectors.toList());
    }

    public AdCard findCard(Ad ad) {

        return getCards().stream()
                .filter(card ->
                        ad.getName().equals(card.getName())
                                && ad.getCity().equals(card.getCity()))
                .findFirst()
                .orElse(null);
    }

    private void goToNextPage() {

        presenceLocator(bar);

        scrollToLocator(bar);

        WebElement next = presenceLocator(arrowButton);

        String oldPage = presenceLocator(bar).getText();
        String oldAd = presenceLocator(adsList).getText();

        next.click();

        reloadPage(bar, oldPage);
        reloadPage(adsList, oldAd);
    }

    public AdCard findCardByNameAndCity(String name, String city) {
        return getCards().stream()
                .filter(card ->
                        name.equals(card.getName()) &&
                                city.equals(card.getCity()))
                .findFirst()
                .orElse(null);
    }

    private boolean isLastPage() {
        String text = presenceLocator(bar).getText();
        String[] parts = text.split(" из ");

        return parts[0].equals(parts[1]);
    }

    @Step("Click on ad card")
    public AdPage clickCard() {
        AdCard card = findCardByNameAndCity(
                currentCard.getName(),
                currentCard.getCity());

        card.getCard().click();

        return new AdPage(driver);
    }
}
