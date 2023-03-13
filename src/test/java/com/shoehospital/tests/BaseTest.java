package com.shoehospital.tests;

import com.shoehospital.config.TestConfig;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.main.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.page;

public class BaseTest {

    @BeforeEach
    public void login () {
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password());
    }

    @AfterEach
    public void afterEach() {
        page(DashboardPage.class)
                .getHeader()
                .clickLogOut();
    }

}
