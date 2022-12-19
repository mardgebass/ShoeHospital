package com.shoehospital.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.shoehospital.DataBaseRepository;
import com.shoehospital.config.TestConfig;
import com.shoehospital.pages.LoginPage;
import com.shoehospital.pages.MainPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Login")
public class LoginTests extends DataBaseRepository {

    @AfterEach
    public void afterEach() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.clearBrowserCache();
    }

    @BeforeEach
    public void beforeEach() {
        Configuration.browserSize = "1280x800";
        Selenide.open(TestConfig.testConfig.baseUrl());
        Configuration.pageLoadTimeout = 40;
        Configuration.timeout = 10000;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login with valid data")
    void validDataTest() {
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password());
        page(MainPage.class)
                .getHeader()
                .checkLogin();
    }

    @Test
    @DisplayName("Logout")
    void logoutTest() {
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password())
                .getHeader()
                .clickLogOut();
        page(LoginPage.class)
                .checkLogout();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login with invalid username")
    void invalidUsernameTest() {
        page(LoginPage.class)
                .login("firstName", TestConfig.testConfig.password())
                .checkError("Invalid credentials");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login with invalid password")
    void invalidPasswordTest() {
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), "75e56d")
                .checkError("Invalid credentials");
    }

}
