package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.products.CreateProductPage;
import com.shoehospital.pages.products.InventoryManagementPage;
import com.shoehospital.pages.products.InventoryStorePage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;
import static com.shoehospital.database.ValuesDB.falseSku;
import static com.shoehospital.database.ValuesDB.getFalseSku;

@DisplayName("New Product")
@ExtendWith({SelenideExtension.class})
public class ProductTests extends BaseTest {

    Faker faker = new Faker();
    String sku = faker.numerify("##########");
    String extraSku = faker.numerify("######");
    String title = faker.name().title();
    String description = faker.lorem().sentence();
    String price = faker.numerify("###.##");
    String quantity = faker.numerify("##");

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Product creation with manufacture")
    public void createProductTest() {
        page(DashboardPage.class)
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
        page(DashboardPage.class)
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
    @DisplayName("Product creation with existing SKU")
    public void checkExistingSkuTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickInventoryManagement();
        page(InventoryManagementPage.class)
                .clickAddProduct();
        page(CreateProductPage.class)
                .addSKU(getFalseSku())
                .checkAlertBarcodeExists();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Adding a product to the store")
    public void addProductTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickInventoryStore();
        page(InventoryStorePage.class)
                .clickEditPencil(getFalseSku())
                .addQuantity(quantity)
                .addPrice(price)
                .clickSave()
                .checkAdding(falseSku, quantity, price);
    }

}


