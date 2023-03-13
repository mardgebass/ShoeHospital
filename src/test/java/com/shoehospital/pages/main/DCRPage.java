package com.shoehospital.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import java.util.Locale;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Double.parseDouble;

public class DCRPage extends BasePage {

    private static double CARD;
    private static double CASH;
    private static double CHECK;
    private static double REFUND;

    @Step("Check value in DCR before test")
    public DCRPage checkRefundBefore() {
        REFUND = Double.parseDouble(Objects.requireNonNull(getRefundShoeRepair().getValue()));
        return this;
    }

    @Step("Check value in DCR after test")
    public void checkRefundShoeRepair(String refund) {
        double expectedResult = REFUND + (parseDouble(refund));
        getRefundShoeRepair().shouldHave(Condition.value(Double.toString(expectedResult)));
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCardBefore() {
        CARD = Double.parseDouble(Objects.requireNonNull(getCardPayment().getValue()));
        return this;
    }

    @Step("Check value in DCR after test")
    public void checkCardResult(String price) {
        double expectedResult = CARD + (parseDouble(price))*1.0825;
        getCardPayment().shouldHave(Condition.value(convert(expectedResult)));
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCashBefore() {
        CASH = Double.parseDouble(Objects.requireNonNull(getCashPayment().getValue()));
        return this;
    }

    @Step("Check value in DCR after test")
    public void checkCashResult(String price) {
        double expectedResult = CASH + (parseDouble(price))*1.0825;
        getCashPayment().shouldHave(Condition.value(convert(expectedResult)));
    }

    @Step("Check value in DCR before test")
    public DCRPage checkCheckBefore() {
        CHECK = Double.parseDouble(Objects.requireNonNull(getCheckPayment().getValue()));
        return this;
    }

    @Step("Check value in DCR after test")
    public void checkCheckResult(String price) {
        double expectedResult = CHECK + (parseDouble(price))*1.0825;
        getCheckPayment().shouldHave(Condition.value(convert(expectedResult)));
    }

    @Step("Check value in DCR after applying discount")
    public void checkCashDiscountResult(String price, String discount) {
        double expectedResult = CASH + (parseDouble(price) - parseDouble("1" + discount))*1.0825;
        getCashPayment().shouldHave(Condition.value(convert(expectedResult)));
    }

    @Step("Check value in DCR after applying discount")
    public void checkCheckDiscountResult(String price, String discount) {
        double expectedResult = CHECK + (parseDouble(price) - parseDouble(price) / 100 * parseDouble("1" + discount)) * 1.0825;
        getCheckPayment().shouldHave(Condition.value(convert(expectedResult)));
    }

    public String convert (Double value) {
        return String.format(Locale.ENGLISH, "%(.2f", (value));
    }

    private SelenideElement getCardPayment() {
        return $("#daily_report_form_otherCardsTotal");
    }

    private SelenideElement getCashPayment() {
        return $("#daily_report_form_cashDeposit");
    }

    private SelenideElement getCheckPayment() {
        return $("#daily_report_form_checkDeposit");
    }

    private SelenideElement getRefundShoeRepair() {
        return $("#daily_report_form_refundsTotal");
    }
}
