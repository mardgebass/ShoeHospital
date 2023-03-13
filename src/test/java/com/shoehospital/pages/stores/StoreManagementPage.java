package com.shoehospital.pages.stores;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StoreManagementPage extends BasePage {

    @Step("Click Add Store")
    public void clickAddStore (){
        $x(".//a[@class='btn btn-primary']").click();
    }

    @Step("Click Pagination")
    public StoreManagementPage clickPagination() {
        $("#store_list_length").click();
        $(byValue("100")).click();
        return this;
    }

    public void checkNewStore(String shortName) {
        $("#store_list").shouldHave(Condition.text(shortName));
    }
}
