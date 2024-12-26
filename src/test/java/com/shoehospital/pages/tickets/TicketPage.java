package com.shoehospital.pages.tickets;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TicketPage extends BasePage {

    @Step("Click Mark Ticket as Pending Pick Up")
    public TicketPage clickRFPU() {
        $(byText("Mark Ticket as Pending Pick Up")).click();
        return this;
    }

    @Step("Click Delete Ticket")
    public TicketPage clickDelete() {
        $x(".//a[@class='btn btn-danger w-100 mt-5']").click();
        return this;
    }

    @Step("Click Confirm Deletion")
    public TicketPage clickConfirmDeletion() {
        $x(".//a[@class='btn btn-danger w-100px']").click();
        return this;
    }


    @Step("Click Mark Ticket as Picked Up")
    public TicketPage clickPickedUp() {
        $(byText("Mark Ticket as Picked Up")).click();
        return this;
    }

    @Step("Status Picked up")
    public void checkStatusPickedUp() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(text("Picked up"));
    }

    @Step("Status Paid")
    public void checkStatusPaid() {
        $x(".//span[@class='badge badge-light-success']").shouldHave(text("Paid"));
    }

    @Step("Status Pending")
    public TicketPage checkPaymentStatusPending() {
        $x(".//span[@class='badge badge-light-warning']").shouldBe(Condition.visible);
        return this;
    }

    @Step("text \"Ticket created\" in Audit History")
    public TicketPage checkCreatedHistory() {
        $("#table_body").shouldHave(text("Ticket created"));
        return this;
    }

    @Step("text \"Ticket marked: Pending Pick Up\" in Audit History")
    public void checkRFPUHistory() {
        $("#table_body").shouldHave(text("Ticket marked: Pending Pick Up"));
    }

    @Step("text \"Ticket closed: Picked Up\" in Audit History")
    public TicketPage checkPickedUpHistory() {
        $("#table_body").shouldHave(text("Ticket closed: Picked Up"));
        return this;
    }

    @Step("Status Ready for Pick up")
    public TicketPage checkStatusRFPU() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(text("Pending Pick Up"));
        return this;
    }

    @Step("Status In Progress")
    public void checkStatusInProgress() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(text("In-Progress"));
    }

    public void clickProceedToPayment() {
        $x(".//a[@class='btn btn-light-success w-100 mb-5']").click();
//        $("#payment-process-btn").click();
//        $x("//*[@id=\"kt_wrapper\"]/div[2]/div/div/div[3]/div/div/a[1]").click();
    }

    public TicketPage clickWatch() {
        $x(".//a[@class='btn fs-7 btn-light-primary d-flex flex-center']").click();
        return this;
    }

    public void checkWatching() {
        $x(".//span[@class='align-self-center badge badge-light-primary']").shouldHave(text("Watching"));
    }

    @Step("alert {An unpaid ticket cannot be completed}")
    public void checkErrorPickedUp(){
        $("#toast-container").shouldHave(text("An unpaid ticket cannot be completed"));
    }

}
