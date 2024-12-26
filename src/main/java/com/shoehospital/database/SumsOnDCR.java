package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$x;

public class SumsOnDCR {

    private static final String AMOUNT_COLUMN = "SUM(amount)";
    private static final String TOTAL_COST_COLUMN = "SUM(a.total_cost)";
    private static final String REQUEST_TOTAL_COST = "SELECT SUM(a.total_cost) FROM payment_positions a join payments b on a.payment_id = b.id WHERE ";

    public static String fromDateTime() {
        String from = $x("/html/body/div[1]/div/div/div[2]/div[1]/form/div[1]/div[2]/div/u[1]").innerText();
        return convertDateTime(from);
    }

    public static String toDateTime() {
        String to = $x("/html/body/div[1]/div/div/div[2]/div[1]/form/div[1]/div[2]/div/u[2]").innerText();
        return convertDateTime(to);
    }

    public static String dcrDay() {
        String day = $x("/html/body/div[1]/div/div/div[2]/div[1]/div/div[1]/h1").innerText();
        String x = day.substring(18);

        SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date;
        try {
            date = originalFormat.parse(x);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String string = outputFormat.format(date);
        return string;

    }

    public static String storeTime() {
        return "store_id = " + TestConfigDB.testConfig.storeId() + " AND created_at between '" + fromDateTime() + "' AND '" + toDateTime() + "'";
    }

    public static String storeTimeDropOff() {
        return "store_id = " + TestConfigDB.testConfig.storeId() + " AND drop_off_date between '" + fromDateTime() + "' AND '" + toDateTime() + "'";
    }

    public static String convertDateTime(String originalDateString) {

        SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        originalFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date;
        try {
            date = originalFormat.parse(originalDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String string = outputFormat.format(date);
        return string;
    }


    public static String getDataBaseValue(String query, String columnLabel) {

        try (Connection connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                String value = resultSet.getString(columnLabel);
                return value == null ? "0.00" : String.format(Locale.ENGLISH, "%(.2f", Double.parseDouble(value));
            } else {
                return "0.00";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    public static String getCheck() {
        return getDataBaseValue("SELECT SUM(amount) FROM app.payments WHERE " + storeTime() + " and payment_type = 3", AMOUNT_COLUMN);
    }
    public static String getCash() {
        return getDataBaseValue("SELECT SUM(amount) FROM app.payments WHERE " + storeTime() + " and payment_type = 1", AMOUNT_COLUMN);
    }
    public static String getCard() {
        return getDataBaseValue("SELECT SUM(amount) FROM app.payments WHERE " + storeTime() + " and payment_type = 2", AMOUNT_COLUMN);
    }

    public static String getGiftCard(){
        return getDataBaseValue("SELECT SUM(giftCard_amount) FROM app.payments WHERE " + storeTime(), "SUM(giftCard_amount)");
    }
    public static String getSumPayments() {
        return getDataBaseValue("SELECT SUM(amount) FROM app.payments WHERE " + storeTime(), AMOUNT_COLUMN);
    }
    public static String getRefund() {
        return getDataBaseValue("SELECT SUM(total_amount) FROM app.refunds WHERE " + storeTime(), "SUM(total_amount)");
    }
    public static String getTaxes() {
        return getDataBaseValue("SELECT SUM(tax_amount) FROM app.payments WHERE " + storeTime(), "SUM(tax_amount)");
    }
    public static String getAlden() {
        return getDataBaseValue(REQUEST_TOTAL_COST + storeTime() + " and a.manufacturer = 'Alden'", TOTAL_COST_COLUMN);
    }
    public static String getKT() {
        return getDataBaseValue(REQUEST_TOTAL_COST + storeTime() + " and a.manufacturer = 'KT'", TOTAL_COST_COLUMN);
    }
    public static String getBR() {
        return getDataBaseValue(REQUEST_TOTAL_COST + storeTime() + " and a.manufacturer = 'B&R'", TOTAL_COST_COLUMN);
    }
    public static String getSundry() {
        return getDataBaseValue(REQUEST_TOTAL_COST + storeTime() + " and a.manufacturer is null and a.model <> 'ticket' AND a.barcode <> 'gift'", TOTAL_COST_COLUMN);
    }

    public static String getGiftPurchase() {
        return getDataBaseValue(REQUEST_TOTAL_COST + storeTime() + " and a.manufacturer is null AND a.barcode = 'gift'", TOTAL_COST_COLUMN);
    }

    public static String getDyeSales() {
        return getDataBaseValue("SELECT SUM(a.dye_price) FROM app.tickets_details a join app.tickets b on a.id = b.ticket_details_id where " + storeTimeDropOff(), "SUM(a.dye_price)");
    }

    public static String getSolePros() {
        return getDataBaseValue("SELECT SUM(a.soles_pro_price) FROM app.tickets_details a join app.tickets b on a.id = b.ticket_details_id where " + storeTimeDropOff(), "SUM(a.soles_pro_price)");
    }

    public static String getRepair() {
        return getDataBaseValue("SELECT SUM(b.total_cost) FROM app.payments a join app.payment_positions b on a.id = b.payment_id where b.model = 'ticket' and a." + storeTime(), "SUM(b.total_cost)");
    }

    public static String getTapeTotal() {
        return getDataBaseValue("SELECT b.z_tape_total FROM app.dcr a join app.dcr_z_reading b on a.id = b.dcr_id WHERE a.store_id = " + TestConfigDB.testConfig.storeId() + " AND report_date = " + dcrDay(), "b.z_tape_total");
    }

    public static String getOverShort() {
        return getDataBaseValue("SELECT b.over_short FROM app.dcr a join app.dcr_z_reading b on a.id = b.dcr_id WHERE a." + storeTime(), "b.over_short");
    }

    public static String getPinks() {
        return getDataBaseValue("SELECT COUNT(*) FROM app.tickets WHERE " + storeTimeDropOff(), "COUNT(*)");
    }

    public static String getUnpaid() {
        return getDataBaseValue("SELECT SUM(estimation_cost) FROM app.tickets WHERE " + storeTimeDropOff() + " AND payment_status = 0", "SUM(estimation_cost)");
    }


    public static String getDailyTickets() {
        return getDataBaseValue("SELECT SUM(estimation_cost) FROM app.tickets WHERE " + storeTimeDropOff(), "SUM(estimation_cost)");

    }
}
