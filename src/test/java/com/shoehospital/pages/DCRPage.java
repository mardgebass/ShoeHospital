package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;

public class DCRPage extends BasePage {

    private static double CARD;
    private static double CASH;
    private static double CHECK;

    @Step("Check value in DCR before test")
    public DCRPage checkCardBefore() {
        CARD = Double.valueOf(getCardPayment().getValue());
        return new DCRPage();
    }

    @Step("Check value in DCR after test")
    public DCRPage checkCardResult(String price) {
        double expectedResult = CARD + parseDouble(price);
        getCardPayment().shouldHave(Condition.value(Double.toString(expectedResult)));
        return new DCRPage();
    }

    private SelenideElement getCardPayment() {
        return $("#daily_report_form_cardDeposit");
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCashBefore() {
        CASH = Double.valueOf(getCashPayment().getValue());
        return new DCRPage();
    }

    @Step("Check value in DCR after test")
    public DCRPage checkCashResult(String price) {
        double expectedResult = CASH + parseDouble(price);
        getCashPayment().shouldHave(Condition.value(Double.toString(expectedResult)));
        return new DCRPage();
    }

    private SelenideElement getCashPayment() {
        return $("#daily_report_form_cashDeposit");
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCheckBefore() {
        CHECK = Double.valueOf(getCheckPayment().getValue());
        return new DCRPage();
    }

    @Step("Check value in DCR after test")
    public DCRPage checkCheckResult(String price) {
        double expectedResult = CHECK + parseDouble(price);
        getCheckPayment().shouldHave(Condition.value(Double.toString(expectedResult)));
        return new DCRPage();
    }

    private SelenideElement getCheckPayment() {
        return $("#daily_report_form_checkDeposit");
    }

}
