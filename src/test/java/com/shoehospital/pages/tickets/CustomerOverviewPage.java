package com.shoehospital.pages.tickets;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CustomerOverviewPage extends BasePage {

    @Step("Click ticket Id")
    public void clickIdLink(String id) {
        $x("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div/div[1]/div/div[1]/div/table/thead/tr/th[5]").click();
        $x("/html/body/div[1]/div/div/div[2]/div/div/div[1]/div/div[2]/form/div/div[1]/div/div[1]/div/table/thead/tr/th[5]").click();
        $(byText(id)).click();
    }

}
