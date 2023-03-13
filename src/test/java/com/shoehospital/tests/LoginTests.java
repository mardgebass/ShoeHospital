package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.config.TestConfig;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.main.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Login")
@ExtendWith(SelenideExtension.class)
public class LoginTests {
    Faker faker = new Faker();
    String username = faker.name().lastName();
    String password = faker.bothify("?##???##");

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login with valid data")
    void validDataTest() {
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password());
        page(DashboardPage.class)
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
                .login(username, TestConfig.testConfig.password())
                .checkError("Invalid credentials");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Login with invalid password")
    void invalidPasswordTest() {
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), password)
                .checkError("Invalid credentials");
    }

}
