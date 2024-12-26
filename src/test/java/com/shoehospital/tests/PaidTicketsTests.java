package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.tickets.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Tickets with payment")
@ExtendWith({SelenideExtension.class})
public class PaidTicketsTests extends BaseTest{

    Faker faker = new Faker();
    String price = faker.numerify("3##");
    String discount = faker.numerify("#");
    String id = faker.numerify("5000####");
    String phone = "2562690950";
    
    @BeforeEach
    public void createTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillFormExistingPhone(phone)
                .clickContinue()
                .clickYes();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addDate()
                .addPrice(price)
                .clickFinish();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("In progress ticket paid by Check")
    public void inProgressCheckTest() {
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCheck();
        page(TicketPage.class)
                .checkStatusPaid();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Pending Picked up ticket paid by card")
    public void pendingPickedUpCardTest() {
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickRFPU()
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCard();
        page(TicketPage.class)
                .checkStatusPaid();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Picked up ticket")
    public void pickedUpTicketTest() {
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCard();
        page(TicketPage.class)
                .clickRFPU()
                .clickPickedUp()
                .checkPickedUpHistory()
                .checkStatusPickedUp();
    }

    
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Individual $ discount")
    public void applyDollarTest() {
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .chooseDiscountType("Custom $")
                .addDiscountSum(discount)
                .checkDiscount("-$1" + discount)
                .payByCash();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Individual % discount")
    public void applyPercentTest() {
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .chooseDiscountType("Custom %")
                .addDiscountSum(discount)
                .checkDiscount("-1" + discount + ".00%")
                .payByCheck();
    }
}


