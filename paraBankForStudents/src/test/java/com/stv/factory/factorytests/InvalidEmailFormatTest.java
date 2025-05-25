package com.stv.factory.factorytests;

import com.stv.factory.factorypages.LoginPage;
import com.stv.framework.core.drivers.MyDriver;
import com.stv.framework.core.lib.WigglePageURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.stv.framework.core.drivers.MyDriver.getDriver;

public class InvalidEmailFormatTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        getDriver().get(WigglePageURLs.START_URL);

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Login_EmailAddress")));

        loginPage = new LoginPage();
        loginPage.acceptCookiesIfVisible();
    }

    @AfterMethod
    public void tearDown() {
        MyDriver.quit();
    }

    @Test
    public void testInvalidEmailFormatShowsErrorOrUnexpectedBehavior() {
        loginPage.enterEmail("invalidemail")
                .enterPassword("invalidpassword")
                .clickSignIn();

        boolean errorShown = loginPage.isErrorMessageDisplayed();

        Assert.assertTrue(errorShown, "Error message is not displayed");
    }
}
