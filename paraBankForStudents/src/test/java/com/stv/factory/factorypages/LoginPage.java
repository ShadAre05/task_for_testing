package com.stv.factory.factorypages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static com.stv.framework.core.drivers.MyDriver.getDriver;

public class LoginPage {

    @FindBy(id = "Login_EmailAddress")
    private WebElement emailField;

    @FindBy(id = "Login_Password")
    private WebElement passwordField;

    @FindBy(id = "LoginButton")
    private WebElement signInButton;

    @FindBy(css = ".dnnFormMessage.dnnFormValidationSummary")
    private WebElement errorMessage;

    @FindBy(css = "a[href='/login/forgottenpassword']")
    private WebElement forgotPasswordLink;

    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement allowCookiesButton;

    private final WebDriverWait wait;

    public LoginPage() {
        WebDriver driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Login_EmailAddress")));

        PageFactory.initElements(driver, this);
    }

    public void acceptCookiesIfVisible() {
        try {
            if (allowCookiesButton.isDisplayed()) {
                allowCookiesButton.click();
                wait.until(ExpectedConditions.invisibilityOf(allowCookiesButton));
            }
        } catch (NoSuchElementException ignored) {}
    }

    public LoginPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public LoginPage clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLink.click();
        return this;
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
