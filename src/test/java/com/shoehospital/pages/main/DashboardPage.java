package com.shoehospital.pages.main;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage extends BasePage {

    public void checkWatching(String Id) {
        $x(".//tbody[@class='text-gray-600 fw-bold']").shouldHave(Condition.text(Id));
    }

    @Step("Items on Shelf after test")
    public void checkItemsOnShelf(String value) {
        $x(".//span[contains(text(), 'Items on Shelf')]").closest("div").closest("div").$x(".//span[@class='fw-bold fs-3x text-gray-800 lh-1 ls-n2']").shouldHave(text(value));
    }

    @Step("Tickets In Progress after test")
    public void checkTicketsInProgress(String value) {
        $x(".//span[contains(text(), 'Repairs in Progress')]").closest("div").closest("div").$x(".//span[@class='fw-bold fs-3x text-gray-800 lh-1 ls-n2']").shouldHave(text(value));
    }

    @Step("Tickets submitted today after test")
    public void checkTicketsToday(String value) {
        $x(".//span[contains(text(), 'Tickets submitted today')]").closest("div").closest("div").$x(".//span[@class='fw-bold fs-3x text-gray-800 lh-1 ls-n2']").shouldHave(text(value));
    }

    public void checkAveragePrice(String average) {
        String count = average.substring(average.indexOf("e=") + 2, average.indexOf(", max")).replace(',', '.');
        String result = String.format(Locale.ENGLISH, "%.2f", Double.parseDouble(count));
        $x(".//span[contains(text(), 'Average price per ticket')]").closest("div").closest("div").$x(".//span[@class='fw-bold fs-3x text-gray-800 lh-1 ls-n2']").shouldHave(text(result));
    }

    @Step("Tickets Without Details after test")
    public void checkTicketsWithoutDetails(String value) {
        $x(".//span[contains(text(), 'Tickets without details')]").closest("div").closest("div").$x(".//span[@class='fw-bold fs-3x text-gray-800 lh-1 ls-n2']").shouldHave(text(value));
    }

}
