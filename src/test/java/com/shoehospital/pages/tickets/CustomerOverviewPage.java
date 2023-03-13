package com.shoehospital.pages.tickets;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CustomerOverviewPage extends BasePage {

    @Step("Click ticket Id")
    public void clickIdLink(String id) {
        $(byText(id)).click();
    }

}
