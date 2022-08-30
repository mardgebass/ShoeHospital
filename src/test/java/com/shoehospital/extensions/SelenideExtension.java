package com.shoehospital.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.shoehospital.config.TestConfig;
import com.shoehospital.pages.LoginPage;
import com.shoehospital.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.page;

public class SelenideExtension implements AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.clearBrowserCache();

    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        Configuration.browserSize = "1280x800";
        Selenide.open(TestConfig.testConfig.baseUrl());
        Configuration.pageLoadTimeout = 40;
        Configuration.timeout = 10000;
        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        page(MainPage.class)
                .getHeader()
                .clickLogOut();
    }

}
