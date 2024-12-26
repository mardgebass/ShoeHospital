package com.shoehospital.pages.payments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class RefundsPage extends BasePage {

    @Step("sum of refund")
    public RefundsPage checkSumOfRefund(String price) {
        getRow().shouldHave(Condition.text(price));
        return this;
    }

    public SelenideElement getRow() {
        return $$x(".//tr[@class='odd']").get(0);
    }

}
