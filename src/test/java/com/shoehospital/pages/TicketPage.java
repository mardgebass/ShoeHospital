package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TicketPage extends BasePage {

    @Step("Click Mark Ticket as Ready For Pick-Up")
    public TicketPage clickRFPU() {
        $(byText("Mark Ticket as Ready For Pick-Up")).click();
        return this;
    }

    @Step("Click Mark Ticket as Complete")
    public TicketPage clickComplete() {
        $(byText("Mark Ticket as Complete")).click();
        return this;
    }

    @Step("Check Status Completed")
    public TicketPage checkStatusCompleted() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(Condition.text("Completed"));
        return this;
    }

    @Step("Check Status Paid")
    public TicketPage checkStatusPaid() {
        $x(".//span[@class='badge badge-light-success']").shouldHave(Condition.text("Paid"));
        return this;
    }

    @Step("Check Status Pending")
    public TicketPage checkStatusPending() {
        $x(".//span[@class='badge badge-light-warning']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Check text \"Ticket created\" in Audit History")
    public TicketPage checkCreatedHistory() {
        $("#table_body").shouldHave(Condition.text("Ticket created"));
        return this;
    }

    @Step("Check text \"Ticket marked: Ready for Pick-Up\" in Audit History")
    public TicketPage checkRFPUHistory() {
        $("#table_body").shouldHave(Condition.text("Ticket marked: Ready for Pick-Up"));
        return this;
    }

    @Step("Check text \"Ticket closed: Completed\" in Audit History")
    public TicketPage checkCompletedHistory() {
        $("#table_body").shouldHave(Condition.text("Ticket closed: Completed"));
        return this;
    }

    @Step("Check text \"Drop off sent\" in Audit History")
    public TicketPage checkDropSentHistory() {
        $("#table_body").shouldHave(Condition.text("Drop off sent"));
        return this;
    }

    @Step("Check Status RFPU")
    public TicketPage checkStatusRFPU() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(Condition.text("Ready for Pick-Up"));
        return this;
    }

    @Step("Check Status In Progress")
    public TicketPage checkStatusInProgress() {
        $x(".//div[@class=' d-flex align-items-center flex-row justify-content-end me-5']").shouldHave(Condition.text("In-Progress"));
        return this;
    }

}
