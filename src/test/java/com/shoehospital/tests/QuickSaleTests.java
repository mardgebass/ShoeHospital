package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.database.ValuesDB;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.payments.PaymentsPage;
import com.shoehospital.pages.products.QuickSalePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.page;
import static com.shoehospital.database.ValuesDB.falseSku;
import static java.lang.Double.parseDouble;

@DisplayName("Quick Sale")
@ExtendWith({SelenideExtension.class})
public class QuickSaleTests extends BaseTest {
    Faker faker = new Faker();
    String oneDigit = faker.number().numberBetween(1, 9) + faker.numerify(".##");
    String twoDigits = faker.number().numberBetween(10, 99) + faker.numerify(".##");

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
                .clickAddDiscount()
                .chooseDiscountType("Custom $")
                .addDiscountSum(oneDigit)
                .clickSaveDiscount()
                .checkDiscountInRow("$" + oneDigit)
                .payByCash()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkDollarDiscountResult(ValuesDB.getSum(), oneDigit);
    }

    @Test
    @DisplayName("Sale product with % discount (check)")
    public void discountPercentTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .clickAddDiscount()
                .chooseDiscountType("Custom %")
                .addDiscountSum(twoDigits)
                .clickSaveDiscount()
                .checkDiscountInRow(twoDigits + "%")
                .payByCheck()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkPercentDiscountResult(ValuesDB.getSum(), twoDigits);
    }

    @Test
    @DisplayName("Sale products with promocode in % without payment")
    public void discountInRowPromoPercentTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .clickAddDiscount()
                .chooseDiscountType(ValuesDB.getTitlePercent())
                .clickSaveDiscount()
                .checkDiscountInRow(String.format(Locale.ENGLISH, "%(.2f", parseDouble(ValuesDB.getValuePercent())) + "%");
    }

    @Test
    @DisplayName("Sale products with promocode in % (check)")
    public void discountPromoPercentTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .clickAddDiscount()
                .chooseDiscountType(ValuesDB.getTitlePercent())
                .clickSaveDiscount()
                .payByCheck()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkPercentDiscountResult(ValuesDB.getSum(), ValuesDB.getValuePercent());
    }

    @Test
    @DisplayName("Sale products with promocode in $ without payment")
    public void discountInRowPromoDollarTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .clickAddDiscount()
                .chooseDiscountType(ValuesDB.getTitleDollar())
                .clickSaveDiscount()
                .checkDiscountInRow("$" + String.format(Locale.ENGLISH, "%(.2f", parseDouble(ValuesDB.getValueDollar())));
    }

    @Test
    @DisplayName("Sale products with promocode in $ (check)")
    public void discountPromoDollarTest() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getSku())
                .clickAddDiscount()
                .chooseDiscountType(ValuesDB.getTitleDollar())
                .clickSaveDiscount()
                .payByCheck()
                .getHeader()
                .clickPayments();
        page(PaymentsPage.class)
                .checkCheckResult()
                .checkDollarDiscountResult(ValuesDB.getSum(), ValuesDB.getValueDollar());
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
    @DisplayName("Sale product of another store")
    public void saleProductOfAnotherStore() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getFalseSku())
                .clickConfirm()
                .checkSkuField(falseSku);
    }

    @Test
    @DisplayName("Sale unknown product")
    public void saleUnknownProduct() {
        page(QuickSalePage.class)
                .addSKU(ValuesDB.getFalseSku())
                .clickConfirm()
                .setPrice(oneDigit)
                .payByCard()
                .checkAlert();
    }

}