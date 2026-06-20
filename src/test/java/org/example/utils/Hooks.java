package org.example.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.example.data.User;
import org.example.tests.TestContext;

import static org.example.utils.EnvConfig.BASE_URL;

public class Hooks {
    private DriverFactory factory;
    public static TestContext context;

    @Before
    public void before() {
        RestAssured.baseURI = BASE_URL;

        factory = new DriverFactory();
        factory.startUp();

        DriverManager.setDriver(factory.getDriver());
        context = new TestContext();

        context.driver = factory.getDriver();
        context.user = User.userWithRandomField();
    }

    @After
    public void after() {
        if (factory.getDriver() != null) {
            factory.getDriver().quit();
        }
    }
}
