package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtensionTicket;
import com.shoehospital.pages.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("New order with payment")
@ExtendWith({SelenideExtensionTicket.class})
public class PaidTicketsTests {

    Faker faker = new Faker();
    String price = faker.numerify("##");
    String id = faker.numerify("10000###");
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String phone = faker.numerify("9#########");
    String email = faker.bothify("??????##@gmail.com");


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Picked up ticket with prepayment by check")
    public void completeOrderByCheckTest() {

        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addDate()
                .addPrice(price)
                .clickFinish();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCheckBefore();
        page(MainPage.class)
                .getHeader()
                .clickSearch()
                .enterSearchRequest(id)
                .clickTicketInResults(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCheck();
        page(TicketPage.class)
                .checkStatusPaid()
                .clickRFPU()
                .clickPickedUp();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCheckResult(price);
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Picked up ticket with prepayment in cash")
    public void completeOrderByCashTest() {

        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .clickCWR()
                .addPrice(price)
                .clickFinish();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashBefore();
        page(MainPage.class)
                .getHeader()
                .clickSearch()
                .enterSearchRequest(id)
                .clickTicketInResults(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCash();
        page(TicketPage.class)
                .checkStatusPaid()
                .clickRFPU()
                .clickPickedUp();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Picked up ticket with prepayment by card")
    public void completeOrderByCardTest() {

        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addPrice(price)
                .clickFinish();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardBefore();
        page(MainPage.class)
                .getHeader()
                .clickSearch()
                .enterSearchRequest(id)
                .clickTicketInResults(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCard();
        page(TicketPage.class)
                .checkStatusPaid()
                .clickRFPU()
                .clickPickedUp();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardResult(price);
    }

}


