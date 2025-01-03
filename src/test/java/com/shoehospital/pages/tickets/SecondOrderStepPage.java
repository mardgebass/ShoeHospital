package com.shoehospital.pages.tickets;

import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.ARROW_LEFT;

public class SecondOrderStepPage extends BasePage {

    @Step("Add ticket {ticket}")
    public SecondOrderStepPage addTicketId(String ticket){
        $("#ticket_collection_create_form_tickets_2_ticketBarcode").setValue(ticket).pressEnter();
        sleep(1000);
        return this;
    }

    @Step("Click Finish")
    public void clickFinish(){
        $("#fake-finish").click();
        $("#ticket-sequence-agreement-button").click();
//        $("#ticket-sequence-agreement-button").click();
    }

//    @Step("Click CWR")
//    public SecondOrderStepPage clickCWR(){
//        $(byAttribute("data-attr","cwr")).$x(".//input[@class='form-check-input h-25px w-25px']").click();
//        return this;
//    }

    @Step("Add price {price}")
    public SecondOrderStepPage addPrice(String price){
        sleep(2000);
        $(byAttribute("data-attr","est_cost")).$x(".//input[@class='form-control formatCost']").sendKeys(ARROW_LEFT, ARROW_LEFT, ARROW_LEFT, price);
        return this;
    }

    @Step("Add date")
    public SecondOrderStepPage addDate() {
        $x(".//input[@class='form-control flatpickr-input']").click();
        $x(".//div[@class='flatpickr-calendar animate open arrowTop arrowLeft']").$x(".//span[@class='flatpickr-day today']").click();
        return this;
    }

}
