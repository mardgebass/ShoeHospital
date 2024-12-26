package com.shoehospital.tests;

import com.shoehospital.database.AveragePriceOnDashboard;
import com.shoehospital.database.TicketsAmountOnDashboard;
import com.shoehospital.database.ValuesDB;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.tickets.TicketPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;
import static com.shoehospital.database.ValuesDB.notWatching;

@DisplayName("Tablet's tests on Dashboard")
@ExtendWith({SelenideExtension.class})
public class DashboardTests extends BaseTest {

    @Test
    @DisplayName("Items on Shelf")
    public void pendingPickUpTicketTest() {
        page(DashboardPage.class)
                .checkItemsOnShelf(TicketsAmountOnDashboard.getOnShelf());
    }

    @Test
    @DisplayName("Repairs in progress")
    public void inProgressTabletTest() {
        page(DashboardPage.class)
                .checkTicketsInProgress(TicketsAmountOnDashboard.getInProgress());
    }

    @Test
    @DisplayName("Tickets Submitted Today")
    public void TicketsTodayTabletTest() {
        page(DashboardPage.class)
                .checkTicketsToday(TicketsAmountOnDashboard.getToday());
    }

    @Test
    @DisplayName("Average price per ticket")
    public void AverageTest() {
        page(DashboardPage.class)
                .checkAveragePrice(AveragePriceOnDashboard.getAverageValue());
    }

    @Test
    @DisplayName("Tickets Without Details")
    public void WithoutDetailsTabletTest() {
        page(DashboardPage.class)
                .checkTicketsWithoutDetails(TicketsAmountOnDashboard.getWithoutDetails());
    }

    @Test
    @DisplayName("Watching ticket in Watched tickets")
    public void watchingTest() {
        page(DashboardPage.class)
                .getHeader()
                .enterSearchRequest(ValuesDB.getNotWatching());
        page(TicketPage.class)
                .clickWatch()
                .getHeader()
                .clickLogo();
        page(DashboardPage.class)
                .checkWatching(notWatching);
    }

}
