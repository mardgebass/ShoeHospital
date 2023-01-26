package com.shoehospital.pages;

import io.qameta.allure.Step;

import java.security.SecureRandom;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_RIGHT;

public class CreateProductPage {

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

    @Step("Select category")
    public CreateProductPage selectRegion() {
        $x(".//span[@class='select2-selection select2-selection--multiple form-select']").click();
        $$x(".//li[@class='select2-results__option select2-results__option--selectable']").get(random.nextInt(2)).click();
        return this;
    }

    @Step("Add price")
    public CreateProductPage addPrice(String price) {
        $("#edit_product_form_retailPrice").sendKeys(ARROW_RIGHT, price);
        return this;
    }

    @Step("Click Save")
    public CreateProductPage clickSave() {
        $("#edit_product_form_save").click();
        return this;
    }

}
