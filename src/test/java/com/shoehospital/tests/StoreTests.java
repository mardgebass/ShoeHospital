package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.stores.AddStorePage;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.stores.StoreManagementPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("New Shop")
@ExtendWith({SelenideExtension.class})
public class StoreTests extends BaseTest{

    Faker faker = new Faker();
    String name = faker.app().name();
    String number = faker.numerify("##");

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Shop creation")
    public void createShopTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickStoreManagement();
        page(StoreManagementPage.class)
                .clickAddStore();
        page(AddStorePage.class)
                .chooseRegion()
                .fillForm("Austin " + name + " " + number, "SH" + number, number, name + "@austinshoehospital.com")
                .clickSave()
                .getHeader()
                .clickStoreManagement();
        page(StoreManagementPage.class)
                .clickPagination()
                .checkNewStore("SH" + number);
    }

}


