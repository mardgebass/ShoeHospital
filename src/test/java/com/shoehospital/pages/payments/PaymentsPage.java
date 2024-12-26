package com.shoehospital.pages.payments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;
import static org.openqa.selenium.Keys.ARROW_LEFT;

public class PaymentsPage extends BasePage {

    private final double taxRate = 1.0825;

    @Step("Scroll to the top")
    public PaymentsPage scrollTop() {
        $("#inputGroup-sizing-default-search").scrollIntoView("{block: \"center\"}");
        return this;
    }

    @Step("Click Refund button")
    public PaymentsPage clickRefund(String id, String amount) {
        $("#payment_search_search").setValue(amount).pressEnter();
        $(byId(id)).find(byText("Refund")).click();
        return this;
    }

    @Step("Fill Refund popup with Shoe Repair")
    public PaymentsPage fillShoeRepair(String categories) {
        String thirdRefund = rounding(Double.parseDouble(categories) * 0.92378753);
        $("#refund_create_form_shoeRepairAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);
        return this;
    }

    @Step("Fill Refund popup")
    public PaymentsPage fillRefundPopup(String categories) {

        String thirdRefund = rounding(Double.parseDouble(categories) * 0.92378753 / 3);
        String residue = rounding(Double.parseDouble(categories) - Double.parseDouble(thirdRefund) * 3);

        $("#refund_create_form_shoeRepairAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);
        $("#refund_create_form_otherRetailAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);
        $("#refund_create_form_sundriesAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, residue);

        return this;
    }

    @Step("Fill Refund popup")
    public PaymentsPage fillSundriesPopup(String categories) {
        String thirdRefund = rounding(Double.parseDouble(categories) * 0.92378753);
        $("#refund_create_form_sundriesAmount").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, thirdRefund);
        return this;
    }

    @Step("Choose payment type")
    public PaymentsPage chooseType(String type) {

        $x(".//span[@class='select2-selection select2-selection--single form-select']").click();
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

    @Step("type of payment Check")
    public PaymentsPage checkCheckResult () {
        getRow().shouldHave(Condition.text("Check"));
        return new PaymentsPage();
    }

    @Step("type of payment Cash")
    public PaymentsPage checkCardResult () {
        getRow().shouldHave(Condition.text("Card"));
        return new PaymentsPage();
    }

    @Step("sum for two units of the same product")
    public void checkSum (String price){
        String sum = rounding((parseDouble(price)) * 2 * taxRate);
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("sum for one product")
    public void checkSumForOne (String price){
        String sum = rounding((parseDouble(price)) * taxRate);
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("sum for two units of the same product and one another")
    public void checkDoubleSum (String firstPrice, String secondPrice){
        String sum = rounding (((parseDouble(firstPrice)) * 2 + (parseDouble(secondPrice))) * taxRate);
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("error")
    public void checkEqualError () {
        $("#toast-container").shouldBe(Condition.visible);
        $("#refund-create-modal").$x(".//span[@class='svg-icon svg-icon-2x']").click();
    }

    public SelenideElement getRow () {
        return $$x(".//tr[@class='odd']").get(0);
    }

    @Step("value on Payments page DCR after applying % discount")
    public void checkPercentDiscountResult(String price, String discount) {
        Double a = parseDouble(price) / 100 * parseDouble(discount);
        String sum = rounding(((parseDouble(price) - parseDouble(rounding(a))) * taxRate));
        getRow().shouldHave(Condition.text(sum));
    }

    @Step("value on Payments page after applying $ discount")
    public void checkDollarDiscountResult(String price, String discount) {
        String sum = rounding((parseDouble(price) - parseDouble(discount)) * taxRate);
        getRow().shouldHave(Condition.text(sum));
    }

    }
