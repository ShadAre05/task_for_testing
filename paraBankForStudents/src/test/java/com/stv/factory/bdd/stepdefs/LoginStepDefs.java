package com.stv.factory.bdd.stepdefs;

import com.stv.factory.factorypages.LoginPage;
import com.stv.framework.core.lib.WigglePageURLs;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.stv.framework.core.drivers.MyDriver.getDriver;
import static org.testng.Assert.assertTrue;

public class LoginStepDefs {
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        getDriver().get(WigglePageURLs.START_URL);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(driver -> driver.getTitle().contains("Login"));
        loginPage = new LoginPage();
        loginPage.acceptCookiesIfVisible();
    }

    @When("I enter an email address without @ symbol")
    public void iEnterAnInvalidEmail() {
        loginPage.enterEmail("invalidemail.com");
    }

    @When("I enter a valid password")
    public void iEnterAValidPassword() {
        loginPage.enterPassword("SomePassword123");
    }

    @When("I click the Sign In button")
    public void iClickSignIn() {
        loginPage.clickSignIn();
    }

    @Then("I should see an error message indicating an invalid email format")
    public void iShouldSeeInvalidEmailError() {
        assertTrue(loginPage.isErrorMessageDisplayed(), "Expected error message for invalid email format.");
    }

    @When("I enter an email {string}")
    public void iEnterEmail(String email) {
        loginPage.enterEmail(email);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Then("I should see an error message indicating incorrect login credentials")
    public void iShouldSeeLoginError() {
        assertTrue(loginPage.isErrorMessageDisplayed(), "Expected error message for incorrect login credentials.");
    }
}
