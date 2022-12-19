package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.parseInt;
import static org.openqa.selenium.Keys.ARROW_LEFT;

public class PaymentsPage extends BasePage {

    private static String AMOUNT_RESIDUE;
    private static String AMOUNT_AFTER_POINT;
    private static String HALF_OF_REFUND;
    public static String AMOUNT_REFUND;

    @Step("Click Refund button")
    public PaymentsPage clickRefundButton() {
        $x(".//a[@class='btn btn-light bnt-active-light-primary btn-sm refund-btn 1']").click();
        return new PaymentsPage();
    }

    @Step("Click Refund type")
    public PaymentsPage clickRefundType() {
        $x(".//span[@class='select2-selection select2-selection--single form-select form-select-solid']").click();
        return new PaymentsPage();
    }

    @Step("Click Cash Refund type")
    public PaymentsPage clickCashRefundType() {
        $x(".//span[@class='select2-container select2-container--bootstrap5 select2-container--open']").$(byText("Cash")).click();
        return new PaymentsPage();
    }

    @Step("Click Cash Refund type")
    public PaymentsPage clickCardRefundType() {
        $x(".//span[@class='select2-container select2-container--bootstrap5 select2-container--open']").$(byText("Card")).click();
        return new PaymentsPage();
    }

    @Step("Click Cash Refund type")
    public PaymentsPage clickCheckRefundType() {
        $x(".//span[@class='select2-container select2-container--bootstrap5 select2-container--open']").$(byText("Check")).click();
        return new PaymentsPage();
    }

    @Step("Get amount for refund")
    public PaymentsPage getAmountForRefund() {
        String str = $x(".//span[@class='text-muted fw-bold fs-5']").innerText();
        AMOUNT_REFUND = str.substring(7,str.indexOf(")"));
        AMOUNT_AFTER_POINT = "0" + str.substring(str.indexOf("."),str.indexOf(")"));

        int a = parseInt(str.substring(7,str.indexOf(".")));

        if (a % 2 == 0) {
            HALF_OF_REFUND = String.valueOf(a / 2);
            AMOUNT_RESIDUE = "0";
        } else {
            HALF_OF_REFUND = String.valueOf((a - 1) / 2);
            AMOUNT_RESIDUE = "1";
        }

        return new PaymentsPage();
    }

    @Step("Fill Amount")
    public PaymentsPage fillAmountRefund() {
        $(byName("refund_create_form[totalAmount]")).sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, AMOUNT_REFUND);
        return new PaymentsPage();
    }

    @Step("Fill Shoe Repair")
    public PaymentsPage fillShoeRepair() {
        $("#refund_create_form_shoeRepairAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, AMOUNT_AFTER_POINT);
        return new PaymentsPage();
    }

    @Step("Fill Other Retail")
    public PaymentsPage fillOtherRetail() {
        $("#refund_create_form_otherRetailAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, AMOUNT_RESIDUE);
        return new PaymentsPage();
    }

    @Step("Fill Other Retail")
    public PaymentsPage fillErrorOtherRetail() {
        $("#refund_create_form_otherRetailAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, "3");
        return new PaymentsPage();
    }

    @Step("Fill Sundries")
    public PaymentsPage fillSundries() {
        $("#refund_create_form_sundriesAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, HALF_OF_REFUND);
        return new PaymentsPage();
    }

    @Step("Fill Sales Taxes")
    public PaymentsPage fillSalesTaxes() {
        $("#refund_create_form_salesTaxes").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, HALF_OF_REFUND);
        return new PaymentsPage();
    }

    @Step("Fill Note")
    public PaymentsPage fillNote() {
        $("#refund_create_form_note").sendKeys("Autotest");
        return new PaymentsPage();
    }

    @Step("Click Save refund")
    public PaymentsPage clickSaveRefund() {
        $x(".//button[@class='btn btn-primary w-100px']").click();
        return new PaymentsPage();
    }

    @Step("Click Refunds tab")
    public PaymentsPage clickRefunds() {
        $x(".//div[@class='card-header']").$x(".//a[@class='nav-link']").click();
//        $x(".//a[@class='nav-link']").scrollIntoView(true).click();
        return new PaymentsPage();
    }

    @Step("Check type of payment Cash")
    public PaymentsPage checkCashResult() {
        getRow().shouldHave(Condition.text("Cash"));
        return new PaymentsPage();
    }

    @Step("Check type of payment Check")
    public PaymentsPage checkCheckResult() {
        getRow().shouldHave(Condition.text("Check"));
        return new PaymentsPage();
    }

    @Step("Check type of payment Cash")
    public PaymentsPage checkCardResult() {
        getRow().shouldHave(Condition.text("Card"));
        return new PaymentsPage();
    }

    @Step("Check sum for two products")
    public PaymentsPage checkSum(String price) {
        getRow().shouldHave(Condition.text(price));
        return new PaymentsPage();
    }

    @Step("Check error")
    public PaymentsPage checkEqualError() {

        $("#toast-container").shouldBe(Condition.visible);
        $("#refund-create-modal").$x(".//span[@class='svg-icon svg-icon-2x']").click();
//        $(byText("The sum of the categories must be equal to the total sum")).shouldBe(Condition.visible);
        return new PaymentsPage();
    }

    public SelenideElement getRow() {
        return $$x(".//tr[@class='odd']").get(0);
    }

}
