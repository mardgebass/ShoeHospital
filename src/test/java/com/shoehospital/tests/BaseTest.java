package com.shoehospital.tests;

import com.codeborne.selenide.Selenide;
import com.shoehospital.config.TestConfig;
import com.shoehospital.pages.main.LoginPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.page;

public class BaseTest {

    @BeforeEach
    public void login () {
        Selenide.open(TestConfig.testConfig.baseUrl());

////        String cooKIE = WebDriverRunner.getWebDriver().manage().getCookieNamed("PHPSESSID").getValue();
//        given ()
//                .header("PHPSESSID", "v37bj5cet91i2dt3m7kctr029b")
//                .formParam("_username", "natalia.andreevna")
//                .formParam("_password", "qwerty$")
//                .formParam("_csrf_token", "f27431e5717.iqtdMqZuMXRkvugr9nE2UkM45aL8KXRlZ2irHdW0A_o.7Og-X54Ie0Zd-9punjJUJAcBqMa5QSEhFA2bUIzRTJSyygld_wNZTAnZiw")
//                .when()
//                .post("http://demo-shoehospital.zimalab.com/login")
//                .then().statusCode(200);
////                .then().log().all().extract().cookie("PHPSESSID");
//        Date expDate = new Date();
//        expDate.setTime(expDate.getTime() * (10000*10000));
//        Cookie cookie = new Cookie("PHPSESSID", "v37bj5cet91i2dt3m7kctr029b", "demo-shoehospital.zimalab.com", "/", expDate );
//        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
//        sleep(5000);
//
//        Selenide.refresh();


//        String cooKIE = WebDriverRunner.getWebDriver().manage().getCookieNamed("PHPSESSID").getValue();

//        String sessionId = given ()
//                .cookie("PHPSESSID", "59v90jgi5ugh56s8cvnmi3i1vh")
//                .queryParam("_username", TestConfig.testConfig.username())
//                .queryParam("_password", TestConfig.testConfig.password())
//                .queryParam("_csrf_token", "3b.QcL46ZvLM1UtIAnk2mTgYfqfTHmhyW8U4oVBzAHcYeI.bPO_uqm8eWFsSlGjngbXJ6nnYTfZmCtQiP0lv22mB4kTtbPEw58KMntpUA")
//                .post("http://demo-shoehospital.zimalab.com/login")
//                .then().log().all().extract().cookie("PHPSESSID");
//        .contentType(ContentType.JSON)
//                .cookie("PHPSESSID", cooKIE)
//                .formParam("_username", TestConfig.testConfig.username())
//                .formParam ("_password", TestConfig.testConfig.password())
//                .formParam ("_csrf_token", cooKIE)
//                .post(TestConfig.testConfig.baseUrl())
//                .then().log().all().extract().cookie("PHPSESSID");

//        Date expDate = new Date();
//        expDate.setTime(expDate.getTime() * (10000*10000));
//        Cookie cookie = new Cookie("PHPSESSID", "59v90jgi5ugh56s8cvnmi3i1vh", "demo-shoehospital.zimalab.com", "/", expDate );
//        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
//        Selenide.refresh();

        page(LoginPage.class)
                .login(TestConfig.testConfig.username(), TestConfig.testConfig.password());
    }

//    @AfterEach
//    public void afterEach() {
//        page(DashboardPage.class)
//                .getHeader()
//                .clickLogOut();
//    }

}
