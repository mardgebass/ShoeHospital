package com.shoehospital.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class InventoryManagementPage extends BasePage {

    @Step("Click Add product")
    public InventoryManagementPage clickAddProduct() {
        $("#createNewProduct").click();
        return this;
    }

    @Step("Check sku and extraSku")
    public InventoryManagementPage checkSku(String sku, String extraSku) {
        $(byText(sku + "; " + extraSku)).click();
        return this;
    }

}
