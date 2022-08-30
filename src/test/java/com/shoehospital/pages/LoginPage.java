package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    @Step("Login by {login} / {password}")
    public LoginPage login(String login, String password){
        $("#username").setValue(login);
        $("#password").setValue(password);
        $x(".//button[@class='btn btn-lg btn-primary w-100 btn']").click();
        return new LoginPage();
    }

    @Step("Check error message {error} ")
    public LoginPage checkError(String error) {
        $x(".//div[@class='alert alert-danger']").shouldHave(Condition.text(error));
        return this;
    }

    @Step("Check Logout")
    public LoginPage checkLogout() {
        $("#username").shouldBe(Condition.visible);
        $("#password").shouldBe(Condition.visible);
        $x(".//button[@class='btn btn-lg btn-primary w-100 btn']").shouldBe(Condition.visible);
        return this;
    }
}
