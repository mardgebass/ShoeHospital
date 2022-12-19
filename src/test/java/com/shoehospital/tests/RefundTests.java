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

@DisplayName("Make a refund")
@ExtendWith({SelenideExtension.class})
public class RefundTests extends PaymentsPage {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Make a refund by cash")
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
                .fillNote()
                .clickSaveRefund()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(AMOUNT_REFUND);
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundShoeRepair(AMOUNT_REFUND);
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
                .fillShoeRepair()
                .fillOtherRetail()
                .fillSundries()
                .fillSalesTaxes()
                .clickRefundType()
                .clickCardRefundType()
                .fillNote()
                .clickSaveRefund()
                .clickRefunds();
        page(RefundsPage.class)
                .checkSumOfRefund(AMOUNT_REFUND);
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundShoeRepair(AMOUNT_REFUND);
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
                .checkSumOfRefund(AMOUNT_REFUND);
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundShoeRepair(AMOUNT_REFUND);
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
