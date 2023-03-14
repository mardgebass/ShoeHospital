package com.shoehospital.pages.payments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import java.util.Locale;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;
import static org.openqa.selenium.Keys.ARROW_LEFT;

public class PaymentsPage extends BasePage {

    @Step("Scroll to the top")
    public PaymentsPage scrollTop() {
        sleep(5000);
        $("#inputGroup-sizing-default-search").scrollIntoView("{block: \"center\"}");
        return this;
    }

    @Step("Click Refund button")
    public PaymentsPage clickRefund(String id, String amount) {
//        $("#payment_search_search").setValue(amount).pressEnter();
        $(byId(id)).find(byText("Refund")).click();
        return this;
    }

    @Step("Fill Refund popup")
    public PaymentsPage fillRefundPopup(String amount, String categories, String type) {
        $(byName("refund_create_form[totalAmount]")).sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, amount);

        String thirdRefund = convert(Double.parseDouble(amount) / 6);
        String residue = convert(Double.parseDouble(amount) - Double.parseDouble(thirdRefund) * 3);

        $("#refund_create_form_shoeRepairAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, residue);
        $("#refund_create_form_otherRetailAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);
        $("#refund_create_form_sundriesAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);
        $("#refund_create_form_salesTaxes").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);

        $x(".//span[@class='select2-selection select2-selection--single form-select form-select-solid']").click();
        $x(".//span[@class='select2-container select2-container--bootstrap5 select2-container--open']").$(byText(type)).click();

        $("#refund_create_form_note").sendKeys("Autotest");
        return this;
    }

    @Step("Click Save")
    public PaymentsPage clickSave () {
        $x(".//button[@class='btn btn-primary w-100px']").click();
        return this;
    }

    @Step("Click Refunds tab")
    public void clickRefunds () {
        $x(".//div[@class='card-header']").$x(".//a[@class='nav-link']").click();
    }

    @Step("Check type of payment Cash")
    public PaymentsPage checkCashResult () {
        getRow().shouldHave(Condition.text("Cash"));
        return new PaymentsPage();
    }

    @Step("Check type of payment Check")
    public PaymentsPage checkCheckResult () {
        getRow().shouldHave(Condition.text("Check"));
        return new PaymentsPage();
    }

    @Step("Check type of payment Cash")
    public PaymentsPage checkCardResult () {
        getRow().shouldHave(Condition.text("Card"));
        return new PaymentsPage();
    }

    @Step("Check sum for two units of the same product")
    public void checkSum (String price){
        String sum = convert((parseDouble(price)) * 2 * 1.0825);
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("Check sum for one product")
    public void checkSumForOne (String price){
        String sum = convert((parseDouble(price)) * 1.0825);
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("Check sum for two units of the same product and one another")
    public void checkDoubleSum (String firstPrice, String secondPrice){
        String sum = convert (((parseDouble(firstPrice)) * 2 + (parseDouble(secondPrice))) * 1.0825);
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("Check error")
    public void checkEqualError () {
        $("#toast-container").shouldBe(Condition.visible);
        $("#refund-create-modal").$x(".//span[@class='svg-icon svg-icon-2x']").click();
    }

    public SelenideElement getRow () {
        return $$x(".//tr[@class='odd']").get(0);
    }

    @Step("Check value in DCR after applying % discount")
    public void checkPercentDiscountResult(String price, String discount) {
        Double a = parseDouble(price) / 100 * parseDouble(discount);
        String sum = convert(((parseDouble(price) - parseDouble(convert(a))) * 1.0825));
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("Check value in DCR after applying $ discount")
    public void checkDollarDiscountResult(String price, String discount) {
        String sum = convert((parseDouble(price) - parseDouble(discount)) * 1.0825);
        getRow().shouldHave(Condition.text(sum));
    }

    public String convert (Double value) {
        return String.format(Locale.ENGLISH, "%(.2f", (value));
    }

    }
