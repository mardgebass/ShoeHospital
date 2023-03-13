package com.shoehospital.pages.tickets;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

    @Step("Check Status Picked up")
    public void checkStatusPickedUp() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(text("Picked up"));
    }

    @Step("Check Status Paid")
    public TicketPage checkStatusPaid() {
        $x(".//span[@class='badge badge-light-success']").shouldHave(text("Paid"));
        return this;
    }

    @Step("Check Status Pending")
    public TicketPage checkPaymentStatusPending() {
        $x(".//span[@class='badge badge-light-warning']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Check text \"Ticket created\" in Audit History")
    public TicketPage checkCreatedHistory() {
        $("#table_body").shouldHave(text("Ticket created"));
        return this;
    }

    @Step("Check text \"Ticket marked: Pending Pick Up\" in Audit History")
    public void checkRFPUHistory() {
        $("#table_body").shouldHave(text("Ticket marked: Pending Pick Up"));
    }

    @Step("Check text \"Ticket closed: Picked up\" in Audit History")
    public TicketPage checkPickedUpHistory() {
        $("#table_body").shouldHave(text("Ticket closed: Picked up"));
        return this;
    }

    @Step("Check text \"Drop off sent\" in Audit History")
    public void checkDropSentHistory() {
        $("#table_body").shouldHave(text("Drop off sent"));
    }

    @Step("Check Status Ready for Pick up")
    public TicketPage checkStatusRFPU() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(text("Pending Pick Up"));
        return this;
    }

    @Step("Check Status In Progress")
    public TicketPage checkStatusInProgress() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(text("In-Progress"));
        return this;
    }

    public void clickProceedToPayment() {
        $x(".//a[@class='btn btn-light-success w-100 mb-5']").click();
    }

    public TicketPage clickWatch() {
        $x(".//a[@class='btn fs-7 btn-light-primary d-flex flex-center']").click();
        return this;
    }

    public void checkWatching() {
        $x(".//span[@class='align-self-center badge badge-light-primary']").shouldHave(text("Watching"));
    }

    @Step("Check alert {An unpaid ticket cannot be completed}")
    public void checkErrorPickedUp(){
        $("#toast-container").shouldHave(text("An unpaid ticket cannot be completed"));
    }

}
