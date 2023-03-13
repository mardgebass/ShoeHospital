package com.shoehospital.pages.tickets;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;

public class CartPage extends BasePage {

    @Step("Pay by Cash")
    public CartPage payByCash() {
        $(byText("Cash")).click();
        $("#payment_cash_modal").$(byText("Received")).click();
        return this;
    }

    @Step("Pay by Check")
    public CartPage payByCheck() {
        $(byText("Check")).click();
        $("#payment_check_modal").$(byText("Received")).click();
        return this;
    }

    @Step("Pay by Card")
    public CartPage payByCard() {
        int row = (int)(Math.random() * 5);
        $(byText("Credit Card")).click();
        $$x(".//label[@class='radio me-3']").get(row).click();
        $("#card-pay-received-btn").click();
        return this;
    }

    @Step("Choose Discount Type")
    public CartPage chooseDiscountType(String type) {
        $x(".//button[@class='cart-apply-discount-btn btn btn-icon btn-sm btn-bg-light text-hover-primary me-3']").click();
        $x(".//span[@class='select2-selection select2-selection--single form-select']").click();
        $("#select2-discount_form_promocode-results").$(byText(type)).click();
        return this;
    }

    @Step("Add sum of discount")
    public CartPage addDiscountSum(String percent) {
        $("#discount_form_value").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, percent);
        $x(".//button[@class='btn btn-primary w-100px pe-5 ps-5']").click();
        return this;
    }

    public CartPage checkDiscount(String discount) {
        $x(".//span[@class='text-success']").shouldHave(text(discount));
        return this;
    }

}
