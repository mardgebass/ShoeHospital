package com.shoehospital.pages;

import io.qameta.allure.Step;

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
        $x(".//tr[1]/td[3]/div/input").click();
        return this;
    }

    @Step("Add price {price}")
    public SecondOrderStepPage addPrice(String price){
        $x(".//tr[1]/td[4]/div/input").sendKeys(ARROW_RIGHT);
        $x(".//tr[1]/td[4]/div/input").sendKeys(price);
        return this;
    }

    @Step("Click date")
    public SecondOrderStepPage clickDate() {
        $x(".//input[@class='form-control flatpickr-input']").click();
        return this;
    }

    @Step("Add date")
    public SecondOrderStepPage addDate() {
        $x(".//span[@class='flatpickr-day today']").click();
        return this;
    }

}
