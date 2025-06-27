package com.shoehospital.tests;

import com.codeborne.selenide.Selenide;
import com.shoehospital.config.TestConfig;
import com.shoehospital.pages.main.LoginPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.page;

public class BaseTest {

    @BeforeEach
    public void login () {
        Selenide.open(TestConfig.testConfig.baseUrl());
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password());
    }

//    @AfterEach
//    public void afterEach() {
//        page(DashboardPage.class)
//                .getHeader()
//                .clickLogOut();
//    }

}
