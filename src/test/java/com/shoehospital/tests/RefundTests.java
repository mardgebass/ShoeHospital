package com.shoehospital.tests;

import com.shoehospital.database.ValuesDB;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.payments.PaymentsPage;
import com.shoehospital.pages.payments.RefundsPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.security.SecureRandom;

import static com.codeborne.selenide.Selenide.page;
import static com.shoehospital.database.ValuesDB.amountById;

@DisplayName("Refund")
@ExtendWith({SelenideExtension.class})
public class RefundTests extends BaseTest {

    SecureRandom random = new SecureRandom();
    float number = random.nextInt(5) + 1;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund in cash")
    public void makeRefundByCash() {
        page(DashboardPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefund(ValuesDB.getPaymentIdWithoutRefund(), ValuesDB.getAmountFromPayments())
                .fillRefundPopup(amountById, amountById)
                .chooseType("Cash")
                .clickSave()
                .scrollTop()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(ValuesDB.getAmountFromRefunds());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund by card")
    public void makeRefundByCard() {
        page(DashboardPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefund(ValuesDB.getPaymentIdWithoutRefund(), ValuesDB.getAmountFromPayments())
                .fillRefundPopup(amountById, amountById)
                .chooseType("Card")
                .clickSave()
                .scrollTop()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(ValuesDB.getAmountFromRefunds());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund by check")
    public void makeRefundByCheck() {
        page(DashboardPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefund(ValuesDB.getPaymentIdWithoutRefund(), ValuesDB.getAmountFromPayments())
                .fillRefundPopup(amountById, amountById)
                .chooseType("Check")
                .clickSave()
                .scrollTop()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(ValuesDB.getAmountFromRefunds());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a partial refund by check")
    public void partialRefund() {
        page(DashboardPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefund(ValuesDB.getPaymentIdWithoutRefund(), ValuesDB.getAmountFromPayments())
                .fillRefundPopup((String.valueOf((Float.parseFloat(amountById)) / number)), (String.valueOf((Float.parseFloat(amountById)) / number)))
                .chooseType("Check")
                .clickSave()
                .scrollTop()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(ValuesDB.getAmountFromRefunds());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund without Amount")
    public void makeRefundWithoutAmount() {
        page(DashboardPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .scrollTop()
                .clickRefund(ValuesDB.getPaymentIdWithoutRefund(), ValuesDB.getAmountFromPayments())
                .fillRefundPopup("0", amountById)
                .chooseType("Check")
                .clickSave()
                .checkEqualError();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund with wrong amount")
    public void makeRefundWithErrorSum() {
        page(DashboardPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefund(ValuesDB.getPaymentIdWithoutRefund(), ValuesDB.getAmountFromPayments())
                .fillRefundPopup((String.valueOf(Float.parseFloat(amountById) + number)), amountById)
                .chooseType("Check")
                .clickSave()
                .checkEqualError();
    }

}
