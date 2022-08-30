package com.shoehospital.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header {

    private SelenideElement navigateTo() {
        return $x(".//span[@class='menu-link py-3 fs-5']");
    }

    @Step("Check Login")
    public MainPage checkLogin() {
        navigateTo().shouldBe(Condition.visible);
        return new MainPage();
    }

    @Step("Navigate to New Order")
    public MainPage clickNewOrder() {
        navigateTo().click();
        $(byText("New Order")).click();
        return new MainPage();
    }

    @Step("Navigate to QuickSale")
    public MainPage clickQuickSale() {
        navigateTo().click();
        $(byText("Quick Sale")).click();
        return new MainPage();
    }

    @Step("Navigate to Payments")
    public MainPage clickPayments() {
        navigateTo().click();
        $(byText("Payments")).click();
        return new MainPage();
    }

    @Step("Navigate to DCR")
    public MainPage clickDCR() {
        navigateTo().click();
        $(byText("DCR")).click();
        return new MainPage();
    }

    @Step("Navigate to LogOut")
    public MainPage clickLogOut() {
        $("#kt_header_user_menu_toggle").click();
        $(byText("Sign Out")).click();
        return new MainPage();
    }

    @Step("Navigate to Logo")
    public MainPage clickLogo() {
        $x(".//div[@class='d-flex align-items-center me-5']").click();
        return new MainPage();
    }

}
