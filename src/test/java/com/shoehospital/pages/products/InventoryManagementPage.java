package com.shoehospital.pages.products;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class InventoryManagementPage extends BasePage {

    @Step("Click Add product")
    public void clickAddProduct() {
        $("#createNewProduct").click();
    }

    @Step("Check sku and extraSku")
    public void checkSku(String sku, String extraSku) {
        sleep(500);
        $("#product_search_search").setValue(sku);
        sleep(500);
        $(byText(sku + "; " + extraSku)).exists();
    }

}
