package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Double.parseDouble;

public class DCRPage extends BasePage {

    private static double CARD;
    private static double CASH;
    private static double CHECK;
    private static double REFUND;

    @Step("Check value in DCR before test")
    public DCRPage checkRefundBefore() {
        REFUND = Double.valueOf(getRefundShoeRepair().getValue());
        return new DCRPage();
    }

    @Step("Check value in DCR after test")
    public DCRPage checkRefundShoeRepair(String refund) {
        double expectedResult = REFUND + (parseDouble(refund));
        getRefundShoeRepair().shouldHave(Condition.value(Double.toString(expectedResult)));
        return new DCRPage();
    }

    private SelenideElement getRefundShoeRepair() {
        return $("#daily_report_form_refundsTotal");
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCardBefore() {
        CARD = Double.valueOf(getCardPayment().getValue());
        return new DCRPage();
    }

    @Step("Check value in DCR after test")
    public DCRPage checkCardResult(String price) {
        double expectedResult = CARD + (parseDouble(price))*1.0825;
        getCardPayment().shouldHave(Condition.value(String.format(Locale.ENGLISH, "%(.2f", expectedResult)));
        return new DCRPage();
    }

    private SelenideElement getCardPayment() {
        return $("#daily_report_form_otherCardsTotal");
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCashBefore() {
        CASH = Double.valueOf(getCashPayment().getValue());
        return new DCRPage();
    }

    @Step("Check value in DCR after test")
    public DCRPage checkCashResult(String price) {
        double expectedResult = CASH + (parseDouble(price))*1.0825;
        getCashPayment().shouldHave(Condition.value(String.format(Locale.ENGLISH, "%(.2f", expectedResult)));
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
        double expectedResult = CHECK + (parseDouble(price))*1.0825;
        getCheckPayment().shouldHave(Condition.value(String.format(Locale.ENGLISH, "%(.2f", expectedResult)));
        return new DCRPage();
    }

    private SelenideElement getCheckPayment() {
        return $("#daily_report_form_checkDeposit");
    }

}
