package com.shoehospital.tests;

import com.github.javafaker.Faker;
import com.shoehospital.extensions.SelenideExtension;
import com.shoehospital.pages.main.DCRPage;
import com.shoehospital.pages.main.DashboardPage;
import com.shoehospital.pages.tickets.CartPage;
import com.shoehospital.pages.tickets.FirstOrderStepPage;
import com.shoehospital.pages.tickets.SecondOrderStepPage;
import com.shoehospital.pages.tickets.TicketPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;

@DisplayName("Tickets with payment")
@ExtendWith({SelenideExtension.class})
public class PaidTicketsTests extends BaseTest{

    Faker faker = new Faker();
    String price = faker.numerify("###");
    String discount = faker.numerify("#.##");
    String id = faker.numerify("50000###");
    String phone = "9385297121";
    
    @BeforeEach
    public void loginAndCreationTest() {
        page(DashboardPage.class)
                .getHeader()
                .clickNewOrder();
        page(FirstOrderStepPage.class)
                .fillFormExistingPhone(phone)
                .clickContinue()
                .clickYes();
        page(SecondOrderStepPage.class)
                .addTicketId(id)
                .addDate()
                .addPrice(price)
                .clickFinish()
                .getHeader()
                .clickDCR();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("In progress ticket paid by Check")
    public void inProgressCheckTest() {
        page(DCRPage.class)
                .checkCheckBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCheck();
        page(TicketPage.class)
                .checkStatusPaid()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCheckResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Pending Picked up ticket paid by check")
    public void pendingPickedUpCheckTest() {
        page(DCRPage.class)
                .checkCheckBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickRFPU()
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCheck();
        page(TicketPage.class)
                .checkStatusPaid()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCheckResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("In progress ticket paid in Cash")
    public void inProgressCashTest() {
        page(DCRPage.class)
                .checkCashBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCash();
        page(TicketPage.class)
                .checkStatusPaid()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Pending Picked up ticket paid in cash")
    public void pendingPickedUpCashTest() {
        page(DCRPage.class)
                .checkCashBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickRFPU()
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCash();
        page(TicketPage.class)
                .checkStatusPaid()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("In-progress ticket paid by card")
    public void inProgressCardTest() {
        page(DCRPage.class)
                .checkCardBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCard();
        page(TicketPage.class)
                .checkStatusPaid()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Pending Picked up ticket paid by card")
    public void pendingPickedUpCardTest() {
        page(DCRPage.class)
                .checkCardBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickRFPU()
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCard();
        page(TicketPage.class)
                .checkStatusPaid()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCardResult(price);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Picked up ticket")
    public void pickedUpTicketTest() {
        page(DCRPage.class)
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .payByCard();
        page(TicketPage.class)
                .clickRFPU()
                .clickPickedUp()
                .checkPickedUpHistory()
                .checkStatusPickedUp();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Error for Mark ticket Picked up without payment")
    public void errorPickedUpTest() {
        page(DCRPage.class)
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickRFPU()
                .clickPickedUp()
                .checkErrorPickedUp();
    }
    
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Apply individual $ discount")
    public void applyDollarTest() {
        page(DCRPage.class)
                .checkCashBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .chooseDiscountType("Custom $")
                .addDiscountSum(discount)
                .checkDiscount("-$1" + discount)
                .payByCash()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCashDiscountResult(price, discount);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Apply individual % discount")
    public void applyPercentTest() {
        page(DCRPage.class)
                .checkCheckBefore()
                .getHeader()
                .enterSearchRequest(id);
        page(TicketPage.class)
                .clickProceedToPayment();
        page(CartPage.class)
                .chooseDiscountType("Custom %")
                .addDiscountSum(discount)
                .checkDiscount("-1" + discount + "%")
                .payByCheck()
                .getHeader()
                .clickDCR();
        page(DCRPage.class)
                .checkCheckDiscountResult(price, discount);
    }
}


