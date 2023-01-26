package com.shoehospital.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddStorePage extends BasePage{

    @Step("Choose region")
    public AddStorePage chooseRegion() {
        $x(".//span[@class='select2-selection select2-selection--single form-select']").click();
        $x(".//span[@class='select2-container select2-container--bootstrap5 select2-container--open']").$(byText("Austin/San Antonio")).click();
        return this;
    }

    @Step("Fill the form")
    public AddStorePage fillForm(String name, String shortName, String storeNumber, String email) {
        $("#store_form_name").sendKeys(name);
        $("#store_form_shortName").sendKeys(shortName);
        $("#store_form_storeNumber").sendKeys(storeNumber);
        $("#store_form_phone").sendKeys("7654563333");
        $("#store_form_email").sendKeys(email);
        $("#store_form_serviceEmail").sendKeys("shop@austinshoehospital.com");
        $("#store_form_city").sendKeys("Austin/San Antonio");
        $("#store_form_state").sendKeys("Texas");
        $("#store_form_zipCode").sendKeys("45321");
        $("#store_form_taxRate").sendKeys("0.0825");
        $("#store_form_address").sendKeys("New str 26");
        return this;
    }

    @Step("Choose region")
    public AddStorePage clickSave() {
        $("#store_form_save").click();
        return this;
    }

}
