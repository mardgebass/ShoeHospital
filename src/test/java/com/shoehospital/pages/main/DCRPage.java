package com.shoehospital.pages.main;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import java.util.Locale;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selenide.$;

public class DCRPage extends BasePage {

    public String format(Double amount){
        return String.format(Locale.ENGLISH, "%.2f", amount);
    }

    @Step("{expectedResult} value in Refund DCR from DB")
    public void checkRefundDB(String expectedResult) {
        $("#dcr_form_dcrPayments_refundsTotal").shouldHave(exactValue(expectedResult));
    }

    @Step("{expectedResult} value in Card DCR from DB")
    public void checkCardDB(String card, String giftCard) {
        var expectedResult = Double.parseDouble(card) + Double.parseDouble(giftCard);
        $("#dcr_form_dcrPayments_cardsTotal").shouldHave(exactValue(format(expectedResult)));
    }

    @Step("{expectedResult} value in Cash DCR from DB")
    public void checkCashDB(String expectedResult) {
        $("#dcr_form_dcrPayments_cashDeposit").shouldHave(exactValue((expectedResult)));
    }

    @Step("Check{expectedResult} value in Check DCR from DB")
    public void checkCheckDB(String expectedResult) {
        $("#dcr_form_dcrPayments_checkDeposit").shouldHave(exactValue(expectedResult));
    }

    @Step("{expectedResult} value in Taxes DCR from DB")
    public void checkTaxesDB(String expectedResult) {
        $("#dcr_form_dcrProducts_tax").shouldHave(exactValue(expectedResult));
    }

    @Step("{payments} - {refund} value in Total DCR from DB")
    public void checkTotalDB(String payments, String giftCard, String refund) {
        double expectedResult = Double.parseDouble(payments) + Double.parseDouble(giftCard) - Double.parseDouble(refund);
        $("#dcr_form_dcrPayments_totalCollections").shouldHave(exactValue(format(expectedResult)));
    }

    @Step("{expectedResult} value in KT DCR from DB")
    public void checkKTDB(String kt) {
        $("#dcr_form_dcrProducts_otherRetailDetail_KT").shouldHave(exactValue(kt));
    }

    @Step("{expectedResult} value in Alden DCR from DB")
    public void checkAldenDB(String alden) {
        $("#dcr_form_dcrProducts_otherRetailDetail_Alden").shouldHave(exactValue(alden));
    }

    @Step("{expectedResult} value in BR DCR from DB")
    public void checkBRDB(String br) {
        $("#dcr_form_dcrProducts_otherRetailDetail_BR").shouldHave(exactValue(br));
    }
    @Step("{sundry} value in Sundry DCR from DB")
    public void checkSundryDB(String sundry) {
        $("#dcr_form_dcrProducts_sundry").shouldHave(exactValue(sundry));
    }
    @Step("{expectedResult} value from DB")
    public void checkDyeSalesDB(String expectedResult) {
        $("#dcr_form_dcrTickets_dyeSales").shouldHave(exactValue(expectedResult));
    }

    @Step("{expectedResult} value in from DB")
    public void checkSoleProsDB(String expectedResult) {
        $("#dcr_form_dcrTickets_solesPro").shouldHave(exactValue(expectedResult));
    }

    @Step("{expectedResult} value from DB")
    public void checkRepairDB(String expectedResult) {
        $("#dcr_form_dcrProducts_repair").shouldHave(exactValue(expectedResult));
    }

    public void checkArReductionDB(String refund, String tapeTotal, String taxes) {
        var expectedResult = Double.parseDouble(refund) + Double.parseDouble(refund) + Double.parseDouble(tapeTotal) - Double.parseDouble(taxes);
        $("#dcr_form_dcrZReading_arReduction").shouldHave(exactValue(format(expectedResult)));
    }

    public void checkOverShort(String sumPayments, String giftCard, String refund, String tapeTotal) {
        var expectedResult = Double.parseDouble(sumPayments) + Double.parseDouble(giftCard) - Double.parseDouble(refund) - Double.parseDouble(tapeTotal);
        $("#dcr_form_dcrZReading_overShort").shouldHave(exactValue(format(expectedResult)));
    }

    @Step("{expectedResult} value from DB")
    public void checkGiftDB(String expectedResult) {
        $("#dcr_form_dcrProducts_giftCard").shouldHave(exactValue(expectedResult));
    }

    public void checkPinksDB(String expectedResult) {
        String result = expectedResult.substring(0, expectedResult.indexOf(".00"));
        $("#dcr_form_dcrTickets_totalPinks").shouldHave(exactValue(result));
    }

    public void checkUnpaid(String expectedResult) {
        $("#dcr_form_dcrTickets_sumUnpaidTickets").shouldHave(exactValue(expectedResult));
    }

    public void checkDailyTickets(String expectedResult) {
        $("#dcr_form_dcrTickets_dailyTicketSales").shouldHave(exactValue(expectedResult));
    }

    public void checkTapeTotalDB(String totalZreading, String totalOverrings) {
        var expectedResult = Double.parseDouble(totalZreading) - Double.parseDouble(totalOverrings);
        $("#dcr_form_dcrZReading_zTapeTotal").shouldHave(exactValue(format(expectedResult)));
    }
}
