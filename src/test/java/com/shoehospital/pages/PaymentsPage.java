package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class PaymentsPage extends BasePage {

    @Step("Check type of payment Cash")
    public PaymentsPage checkCashResult() {
        getRow().shouldHave(Condition.text("Cash"));
        return new PaymentsPage();
    }

    @Step("Check type of payment Check")
    public PaymentsPage checkCheckResult() {
        getRow().shouldHave(Condition.text("Check"));
        return new PaymentsPage();
    }

    @Step("Check type of payment Cash")
    public PaymentsPage checkCardResult() {
        getRow().shouldHave(Condition.text("Card"));
        return new PaymentsPage();
    }

    @Step("Check sum for two products {price}")
    public PaymentsPage checkSum(String price) {
        getRow().shouldHave(Condition.text(price));
        return new PaymentsPage();
    }

    public SelenideElement getRow() {
        return $$x(".//tr[@class='odd']").get(0);
    }

}
