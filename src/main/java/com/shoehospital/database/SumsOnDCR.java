package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SumsOnDCR {

    private static Connection connection;
    private static Statement statement;
    static String resultValue;


    static String amountColumn = "SUM(amount)";
    static String requestSumAmount = "SELECT SUM(amount) FROM app.payments WHERE store_id = " + TestConfigDB.testConfig.storeId() + " AND DATE(created_at) = CURDATE() and payment_type = ";

    static String totalCostColumn = "SUM(a.total_cost)";
    static String requestTotalCost = "SELECT SUM(a.total_cost) FROM payment_positions a join payments b on a.payment_id = b.id where store_id = "
            + TestConfigDB.testConfig.storeId() + " and DATE(created_at) = CURDATE() and a.manufacturer";


    static String requestRefund = "SELECT SUM(total_amount) FROM app.refunds WHERE store_id = " + TestConfigDB.testConfig.storeId()
            + " AND DATE(created_at) = CURDATE()";

    static String requestTaxes = "SELECT SUM(tax_amount) FROM app.payments WHERE store_id = " + TestConfigDB.testConfig.storeId()
            + " AND DATE(created_at) = CURDATE()";


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

            String a = barcodes.get(0);

            if (a == null) {
                resultValue = "0.00";
            }
            else {
                resultValue = String.format(Locale.ENGLISH, "%(.2f", (Double.parseDouble(a)));
            }

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

    //amount
    public static String getCheck() {
        return getDataBaseValue(requestSumAmount + "3", amountColumn);
    }
    public static String getCash() {
        return getDataBaseValue(requestSumAmount + "1", amountColumn);
    }
    public static String getCard() {
        return getDataBaseValue(requestSumAmount + "2", amountColumn);
    }
    public static String getSumPayments() {
        return getDataBaseValue(requestSumAmount, amountColumn);
    }



    public static String getRefund() {
        return getDataBaseValue(requestRefund, "SUM(total_amount)");
    }

    public static String getTaxes() {
        return getDataBaseValue(requestTaxes, "SUM(tax_amount)");
    }


//    a.total_cost
    public static String getAlden() {
        return getDataBaseValue(requestTotalCost + "= 'Alden'", totalCostColumn);
    }
    public static String getKT() {
        return getDataBaseValue(requestTotalCost + "= 'KT'", totalCostColumn);
    }
    public static String getBR() {
        return getDataBaseValue(requestTotalCost + "= 'B&R'", totalCostColumn);
    }
    public static String getSundry() {
        return getDataBaseValue(requestTotalCost + "is null and a.model <> 'ticket'", totalCostColumn);
    }

}
