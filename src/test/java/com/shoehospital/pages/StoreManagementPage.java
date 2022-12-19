package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StoreManagementPage extends BasePage{

    @Step("Click Add Store")
    public StoreManagementPage clickAddStore (){
        $x(".//a[@class='btn btn-primary']").click();
        return this;
    }

    @Step("Click Pagination")
    public StoreManagementPage clickPagination() {
        $("#store_list_length").click();
        $(byValue("100")).click();
        return this;
    }

    public StoreManagementPage checkNewStore(String shortName) {
        $("#store_list").shouldHave(Condition.text(shortName));
        return this;
    }
}
