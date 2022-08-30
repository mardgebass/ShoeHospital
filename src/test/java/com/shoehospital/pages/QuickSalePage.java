package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class QuickSalePage extends BasePage{

    @Step("Add SKU {SKU}")
    public QuickSalePage addSKU (String SKU){
        $("#product-sku-input").setValue(SKU).pressEnter();
        return this;
    }

    @Step("Check alert {paymentCompleted}")
    public QuickSalePage checkAlert(String paymentCompleted){
        $("#toast-container").shouldHave(Condition.text(paymentCompleted));
        return this;
    }

    @Step("Check empty field")
    public void checkEmptyField (){
        $("#product-sku-input").shouldHave(Condition.empty);
        $x(".//button[@class='toast-close-button']").click();
    }

    @Step("Edit Quantity to 2")
    public QuickSalePage editQuantity() {
        $x(".//input[@class='product-quantity-input form-control quantityMask']").setValue("2");
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
        $$x(".//label[@class='radio me-3']").get(0).click();
        $("#card-pay-received-btn").click();
        return this;
    }

}
