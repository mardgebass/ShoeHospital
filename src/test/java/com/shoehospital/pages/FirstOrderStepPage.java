package com.shoehospital.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class FirstOrderStepPage extends BasePage {

    @Step("Fill the form by {phone} / {email} / {firstname} {lastname}")
    public FirstOrderStepPage fillForm(String phone, String email, String firstname, String lastname) {
        $("#customer_form_phone").sendKeys(phone);
        $("#customer_form_email").sendKeys(email);
        $("#customer_form_firstName").sendKeys(firstname);
        $("#customer_form_lastName").sendKeys(lastname);
        return this;
    }

    @Step("Fill the form by phone {phone}")
    public FirstOrderStepPage fillFormExistingPhone(String phone) {
        $("#customer_form_phone").sendKeys(phone);
        return this;
    }

    @Step("Click continue")
    public FirstOrderStepPage clickContinue() {
        $("#fake-continue").click();
        return this;
    }

    @Step("Confirm using the user")
    public FirstOrderStepPage clickYes() {
        sleep(1000);
        $("#customer-agreement-button").click();
        $("#fake-continue").click();
        return this;
    }

}
