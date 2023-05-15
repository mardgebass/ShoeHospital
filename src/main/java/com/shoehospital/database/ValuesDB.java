package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ValuesDB {

    static String requestSku = "SELECT b.barcode FROM stores_products s JOIN barcodes b ON s.product_id = b.product_id " +
            "WHERE store_id = " + TestConfigDB.testConfig.storeId() + " AND hide = 0";

    static String requestSum = "SELECT s.sale_price FROM stores_products s JOIN barcodes b ON s.product_id = b.product_id " +
            "WHERE store_id = " + TestConfigDB.testConfig.storeId() + " AND barcode = ";

    static String requestNotAvailableSku = "SELECT b.barcode FROM product_region s LEFT OUTER JOIN stores_products p " +
            "ON s.product_id = p.product_id JOIN barcodes b ON s.product_id = b.product_id " +
            "WHERE region_id = 2 AND store_id is null";

    static String requestPaymentIdWithoutRefunds = "SELECT id FROM app.payments WHERE refunds_amount = 0 AND store_id = " + TestConfigDB.testConfig.storeId();
    static String requestAmountFromPayments = "SELECT total_amount FROM app.payments WHERE id = ";
    static String requestAmountFromRefunds = "SELECT total_amount FROM app.refunds WHERE payment_id = ";
    static String requestTitle = "SELECT title FROM app.promocode WHERE is_active=1 AND is_service=0 AND is_percent=";
    
    static String requestPromoValue = "SELECT price FROM app.promocode WHERE title = ";

    static String requestNotWatching = "SELECT ticket_barcode FROM app.tickets where store_id = " + TestConfigDB.testConfig.storeId() + " and watching is null and status <> 'Ordering' and status <> 'Completed';";

    public static String falseSku;
    public static String amountById;
    public static String amountByIdRefund;
    public static String notWatching;


    private static Connection connection;
    private static Statement statement;
    private static String resultValue;
    private static String sku;
    private static String paymentId;
    private static String secondSku;
    private static String titlePercent;
    private static String titleDollar;


    public static String getDataBaseValue(String request, String columnLabel) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            List<String> barcodes = new ArrayList<>();
            while (resultSet.next()) {
                barcodes.add(resultSet.getString(columnLabel));
            }

            SecureRandom random = new SecureRandom();
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


//    SKU (barcode)
    public static String getSku() {
        sku = getDataBaseValue(requestSku, "barcode");
        return sku;
    }

    public static String getSecondSku() {
        secondSku = getDataBaseValue(requestSku + "AND b.barcode <> " + sku, "barcode");
        return secondSku;
    }

    public static String getFalseSku() {
        falseSku = getDataBaseValue(requestNotAvailableSku, "barcode");
        return falseSku;
    }
    

//    sums
    public static String getSum() {
        return getDataBaseValue(requestSum + sku, "sale_price");
    }
    public static String getSumOfSecond() {
        return getDataBaseValue(requestSum + secondSku, "sale_price");
    }

    public static String getPaymentIdWithoutRefund() {
        paymentId = getDataBaseValue(requestPaymentIdWithoutRefunds, "id");
        return paymentId;
    }

    public static String getAmountFromPayments() {
        amountById = getDataBaseValue(requestAmountFromPayments + paymentId, "total_amount");
        return amountById;
    }

    public static String getAmountFromRefunds() {
        amountByIdRefund = getDataBaseValue(requestAmountFromRefunds + paymentId, "total_amount");
        return amountByIdRefund;
    }

//      discounts
    public static String getTitlePercent() {
        titlePercent = getDataBaseValue(requestTitle + "1", "title");
        return titlePercent;
    }

    public static String getTitleDollar() {
        titleDollar = getDataBaseValue(requestTitle + "0", "title");
        return titleDollar;
    }

    public static String getValuePercent() {
        return getDataBaseValue(requestPromoValue + "'" + titlePercent + "'", "price");
    }

    public static String getValueDollar() {
        return getDataBaseValue(requestPromoValue + "'" + titleDollar + "'", "price");
    }

    public static String getNotWatching() {
        notWatching = getDataBaseValue(requestNotWatching, "ticket_barcode");
        return notWatching;
    }
}
