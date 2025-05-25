package com.stv.factory.factorytests;

import com.stv.factory.factorypages.LoginPage;
import com.stv.framework.core.lib.WigglePageURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.stv.framework.core.drivers.MyDriver.getDriver;

public class LoginPageTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        getDriver().get(WigglePageURLs.START_URL);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("Login"));
        loginPage = new LoginPage();
        loginPage.acceptCookiesIfVisible();
    }

    @Test(description = "Email field is required")
    public void testEmailIsRequiredError() {
        loginPage.enterEmail("")
                .enterPassword("SomePassword")
                .clickSignIn();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when email is empty");
    }

    @Test(description = "Forgot password link redirects to recovery page")
    public void testForgotPasswordRedirect() {
        String currentUrlBefore = getDriver().getCurrentUrl();
        loginPage.clickForgotPassword();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrlBefore)));

        String currentUrlAfter = getDriver().getCurrentUrl();
        Assert.assertNotEquals(currentUrlBefore, currentUrlAfter,
                "Clicking forgot password should redirect to another page");

        Assert.assertTrue(currentUrlAfter.contains("forgottenpassword") || currentUrlAfter.contains("forgot-password"),
                "URL should contain 'forgottenpassword' or 'forgot-password'");

        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        String headingText = heading.getText().toLowerCase();

        Assert.assertTrue(
                headingText.contains("password") || headingText.contains("create"),
                "Page should display heading related to password recovery, but found: " + headingText
        );
    }

}
