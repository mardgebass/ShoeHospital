package com.shoehospital.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.shoehospital.config.TestConfig;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SelenideExtension implements BeforeEachCallback, AfterAllCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
//        Configuration.browser = "Firefox";
        Configuration.browserSize = "1280x800";
        Selenide.open(TestConfig.testConfig.baseUrl());
        Configuration.pageLoadTimeout = 40;
        Configuration.timeout = 10000;
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        WebDriverRunner.clearBrowserCache();
    }

}
