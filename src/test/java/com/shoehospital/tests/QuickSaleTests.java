package com.shoehospital.tests;

import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.MainPage;
import com.shoehospital.pages.PaymentsPage;
import com.shoehospital.pages.QuickSalePage;
import com.shoehospital.DataBaseRepository;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Quick Sale")
@ExtendWith({SelenideExtension.class})
public class QuickSaleTests extends DataBaseRepository {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale paid by cash")
    public void saleCash() {
        page(MainPage.class)
                .getHeader()
                .clickQuickSale();
        page(QuickSalePage.class)
                .addSKU(getSku())
                .editQuantity()
                .payByCash()
                .checkAlert("Payment completed");
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCashResult()
                .checkSum(getSum());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale paid by card")
    public void saleCard() {
        page(MainPage.class)
                .getHeader()
                .clickQuickSale();
        page(QuickSalePage.class)
                .addSKU(getSku())
                .editQuantity()
                .payByCard()
                .checkAlert("Payment completed");
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCardResult()
                .checkSum(getSum());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale paid by check")
    public void saleCheck() {
        page(MainPage.class)
                .getHeader()
                .clickQuickSale();
        page(QuickSalePage.class)
                .addSKU(getSku())
                .editQuantity()
                .payByCheck()
                .checkAlert("Payment completed");
        page(MainPage.class)
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkSum(getSum());
    }

    @Test
    @DisplayName("Sale not available product")
    public void saleNotAvailableProduct() {
        page(MainPage.class)
                .getHeader()
                .clickQuickSale();
        page(QuickSalePage.class)
                .addSKU(getFalseSku())
                .checkAlert("Product not found")
                .checkEmptyField();
    }

}