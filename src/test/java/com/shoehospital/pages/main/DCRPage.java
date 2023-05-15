package com.shoehospital.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class DCRPage extends BasePage {

    private SelenideElement getCardPayment() {
        return $("#dcr_form_dcrPayments_cardsTotal");
    }

    private SelenideElement getCashPayment() {
        return $("#dcr_form_dcrPayments_cashDeposit");
    }

    private SelenideElement getCheckPayment() {
        return $("#dcr_form_dcrPayments_checkDeposit");
    }

    private SelenideElement getRefundShoeRepair() {
        return $("#dcr_form_dcrPayments_refundsTotal");
    }

    @Step("Check {expectedResult} value in Refund DCR from DB")
    public void checkRefundDB(String expectedResult) {
        getRefundShoeRepair().shouldHave(Condition.value(expectedResult));
    }

    @Step("Check {expectedResult} value in Card DCR from DB")
    public void checkCardDB(String expectedResult) {
        getCardPayment().shouldHave(Condition.value(expectedResult));
    }

    @Step("Check {expectedResult} value in Cash DCR from DB")
    public void checkCashDB(String expectedResult) {
        getCashPayment().shouldHave(Condition.value((expectedResult)));
    }

    @Step("Check{expectedResult} value in Check DCR from DB")
    public void checkCheckDB(String expectedResult) {
        getCheckPayment().shouldHave(Condition.value(expectedResult));
    }

    @Step("Check {expectedResult} value in Taxes DCR from DB")
    public void checkTaxesDB(String expectedResult) {
        $("#dcr_form_dcrProducts_tax").shouldHave(Condition.value(expectedResult));
    }

    @Step("Check {payments} - {refund} value in Total DCR from DB")
    public void checkTotalDB(String payments, String refund) {
        String expectedResult = rounding(Double.parseDouble(payments) - Double.parseDouble(refund));
        $("#dcr_form_dcrPayments_totalCollections").shouldHave(Condition.value(expectedResult));
    }

    @Step("Check {expectedResult} value in KT DCR from DB")
    public void checkKTDB(String kt) {
        $("#dcr_form_dcrProducts_otherRetailDetail_KT").shouldHave(Condition.value(kt));
    }

    @Step("Check {expectedResult} value in Alden DCR from DB")
    public void checkAldenDB(String alden) {
        $("#dcr_form_dcrProducts_otherRetailDetail_Alden").shouldHave(Condition.value(alden));
    }

    @Step("Check {expectedResult} value in BR DCR from DB")
    public void checkBRDB(String br) {
        $("#dcr_form_dcrProducts_otherRetailDetail_BR").shouldHave(Condition.value(br));
    }

    @Step("Check {expectedResult} value in Sundry DCR from DB")
    public void checkSundryDB(String sundry) {
        $("#dcr_form_dcrProducts_sundry").shouldHave(Condition.value(sundry));
    }

}
