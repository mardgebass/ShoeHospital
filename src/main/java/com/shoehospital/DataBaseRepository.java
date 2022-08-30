package com.shoehospital;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {

    private static final String URL = "jdbc:mysql://XXX.XXX.XXX.XX:XXXX/app";
    private static final String user = "app";
    private static final String password = "XXXX";

    String requestSku = "select b.barcode from stores_products s join barcodes b on s.product_id = b.product_id where store_id = 4 and hide = 0";
    String requestSum = "select s.sale_price from stores_products s join barcodes b on s.product_id = b.product_id where store_id = 4 and barcode = ";
    String requestNotAvailableSku = "SELECT b.barcode from product_region s LEFT OUTER JOIN stores_products p on s.product_id = p.product_id JOIN barcodes b on s.product_id = b.product_id WHERE region_id = 2 and store_id is null";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    int row;

    static String sku;
    static String sum;
    static String falseSku;

    SecureRandom random = new SecureRandom();

    public String getSku() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(requestSku);

            List<String> barcodes = new ArrayList<>();

            while (resultSet.next()) {
                barcodes.add(resultSet.getString("barcode"));
            }

            row = random.nextInt(barcodes.size());
            sku = barcodes.get(row);

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
        return sku;
    }

    public String getSum() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(requestSum + sku);

            while (resultSet.next()) {
                sum = String.valueOf((resultSet.getDouble("sale_price"))*2);
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
        return sum;
    }

    public String getFalseSku() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(requestNotAvailableSku);

            List<String> barcodesFromOtherStore = new ArrayList<>();

            while (resultSet.next()) {
                barcodesFromOtherStore.add(resultSet.getString("barcode"));
            }

            falseSku = barcodesFromOtherStore.get(random.nextInt(barcodesFromOtherStore.size()));

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
        return falseSku;
    }

}
