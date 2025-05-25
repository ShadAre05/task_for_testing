package com.stv.factory.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        glue = "com.stv.factory.bdd.stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class LoginTestRunner extends AbstractTestNGCucumberTests{
}
