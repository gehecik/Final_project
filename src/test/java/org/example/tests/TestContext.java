package org.example.tests;

import org.example.data.Ad;
import org.example.data.User;
import org.example.pages.HomePage;
import org.example.pages.ad.AdPage;
import org.example.pages.ad.CreateListingPage;
import org.example.pages.ad.EditListingPage;
import org.example.pages.user.HomeAuthPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestContext {
    public WebDriver driver;
    public User user;
    public String userCreateToken;
    public String userLoginToken;
    public int userId;
    public HomePage homePage;
    public HomeAuthPage homeAuthPage;
    public CreateListingPage createListingPage;
    public EditListingPage editListingPage;
    public AdPage adPage;
    public Ad ad;
    public int adId;

    public WebDriver getDriver() {
        return driver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

//    public HomePage getHomePage() {
//        return homePage;
//    }
//
//    public void setHomePage(HomePage homePage) {
//        this.homePage = homePage;
//    }
//
//    public HomeAuthPage getHomeAuthPage() {
//        return homeAuthPage;
//    }
//
//    public void setHomeAuthPage(HomeAuthPage homeAuthPage) {
//        this.homeAuthPage = homeAuthPage;
//    }
//
//    public CreateListingPage getCreateListingPage() {
//        return createListingPage;
//    }
//
//    public void setCreateListingPage(CreateListingPage createListingPage) {
//        this.createListingPage = createListingPage;
//    }
//
//    public String getUserLoginToken() {
//        return userLoginToken;
//    }
//
//    public void setUserLoginToken(String userLoginToken) {
//        this.userLoginToken = userLoginToken;
//    }
//
//    public String getUserCreateToken() {
//        return userCreateToken;
//    }
//
//    public void setUserCreateToken(String userCreateToken) {
//        this.userCreateToken = userCreateToken;
//    }
//
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public void deleteCurrentAd() throws InterruptedException {
        if (ad == null) {
            return;
        }
        homeAuthPage.checkAd(ad, false);
        adPage = homeAuthPage.clickCard();
        homeAuthPage = adPage.deleteAdFromCard();
    }
}
