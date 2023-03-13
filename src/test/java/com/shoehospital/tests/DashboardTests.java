package com.shoehospital.tests;

import com.shoehospital.database.AveragesDB;
import com.shoehospital.database.CountDB;
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
    @DisplayName("Tablet Items on Shelf")
    public void pendingPickUpTicketTest() {
        page(DashboardPage.class)
                .checkItemsOnShelf(CountDB.getOnShelf());
    }

    @Test
    @DisplayName("Tablet Repairs in progress")
    public void inProgressTabletTest() {
        page(DashboardPage.class)
                .checkTicketsInProgress(CountDB.getInProgress());
    }

    @Test
    @DisplayName("Tablet Tickets Submitted Today")
    public void TicketsTodayTabletTest() {
        page(DashboardPage.class)
                .checkTicketsToday(CountDB.getToday());
    }

    @Test
    @DisplayName("Average price per ticket")
    public void AverageTest() {
        page(DashboardPage.class)
                .checkAveragePrice(AveragesDB.getAverageValue());
    }

    @Test
    @DisplayName("Tablet Tickets Without Details")
    public void WithoutDetailsTabletTest() {
        page(DashboardPage.class)
                .checkTicketsWithoutDetails(CountDB.getWithoutDetails());
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
