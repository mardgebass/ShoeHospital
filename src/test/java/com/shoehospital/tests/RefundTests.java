package com.shoehospital.tests;

import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.DCRPage;
import com.shoehospital.pages.MainPage;
import com.shoehospital.pages.PaymentsPage;
import com.shoehospital.pages.RefundsPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Refund")
@ExtendWith({SelenideExtension.class})
public class RefundTests extends PaymentsPage {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund in cash")
    public void makeRefundByCash() {
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundBefore();
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefundButton()
                .getAmountForRefund()
                .fillAmountRefund()
                .fillShoeRepair()
                .fillOtherRetail()
                .fillSundries()
                .fillSalesTaxes()
                .clickRefundType()
                .clickCashRefundType()
                .clickSaveRefund()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(REFUND);
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundShoeRepair(REFUND);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund by card")
    public void makeRefundByCard() {
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundBefore();
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefundButton()
                .getAmountForRefund()
                .fillAmountRefund()
                .fillAllShoeRepair()
                .clickRefundType()
                .clickCardRefundType()
                .clickSaveRefund()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(REFUND);
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundShoeRepair(REFUND);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund by check")
    public void makeRefundByCheck() {
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundBefore();
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefundButton()
                .getAmountForRefund()
                .fillAmountRefund()
                .fillShoeRepair()
                .fillOtherRetail()
                .fillSundries()
                .fillSalesTaxes()
                .clickRefundType()
                .clickCheckRefundType()
                .fillNote()
                .clickSaveRefund()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(REFUND);
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundShoeRepair(REFUND);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund with wrong amount")
    public void makeRefundWithErrorSum() {
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .clickRefundButton()
                .getAmountForRefund()
                .fillAmountRefund()
                .fillShoeRepair()
                .fillErrorOtherRetail()
                .fillSundries()
                .fillSalesTaxes()
                .clickRefundType()
                .clickCheckRefundType()
                .clickSaveRefund()
                .checkEqualError();
    }

}
