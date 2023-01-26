package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.AddStorePage;
import com.shoehospital.pages.MainPage;
import com.shoehospital.pages.StoreManagementPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("New Shop")
@ExtendWith({SelenideExtension.class})
public class NewShopTests {

    Faker faker = new Faker();

    String name = faker.numerify("Austin ##");
    String storeNumber = faker.numerify("2##");
    String shortName = faker.bothify("SH##");
    String email = faker.bothify("??????##@me.com");

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Shop creation")
//    @RepeatedTest(6)
    public void createShopTest() {

        page(MainPage.class)
                .getHeader()
                .clickStoreManagement();
        page(StoreManagementPage.class)
                .clickAddStore();
        page(AddStorePage.class)
                .chooseRegion()
                .fillForm(name, shortName, storeNumber, email)
                .clickSave();
        page(MainPage.class)
                .getHeader()
                .clickStoreManagement();
        page(StoreManagementPage.class)
                .clickPagination()
                .checkNewStore(shortName);
    }

}


