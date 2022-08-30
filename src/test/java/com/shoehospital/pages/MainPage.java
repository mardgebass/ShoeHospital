package com.shoehospital.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BasePage {

    private static String COUNT_TICKETS_TODAY;
    private static String COUNT_IN_PROGRESS;
    private static String WITHOUT_DETAILS;

    @Step("Check Tickets Today before test")
    public MainPage getTicketsToday() {
        COUNT_TICKETS_TODAY = getToday().get(2).innerText();
        return new MainPage();
    }

    @Step("Check Tickets Today after test")
    public MainPage checkTicketsTodayAfter() {
        int expectedResult = Integer.parseInt(COUNT_TICKETS_TODAY) + 1;
        getToday().get(2).shouldHave(text(Integer.toString(expectedResult)));
        return new MainPage();
    }

    @Step("Check Tickets In Progress before test")
    public MainPage getTicketsInProgress() {
        COUNT_IN_PROGRESS = getToday().get(1).innerText();
        return new MainPage();
    }

    @Step("Check Tickets In Progress after test")
    public MainPage checkTicketsInProgress() {
        int expectedResult = Integer.parseInt(COUNT_IN_PROGRESS) + 1;
        getToday().get(1).shouldHave(text(Integer.toString(expectedResult)));
        return new MainPage();
    }

    @Step("Check Tickets Without Details before test")
    public MainPage getTicketsWithoutDetails() {
        WITHOUT_DETAILS = getToday().get(4).innerText();
        return new MainPage();
    }

    @Step("Check Tickets Without Details after test")
    public MainPage checkTicketsWithoutDetails() {
        int expectedResult = Integer.parseInt(WITHOUT_DETAILS) + 1;
        getToday().get(4).shouldHave(text(Integer.toString(expectedResult)));
        return new MainPage();
    }

    private ElementsCollection getToday() {
        return $$x(".//span[@class='fw-bold fs-3x text-gray-800 lh-1 ls-n2']");
    }

}
