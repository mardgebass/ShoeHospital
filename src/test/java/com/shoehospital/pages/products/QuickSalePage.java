package com.shoehospital.pages.products;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_LEFT;
import static org.openqa.selenium.Keys.DELETE;

public class QuickSalePage extends BasePage {

    @Step("Add SKU {SKU}")
    public QuickSalePage addSKU (String SKU){
        $("#product-sku-input").setValue(SKU).pressEnter();
        return this;
    }

    @Step("Check alert {payment completed}")
    public QuickSalePage checkAlert(){
        $("#toast-container").shouldHave(Condition.text("Payment completed"));
        return this;
    }

    @Step("Check alert {Product not found}")
    public QuickSalePage checkErrorAlert(){
        $("#toast-container").shouldHave(Condition.text("Product not found"));
        return this;
    }

    @Step("Check empty field")
    public void checkEmptyField (){
        $("#product-sku-input").shouldHave(Condition.empty);
        $x(".//button[@class='toast-close-button']").click();
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
    public QuickSalePage clickPlus() {
        $x(".//button[@class='cart-apply-discount-btn btn btn-icon btn-sm btn-bg-light text-hover-primary me-3']").click();
        return this;
    }

    @Step("Choose Discount Type")
    public QuickSalePage chooseDiscountType(String type) {
        $x(".//span[@class='select2-selection select2-selection--single form-select']").click();
        $("#select2-discount_form_promocode-results").$(byText(type)).click();
        return this;
    }

    @Step("Add sum of discount")
    public QuickSalePage addDiscountSum(String percent) {
        $("#discount_form_value").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, DELETE, percent);
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

}
