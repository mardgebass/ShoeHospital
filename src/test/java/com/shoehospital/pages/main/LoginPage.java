package com.shoehospital.pages.main;

import com.codeborne.selenide.Condition;
import com.shoehospital.pages.base.BasePage;
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

    @Step("error message {error} ")
    public void checkError(String error) {
        $x(".//div[@class='alert alert-danger']").shouldHave(Condition.text(error));
    }

    @Step("Logout")
    public void checkLogout() {
        $("#username").shouldBe(Condition.visible);
        $("#password").shouldBe(Condition.visible);
        $x(".//button[@class='btn btn-lg btn-primary w-100 btn']").shouldBe(Condition.visible);
    }
}
