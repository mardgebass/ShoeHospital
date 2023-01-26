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

@DisplayName("New order without payment")
@ExtendWith({SelenideExtension.class})
public class UnpaidTicketsTests {

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String phone = faker.numerify("9#########");
    String price = faker.numerify("###");
    String email = faker.bothify("??????##@me.com");
    String id = faker.numerify("20000###");

    protected static String existingPhone = "9385297121";

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("In-progress ticket")
    public void inProgressTicketTest() {

        page(MainPage.class)
                .getTicketsInProgress()
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
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
                .checkTicketsInProgress();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Pending pick up ticket")
    public void pendingPickUpTicketTest() {

        page(MainPage.class)
                .getTicketsToday()
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillForm(phone, email, firstName, lastName)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addPrice(price)
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickRFPU()
                .checkStatusRFPU()
                .checkRFPUHistory();
        page(MainPage.class)
                .getHeader()
                .clickLogo()
                .checkTicketsTodayAfter();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Ticket deletion")
    public void deleteTicketTest() {

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
                .addPrice(price)
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .clickDelete()
                .clickConfirmDeletion();
        page(MainPage.class)
                .getHeader()
                .clickLogo()
                .checkTicketsTodaySameValue()
                .checkTicketsInProgressSameValue()
                .checkTicketsWithoutDetailsSameValue()
                .getHeader()
                .clickSearch()
                .enterSearchRequest(id)
                .checkSearchResults();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Ticket for existing customer's phone")
    public void createOrderForExistingPhoneTest() {

        page(MainPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillFormExistingPhone(existingPhone)
                .clickContinue()
                .clickYes();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addPrice(price)
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .checkDropSentHistory();
    }

}


