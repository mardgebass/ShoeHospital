package com.shoehospital.pages.products;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ARROW_RIGHT;

public class InventoryStorePage extends BasePage {

    @Step("Click pencil for adding a product")
    public InventoryStorePage clickEditPencil(String sku) {
        $("#product_search_search").setValue(sku);
        $x(".//td[contains(text(), '" + sku + "')]").closest("tr").$x(".//a[@class='btn btn-icon btn-bg-light text-lg-hover-primary btn-sm refund-btn editStoreProductBtn']").click();
        return this;
    }

    @Step("Set quantity in popup")
    public InventoryStorePage addQuantity(String quantity) {
        $("#store_product_edit_quantity").sendKeys(ARROW_RIGHT, quantity);
        return this;
    }

    @Step("Set price in popup")
    public InventoryStorePage addPrice(String price) {
        $("#store_product_edit_salePrice").sendKeys(price);
        return this;
    }

    @Step("Click Save")
    public InventoryStorePage clickSave() {
//        $("#submitStoreProduct").click();
        $x(".//button[@class='btn btn-primary w-100px pe-5 ps-5']").click();
        return this;
    }

    @Step("quantity and price of the product")
    public void checkAdding(String falseSku, String quantity) {
        $x(".//td[contains(text(), '" + falseSku + "')]").closest("tr").shouldHave(Condition.text(quantity));
    }

}
