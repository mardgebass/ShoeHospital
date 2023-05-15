package com.shoehospital.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.shoehospital.config.TestConfig;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.io.IOException;

public class SelenideExtension implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback {

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

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws IOException {
        FileUtils.cleanDirectory(new File("D:/git/repos/ShoeHospital/allure-results"));
    }
}
