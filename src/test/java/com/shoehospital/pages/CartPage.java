package com.shoehospital.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage {

    @Step("Pay by Cash")
    public CartPage payByCash() {
        $(byText("Cash")).click();
        $("#payment_cash_modal").$(byText("Received")).click();
        return this;
    }

    @Step("Pay by Check")
    public CartPage payByCheck() {
        $(byText("Check")).click();
        $("#payment_check_modal").$(byText("Received")).click();
        return this;
    }

    @Step("Pay by Card")
    public CartPage payByCard() {
        $(byText("Credit Card")).click();
        $$x(".//label[@class='radio me-3']").get(0).click();
        $("#card-pay-received-btn").click();
        return this;
    }

}
