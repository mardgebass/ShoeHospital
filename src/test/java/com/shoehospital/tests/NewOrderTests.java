package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("New Order")
@ExtendWith({SelenideExtension.class})
public class NewOrderTests {

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String phone = faker.numerify("##########");
    String price = faker.numerify("##");
    String email = faker.bothify("????????###@gmail.com");
    String id = faker.numerify("111#####");

    protected static String existingPhone = "8000000050";
    protected static String existingEmail = "natalia.r2@zimalab.com";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ticket creation")
    public void createCustomerTicketTest() {

            page(MainPage.class)
                    .getTicketsToday()
                    .getTicketsInProgress()
                    .getTicketsWithoutDetails()
                    .getHeader()
                    .clickNewOrder();
            page(FirstOrderStepPage.class)
                    .fillForm(phone, email, firstName, lastName)
                    .clickContinue();
            page(SecondOrderStepPage.class)
                    .addTicketId(id)
                    .clickDate()
                    .addDate()
                    .addPrice(price)
                    .clickFinish();
            page(CustomerOverviewPage.class)
                    .clickIdLink(id);
            page(TicketPage.class)
                    .checkCreatedHistory()
                    .checkStatusPending()
                    .checkStatusInProgress()
                    .checkDropSentHistory();
            page(MainPage.class)
                    .getHeader()
                    .clickLogo()
                    .checkTicketsTodayAfter()
                    .checkTicketsInProgress()
                    .checkTicketsWithoutDetails();
        }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ticket for existing customer's phone")
    public void createOrderForExistingPhoneTest() {

        page(MainPage.class)
                .getTicketsToday()
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillFormExistingPhone(existingPhone)
                .clickContinue()
                .clickYes();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addPrice(price)
                .clickCWR()
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .checkCreatedHistory()
                .checkStatusPending()
                .checkStatusInProgress()
                .checkDropSentHistory();
        page(MainPage.class)
                .getHeader()
                .clickLogo()
                .checkTicketsTodayAfter();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ticket for existing customer's email")
    public void createOrderForExistingEmailTest() {
        page(MainPage.class)
                .getTicketsToday()
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillFormExistingEmail(phone, existingEmail)
                .clickContinue()
                .clickYes();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addPrice(price)
                .clickCWR()
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .checkCreatedHistory()
                .checkStatusPending()
                .checkStatusInProgress()
                .checkDropSentHistory();
        page(MainPage.class)
                .getHeader()
                .clickLogo()
                .checkTicketsTodayAfter();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Completed ticket with prepayment - check")
    public void completeOrderByCheckTest() {

            page(MainPage.class)
                    .getTicketsToday()
                    .getHeader()
                    .clickDCR();
            page(DCRPage.class)
                    .checkCheckBefore();
            page(MainPage.class)
                    .getHeader()
                    .clickNewOrder();
            page(FirstOrderStepPage.class)
                    .fillForm(phone, email, firstName, lastName)
                    .clickContinue();
            page(SecondOrderStepPage.class)
                    .addTicketId(id)
                    .clickDate()
                    .addDate()
                    .addPrice(price)
                    .clickFinish();
            page(CustomerOverviewPage.class)
                    .clickToPay();
            page(CartPage.class)
                    .payByCheck();
            page(CustomerOverviewPage.class)
                    .clickIdLink(id);
            page(TicketPage.class)
                    .checkStatusPaid()
                    .clickRFPU()
                    .checkStatusRFPU()
                    .checkRFPUHistory()
                    .clickComplete()
                    .checkStatusCompleted()
                    .checkCompletedHistory();
            page(MainPage.class)
                    .getHeader()
                    .clickDCR();
            page(DCRPage.class)
                    .checkCheckResult(price);
            page(MainPage.class)
                    .getHeader()
                    .clickLogo()
                    .checkTicketsTodayAfter();
        }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Completed ticket with prepayment - cash")
    public void completeOrderByCashTest() {

        page(MainPage.class)
                .getTicketsToday()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashBefore();
        page(MainPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .clickDate()
                .addDate()
                .addPrice(price)
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickToPay();
        page(CartPage.class)
                .payByCash();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .checkStatusPaid()
                .clickRFPU()
                .checkStatusRFPU()
                .checkRFPUHistory()
                .clickComplete()
                .checkStatusCompleted()
                .checkCompletedHistory();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashResult(price);
        page(MainPage.class)
                .getHeader()
                .clickLogo()
                .checkTicketsTodayAfter();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Completed ticket with prepayment - card")
    public void completeOrderByCardTest() {

        page(MainPage.class)
                .getTicketsToday()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardBefore();
        page(MainPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .clickDate()
                .addDate()
                .addPrice(price)
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickToPay();
        page(CartPage.class)
                .payByCard();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .checkStatusPaid()
                .clickRFPU()
                .checkStatusRFPU()
                .checkRFPUHistory()
                .clickComplete()
                .checkStatusCompleted()
                .checkCompletedHistory();
        page(MainPage.class)
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardResult(price);
        page(MainPage.class)
                .getHeader()
                .clickLogo()
                .checkTicketsTodayAfter();
    }

}
