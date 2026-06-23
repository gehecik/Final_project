package org.example.utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
