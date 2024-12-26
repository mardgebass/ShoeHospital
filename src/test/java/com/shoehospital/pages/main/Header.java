package com.shoehospital.pages.main;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header {

    private SelenideElement navigateTo() {
        return $x(".//span[@class='menu-link py-3 gap-4']");
    }

    @Step("Login")
    public void checkLogin() {
        navigateTo().shouldBe(Condition.visible);
    }

    @Step("Navigate to New Order")
    public void clickNewOrder() {
        navigateTo().click();
        $(byText("New Order")).click();
    }

    @Step("Navigate to Store Management")
    public void clickStoreManagement() {
        navigateTo().click();
        $(byText("Store Management")).click();
    }

    @Step("Navigate to Store Management")
    public void clickInventoryManagement() {
        navigateTo().click();
        $(byText("Inventory Management")).click();
    }

    @Step("Navigate to Store Management")
    public void clickInventoryStore() {
        navigateTo().click();
        $(byText("Inventory Store")).click();
    }

    @Step("Navigate to QuickSale")
    public void clickQuickSale() {
        navigateTo().click();
        $(byText("Quick Sale")).click();
    }

    @Step("Navigate to Payments")
    public void clickPayments() {
        navigateTo().click();
        $(byText("Payments / Refunds")).click();
    }

    @Step("Navigate to DCR")
    public void clickDCR() {
        navigateTo().click();
        $(byText("DCR")).click();
    }

    @Step("Navigate to LogOut")
    public void clickLogOut() {
        $("#kt_header_user_menu_toggle").click();
        $(byText("Sign Out")).click();
    }

    @Step("Navigate to Logo")
    public Header clickLogo() {
        $x(".//div[@class='d-flex align-items-center me-5']").click();
        return this;
    }

    @Step("Enter search request")
    public Header enterSearchRequest(String id) {
        $x(".//div[@class='btn btn-icon btn-icon-muted btn-active-light btn-active-color-primary w-30px h-30px w-md-40px h-md-40px']").click();
        $(byName("quick-search")).sendKeys(id);
        $x(".//div[@class='d-flex flex-column']").$(byText("#" + id)).click();
        return this;
    }

    @Step("search results")
    public void checkSearchResults(String id) {
        $x(".//div[@class='btn btn-icon btn-icon-muted btn-active-light btn-active-color-primary w-30px h-30px w-md-40px h-md-40px']").click();
        $(byName("quick-search")).sendKeys(id);
        $x(".//div[@class='menu menu-sub menu-sub-dropdown p-7 w-325px w-md-375px show']").shouldHave(Condition.text("No result found"));
    }

}
