package com.shoehospital.tests;

import com.shoehospital.database.SumsOnDCR;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DCRPage;
import com.shoehospital.pages.main.DashboardPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Payments")
@ExtendWith({SelenideExtension.class})
public class DCRTests extends BaseTest {

    @BeforeEach
    public void openDCR () {
        page(DashboardPage.class)
                .getHeader()
                .clickDCR();
    }
    
    @Test
    @DisplayName("Check payments")
    public void checkPayments() {
        page(DCRPage.class)
                .checkCheckDB(SumsOnDCR.getCheck());
    }

    @Test
    @DisplayName("Cash payments")
    public void cashPayments() {
        page(DCRPage.class)
                .checkCashDB(SumsOnDCR.getCash());
    }

    @Test
    @DisplayName("Card payments")
    public void cardPayments() {
        page(DCRPage.class)
                .checkCardDB(SumsOnDCR.getCard(), SumsOnDCR.getGiftCard());
    }

    @Test
    @DisplayName("Refunds")
    public void refunds() {
        page(DCRPage.class)
                .checkRefundDB(SumsOnDCR.getRefund());
    }

    @Test
    @DisplayName("Taxes")
    public void taxes() {
        page(DCRPage.class)
                .checkTaxesDB(SumsOnDCR.getTaxes());
    }

    @Test
    @DisplayName("Total Collection")
    public void total() {
        page(DCRPage.class)
                .checkTotalDB(SumsOnDCR.getSumPayments(), SumsOnDCR.getGiftCard(), SumsOnDCR.getRefund());
    }

    @Test
    @DisplayName("Alden (Other Retails)")
    public void alden() {
        page(DCRPage.class)
                .checkAldenDB(SumsOnDCR.getAlden());
    }

    @Test
    @DisplayName("KT (Other Retails)")
    public void KT() {
        page(DCRPage.class)
                .checkKTDB(SumsOnDCR.getKT());
    }

    @Test
    @DisplayName("BR (Other Retails)")
    public void BR() {
        page(DCRPage.class)
                .checkBRDB(SumsOnDCR.getBR());
    }

    @Test
    @DisplayName("Sundry")
    public void sundry() {
        page(DCRPage.class)
                .checkSundryDB(SumsOnDCR.getSundry());
    }

    @Test
    @DisplayName("Gift Purchase")
    public void gift() {
        page(DCRPage.class)
                .checkGiftDB(SumsOnDCR.getGiftPurchase());
    }

    @Test
    @DisplayName("Repair")
    public void repair() {
        page(DCRPage.class)
                .checkRepairDB(SumsOnDCR.getRepair());
    }

    @Test
    @DisplayName("Dye Sales")
    public void dyeSales() {
        page(DCRPage.class)
                .checkDyeSalesDB(SumsOnDCR.getDyeSales());
    }

    @Test
    @DisplayName("Sole Pros")
    public void solePros() {
        page(DCRPage.class)
                .checkSoleProsDB(SumsOnDCR.getSolePros());
    }

    @Test
    @DisplayName("Over/Short")
    public void overShort() {
        page(DCRPage.class)
                .checkOverShort(SumsOnDCR.getSumPayments(), SumsOnDCR.getGiftCard(), SumsOnDCR.getRefund(), SumsOnDCR.getTapeTotal());
    }

    @Test
    @DisplayName("AR Reduction")
    public void aRReduction() {
        page(DCRPage.class)
                .checkArReductionDB(SumsOnDCR.getRefund(), SumsOnDCR.getTapeTotal(), SumsOnDCR.getTaxes());
    }

//    @Test
//    @DisplayName("Tape Total")
//    public void tapeTotal() {
//        page(DCRPage.class)
//                .checkTapeTotalDB(SumsOnDCR.getTapeTotal(), SumsOnDCR.getTaxes());
//    }

    @Test
    @DisplayName("Pinks")
    public void pinks() {
        page(DCRPage.class)
                .checkPinksDB(SumsOnDCR.getPinks());
    }

    @Test
    @DisplayName("Unpaid Tickets")
    public void unpaidTickets() {
        page(DCRPage.class)
                .checkUnpaid(SumsOnDCR.getUnpaid());
    }

    @Test
    @DisplayName("Total Daily Ticket Sales")
    public void dailyTickets() {
        page(DCRPage.class)
                .checkDailyTickets(SumsOnDCR.getDailyTickets());
    }


}