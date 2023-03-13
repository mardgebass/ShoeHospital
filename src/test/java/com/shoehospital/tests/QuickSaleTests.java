package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.database.ValuesDB;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.payments.PaymentsPage;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.products.QuickSalePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;
import static com.shoehospital.database.ValuesDB.valueDollar;
import static com.shoehospital.database.ValuesDB.valuePercent;
import static java.lang.Double.parseDouble;

@DisplayName("Quick Sale")
@ExtendWith({SelenideExtension.class})
public class QuickSaleTests extends BaseTest {
    Faker faker = new Faker();
    String discount = faker.numerify("##.##");

    @BeforeEach
    public void toQuickSale() {
        page(DashboardPage.class)
                .getHeader()
                .clickQuickSale();
    }

    @Test
    @DisplayName("Sale product with $ discount (cash)")
    public void discountDollarTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .chooseDiscountType("Custom $")
                .clickSaveDiscount()
                .addDiscountSum(discount)
                .clickSaveDiscount()
                .checkDiscountInRow("$" + discount)
                .payByCash()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkDollarDiscountResult(ValuesDB.getSum(), discount);
    }

    @Test
    @DisplayName("Sale product with % discount (check)")
    public void discountPercentTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .chooseDiscountType("Custom %")
                .clickSaveDiscount()
                .addDiscountSum(discount)
                .clickSaveDiscount()
                .checkDiscountInRow(discount + "%")
                .payByCheck()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkPercentDiscountResult(ValuesDB.getSum(), discount);
    }

    @Test
    @DisplayName("Sale products with promocode in % (check)")
    public void discountPromoPercentTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .chooseDiscountType(ValuesDB.getTitlePercent())
                .clickSaveDiscount()
                .checkDiscountInRow(String.format(Locale.ENGLISH, "%(.2f", parseDouble(ValuesDB.getValuePercent())) + "%")
                .payByCheck()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkPercentDiscountResult(ValuesDB.getSum(), valuePercent);
    }

    @Test
    @DisplayName("Sale products with promocode in $ (check)")
    public void discountPromoDollarTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .chooseDiscountType(ValuesDB.getTitleDollar())
                .clickSaveDiscount()
                .checkDiscountInRow("$" + String.format(Locale.ENGLISH, "%(.2f", parseDouble(ValuesDB.getValueDollar())))
                .payByCheck()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkDollarDiscountResult(ValuesDB.getSum(), valueDollar);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale two products paid in cash")
    public void saleTwoProductsInCash() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .editQuantity()
                .payByCash()
                .checkAlert()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCashResult()
                .checkSum(ValuesDB.getSum());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale two products paid by card")
    public void saleTwoProductsByCard() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .editQuantity()
                .payByCard()
                .checkAlert()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCardResult()
                .checkSum(ValuesDB.getSum());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale paid by card")
    public void saleCard() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .payByCard()
                .checkAlert()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCardResult()
                .checkSumForOne(ValuesDB.getSum());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale two products paid by check")
    public void saleTwoProductsCheck() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .editQuantity()
                .payByCheck()
                .checkAlert()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkSum(ValuesDB.getSum());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Sale two products of same unit and one another paid by check")
    public void saleSeveralProducts() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .editQuantity()
                .addSKU(ValuesDB.getSecondSku())
                .payByCheck()
                .checkAlert()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkDoubleSum(ValuesDB.getSum(), ValuesDB.getSumOfSecond());
    }

    @Test
    @DisplayName("Sale not available product")
    public void saleNotAvailableProduct() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getFalseSku())
                .checkErrorAlert()
                .checkEmptyField();
    }

}