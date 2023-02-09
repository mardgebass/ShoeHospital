package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.DataBaseRepository;
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
public class NewProductTests extends DataBaseRepository {

    Faker faker = new Faker();

    String sku = faker.numerify("##########");
    String extraSku = faker.numerify("######");
    String title = faker.name().title();
    String description = faker.lorem().sentence();
    String price = faker.numerify("###.##");
    String quantity = faker.numerify("##");

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

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Product creation without manufacture")
    public void createProductWithoutManufactureTest() {

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
                .selectRegion()
                .addPrice(price)
                .clickSave();
        page(InventoryManagementPage.class)
                .checkSku(sku,extraSku);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Adding a product to the store")
    public void addProductTest() {

        page(MainPage.class)
                .getHeader()
                .clickInventoryStore();
        page(InventoryStorePage.class)
                .clickAddProduct(getFalseSku())
                .addQuantity(quantity)
                .addPrice(price)
                .clickSave()
                .checkAdding(quantity, price);
    }

}


