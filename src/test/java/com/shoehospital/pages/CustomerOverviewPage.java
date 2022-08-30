package com.shoehospital.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CustomerOverviewPage extends BasePage {

    @Step("Click Proceed to Payment")
    public CustomerOverviewPage clickToPay() {
        $("#payment-process-btn").click();
        return this;
    }

    @Step("Click ticket Id")
    public CustomerOverviewPage clickIdLink(String Id) {
        $(byText(Id)).click();
        return this;
    }

}
