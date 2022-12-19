package com.shoehospital.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_RIGHT;

public class SecondOrderStepPage extends BasePage{

    @Step("Add ticket {ticket}")
    public SecondOrderStepPage addTicketId(String ticket){
        $("#ticket_collection_form_tickets_2_ticketBarcode").setValue(ticket).pressEnter();
        return this;
    }

    @Step("Click Finish")
    public SecondOrderStepPage clickFinish(){
        $("#ticket_collection_form_finish").click();
        return this;
    }

    @Step("Click CWR")
    public SecondOrderStepPage clickCWR(){
        sleep(1000);
        $(byAttribute("data-attr","cwr")).$x(".//input[@class='form-check-input h-25px w-25px']").click();
        return this;
    }

    @Step("Add price {price}")
    public SecondOrderStepPage addPrice(String price){
        $(byAttribute("data-attr","est_cost")).$x(".//input[@class='form-control formatCost']").sendKeys(ARROW_RIGHT, price);
        return this;
    }

    @Step("Add date")
    public SecondOrderStepPage addDate() {
        $x(".//input[@class='form-control flatpickr-input']").click();
        $x(".//div[@class='flatpickr-calendar animate open arrowTop arrowLeft']").$x(".//span[@class='flatpickr-day today']").click();
        return this;
    }

}
