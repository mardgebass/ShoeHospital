package com.shoehospital.tests;

import com.shoehospital.database.SumsOnDCR;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DCRPage;
import com.shoehospital.pages.main.DashboardPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Payments in DCR")
@ExtendWith({SelenideExtension.class})
public class DCRTests extends BaseTest {

    @Test
    @DisplayName("Check check payments in DCR")
    public void checkPayments() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCheckDB(SumsOnDCR.getCheck());
    }

    @Test
    @DisplayName("Check cash payments in DCR")
    public void cashPayments() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashDB(SumsOnDCR.getCash());
    }

    @Test
    @DisplayName("Check card payments in DCR")
    public void cardPayments() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardDB(SumsOnDCR.getCard());
    }

    @Test
    @DisplayName("Check refunds in DCR")
    public void refunds() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkRefundDB(SumsOnDCR.getRefund());
    }

    @Test
    @DisplayName("Check taxes in DCR")
    public void taxes() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkTaxesDB(SumsOnDCR.getTaxes());
    }

    @Test
    @DisplayName("Check Total Collection in DCR")
    public void total() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkTotalDB(SumsOnDCR.getSumPayments(), SumsOnDCR.getRefund());
    }

    @Test
    @DisplayName("Check Alden (Other Retails) in DCR")
    public void alden() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkAldenDB(SumsOnDCR.getAlden());
    }

    @Test
    @DisplayName("Check KT (Other Retails) in DCR")
    public void KT() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkKTDB(SumsOnDCR.getKT());
    }

    @Test
    @DisplayName("Check BR (Other Retails) in DCR")
    public void BR() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkBRDB(SumsOnDCR.getBR());
    }

    @Test
    @DisplayName("Check BR Sundry in DCR")
    public void sundry() {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkSundryDB(SumsOnDCR.getSundry());
    }

}