package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ARROW_RIGHT;

public class InventoryStorePage extends BasePage {

    String skuCheck;

    @Step("Click Add product")
    public InventoryStorePage clickAddProduct(String sku) {
        $x(".//td[contains(text(), '" + sku + "')]").closest("tr").$x(".//a[@class='btn btn-icon btn-bg-light text-lg-hover-primary btn-sm refund-btn editStoreProductBtn']").click();
        skuCheck = sku;
        return this;
    }

    @Step("Set quantity")
    public InventoryStorePage addQuantity(String quantity) {
        $("#store_product_edit_quantity").sendKeys(ARROW_RIGHT, quantity);
        return this;
    }

    @Step("Set price")
    public InventoryStorePage addPrice(String price) {
        $("#store_product_edit_salePrice").sendKeys(ARROW_RIGHT, price);
        return this;
    }

    @Step("Click Save")
    public InventoryStorePage clickSave() {
        $("#submitStoreProduct").click();
        return this;
    }

    @Step("Check quantity and price of the product")
    public InventoryStorePage checkAdding(String quantity, String price) {
        $x(".//td[contains(text(), '" + skuCheck + "')]").closest("tr").shouldHave(Condition.text(quantity));
        $x(".//td[contains(text(), '" + skuCheck + "')]").closest("tr").shouldHave(Condition.text(price));
        return this;
    }

}
