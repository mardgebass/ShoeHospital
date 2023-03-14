package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ValuesDB {

    static String storeId = "8";

    static String requestSku =
            "SELECT b.barcode FROM stores_products s JOIN barcodes b " +
            "ON s.product_id = b.product_id " +
            "WHERE store_id = " + storeId + " AND hide = 0";

    static String requestSecondSku =
            "SELECT b.barcode FROM stores_products s JOIN barcodes b " +
                    "ON s.product_id = b.product_id " +
                    "WHERE store_id = " + storeId + " AND hide = 0 AND b.barcode <> ";

    static String requestSum = "SELECT s.sale_price FROM stores_products s JOIN barcodes b " +
            "ON s.product_id = b.product_id " +
            "WHERE store_id = " + storeId + " AND barcode = ";

    static String requestNotAvailableSku = "SELECT b.barcode FROM product_region s LEFT OUTER JOIN stores_products p " +
            "ON s.product_id = p.product_id JOIN barcodes b ON s.product_id = b.product_id " +
            "WHERE region_id = 2 AND store_id is null";

    static String requestPaymentIdWithoutRefunds = "SELECT id FROM app.payments WHERE refunds_amount = 0 AND store_id = " + storeId;
    static String requestAmountFromPayments = "SELECT total_amount FROM app.payments WHERE id = ";
    static String requestAmountFromRefunds = "SELECT total_amount FROM app.refunds WHERE payment_id = ";
    static String requestTitlePercent = "SELECT title FROM app.promocode WHERE is_active=1 AND is_service=0 AND is_percent=1";
    static String requestPromoValue = "SELECT price FROM app.promocode WHERE title = ";
    static String requestTitleDollar = "SELECT title FROM app.promocode WHERE is_active=1 AND is_service=0 AND is_percent=0";
    static String requestNotWatching = "SELECT ticket_barcode FROM app.tickets where store_id = " + storeId + " and watching is null and status <> 'Ordering' and status <> 'Completed';";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static String sku;
    public static String notWatching;
    static String sum;
    public static String falseSku;
    static String resultValue;
    static String paymentId;
    public static String amountById;
    public static String amountByIdRefund;
    static String secondSku;
    static String secondSum;
    static String titlePercent;
    public static String valuePercent;
    public static String valueDollar;
    static String titleDollar;

    static SecureRandom random = new SecureRandom();

    public static String getDataBaseValue(String request, String columnLabel) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(request);

            List<String> barcodes = new ArrayList<>();
            while (resultSet.next()) {
                barcodes.add(resultSet.getString(columnLabel));
            }

            resultValue = barcodes.get(random.nextInt(barcodes.size()));

        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return resultValue;
    }

    public static String getSku() {
        sku = getDataBaseValue(requestSku, "barcode");
        return sku;
    }

    public static String getSecondSku() {
        secondSku = getDataBaseValue(requestSecondSku + sku, "barcode");
        return secondSku;
    }

    public static String getFalseSku() {
        falseSku = getDataBaseValue(requestNotAvailableSku, "barcode");
        return falseSku;
    }

    public static String getPaymentId() {
        paymentId = getDataBaseValue(requestPaymentIdWithoutRefunds, "id");
        return paymentId;
    }

    public static String getAmountFromPayments() {
        amountById = getDataBaseValue(requestAmountFromPayments + paymentId, "total_amount");
        return amountById;
    }

    public static String getSum() {
        sum = getDataBaseValue(requestSum + sku, "sale_price");
        return sum;
    }

    public static String getSumOfSecond() {
        secondSum = getDataBaseValue(requestSum + secondSku, "sale_price");
        return secondSum;
    }

    public static String getAmountFromRefunds() {
        amountByIdRefund = getDataBaseValue(requestAmountFromRefunds + paymentId, "total_amount");
        return amountByIdRefund;
    }

    public static String getTitlePercent() {
        titlePercent = getDataBaseValue(requestTitlePercent, "title");
        return titlePercent;
    }

    public static String getTitleDollar() {
        titleDollar = getDataBaseValue(requestTitleDollar, "title");
        return titleDollar;
    }

    public static String getValuePercent() {
        valuePercent = getDataBaseValue(requestPromoValue + "'" + titlePercent + "'", "price");
        return valuePercent;
    }

    public static String getValueDollar() {
        valueDollar = getDataBaseValue(requestPromoValue + "'" + titleDollar + "'", "price");
        return valueDollar;
    }

    public static String getNotWatching() {
        notWatching = getDataBaseValue(requestNotWatching, "ticket_barcode");
        return notWatching;
    }
}
