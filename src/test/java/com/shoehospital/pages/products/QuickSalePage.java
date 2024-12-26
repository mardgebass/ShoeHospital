package com.shoehospital.pages.products;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_LEFT;

public class QuickSalePage extends BasePage {

    @Step("Add SKU {SKU}")
    public QuickSalePage addSKU (String SKU){
        $("#product-sku-input").setValue(SKU).pressEnter();
        sleep(2000);
        return this;
    }

    @Step("alert {payment completed}")
    public QuickSalePage checkAlert(){
        $("#toast-container").shouldHave(Condition.text("Payment completed"));
        return this;
    }

    @Step("alert {Product not found}")
    public QuickSalePage clickConfirm(){
        sleep(2000);
        $("#confirm_custom_product").$("#confirm_custom_product_yes").click();
        return this;
    }

    @Step("barcode in SKU field")
    public void checkSkuField(String Sku){
        $x(".//input[@class='form-control form-control-solid']").shouldHave(exactValue(Sku));
    }

    @Step("Edit Quantity to 2")
    public QuickSalePage editQuantity() {
        $x(".//input[@class='product-quantity-input form-control quantityMask']").sendKeys(Keys.DELETE, "2");
        return this;
    }

    @Step("Pay by Cash")
    public QuickSalePage payByCash() {
        $(byText("Cash")).click();
        $("#payment_cash_modal").$(byText("Received")).click();
        return this;
    }

    @Step("Pay by Check")
    public QuickSalePage payByCheck () {
        $(byText("Check")).click();
        $("#payment_check_modal").$(byText("Received")).click();
        return this;
    }

    @Step("Pay by Card")
    public QuickSalePage payByCard () {
        $(byText("Credit Card")).click();
        $$x(".//label[@class='radio me-3']").get((int)(Math.random() * 5)).click();
        $("#card-pay-received-btn").click();
        return this;
    }

    @Step("Click plus icon")
    public QuickSalePage clickAddDiscount() {
        $x(".//button[@class='cart-apply-discount-btn btn btn-icon btn-sm btn-bg-light text-hover-primary me-3']").click();
        return this;
    }

    @Step("Choose Discount Type")
    public QuickSalePage chooseDiscountType(String type) {
        $("#select2-discount_form_promocode-container").click();
        $("#select2-discount_form_promocode-results").$(byText(type)).click();
        return this;
    }

    @Step("Add sum of discount")
    public QuickSalePage addDiscountSum(String percent) {
        $("#discount_form_value").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        $("#discount_form_value").sendKeys(percent);
        return this;
    }

    public QuickSalePage clickSaveDiscount() {
        $x(".//button[@class='btn btn-primary w-100px pe-5 ps-5']").click();
        return this;
    }

    public QuickSalePage checkDiscountInRow(String priceDiscount) {
        $x(".//span[@class='text-success']").shouldHave(text("-" + priceDiscount));
        return this;
    }

    public QuickSalePage setPrice(String price) {
        $x(".//input[@class='custom-price-field form-control']").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, price);
        return this;
    }
}
