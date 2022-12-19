package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class RefundsPage extends BasePage{

    @Step("Check sum of refund")
    public RefundsPage checkSumOfRefund(String price) {
        getRow().shouldHave(Condition.text(price));
        return new RefundsPage();
    }

    public SelenideElement getRow() {
        return $$x(".//tr[@class='odd']").get(0);
    }

}
