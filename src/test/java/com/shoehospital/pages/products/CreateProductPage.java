package com.shoehospital.pages.products;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import java.security.SecureRandom;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_LEFT;

public class CreateProductPage extends BasePage {

    SecureRandom random = new SecureRandom();

    @Step("Add product SKU")
    public CreateProductPage addSKU(String sku) {
        $("#edit_product_form_barcodes_0_barcode").setValue(sku).pressEnter();
        return this;
    }

    @Step("Add extra product SKU")
    public CreateProductPage addExtraSKU(String sku) {
        $("#edit_product_form_barcodes_1_barcode").setValue(sku);
        return this;
    }

    @Step("Add title")
    public CreateProductPage addTitle(String title) {
        $("#edit_product_form_title").setValue(title);
        return this;
    }

    @Step("Add description")
    public CreateProductPage addDescription(String description) {
        $("#edit_product_form_description").setValue(description);
        return this;
    }

    @Step("Select category")
    public CreateProductPage selectCategory() {
        $("#select2-edit_product_form_productCategory-container").click();
        $$x(".//li[@class='select2-results__option select2-results__option--selectable']").get(random.nextInt(6)).click();
        return this;
    }

    @Step("Select category")
    public CreateProductPage selectManufacturer() {
        $("#select2-edit_product_form_manufacturer-container").click();
        $$x(".//li[@class='select2-results__option select2-results__option--selectable']").get(random.nextInt(2)).click();
        return this;
    }

    @Step("Select region")
    public CreateProductPage selectRegion() {
        $x(".//span[@class='select2-selection select2-selection--multiple form-select']").click();
        $("#select2-edit_product_form_regions-results").$(byText("Austin/San Antonio")).click();
        return this;
    }

    @Step("Add price")
    public CreateProductPage addPrice(String price) {
        $("#edit_product_form_retailPrice").sendKeys(ARROW_LEFT, price);
        return this;
    }

    @Step("Click Save")
    public void clickSave() {
        $("#edit_product_form_save").click();
    }

    public void checkAlertBarcodeExists() {
        $("#toast-container").shouldHave(text("This barcode already exists"));
    }
}
