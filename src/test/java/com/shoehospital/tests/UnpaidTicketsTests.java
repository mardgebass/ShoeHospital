package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.database.ValuesDB;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.tickets.CustomerOverviewPage;
import com.shoehospital.pages.tickets.FirstOrderStepPage;
import com.shoehospital.pages.tickets.SecondOrderStepPage;
import com.shoehospital.pages.tickets.TicketPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("New order without payment")
@ExtendWith({SelenideExtension.class})
public class UnpaidTicketsTests extends BaseTest {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String phone = faker.numerify("77########");
    String price = faker.numerify("##");
    String email = faker.bothify("??????##@example.com");
    String id = faker.numerify("7000####");

    @Test
//    @RepeatedTest(6)
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("In-progress Ticket")
    public void inProgressTicketTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillPhone(phone)
                .clickContinue();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addPrice(price)
                .clickFinish();
        page(CustomerOverviewPage.class)
                .clickIdLink(id);
        page(TicketPage.class)
                .checkCreatedHistory()
                .checkPaymentStatusPending()
                .checkStatusInProgress();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Pending Pick up Ticket")
    public void pendingPickUpTicketTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillEmail(email)
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
    }

    @Test
    @DisplayName("Watching Ticket")
    public void watchTicketTest() {
        page(DashboardPage.class)
                .getHeader()
                .enterSearchRequest(ValuesDB.getNotWatching());
        page(TicketPage.class)
                .clickWatch()
                .checkWatching();
    }

    @Test
    @DisplayName("Ticket Deletion")
    public void deleteTicketTest() {
        page(DashboardPage.class)
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
                .clickConfirmDeletion()
                .getHeader()
                .checkSearchResults(id);
    }

}


