package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("New Product")
@ExtendWith({SelenideExtension.class})
public class NewProductTests {

    Faker faker = new Faker();

    String sku = faker.numerify("##########");
    String extraSku = faker.numerify("######");
    String title = faker.name().title();
    String description = faker.lorem().sentence();
    String price = faker.numerify("###");

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Product creation")
    public void createProductTest() {

        page(MainPage.class)
                .getHeader()
                .clickInventoryManagement();
        page(InventoryManagementPage.class)
                .clickAddProduct();
        page(CreateProductPage.class)
                .addSKU(sku)
                .addExtraSKU(extraSku)
                .addTitle(title)
                .addDescription(description)
                .selectCategory()
                .selectManufacturer()
                .selectRegion()
                .addPrice(price)
                .clickSave();
        page(InventoryManagementPage.class)
                .checkSku(sku,extraSku);
    }

}


