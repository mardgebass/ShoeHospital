package com.shoehospital;

import com.shoehospital.config.TestConfigDB;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class DataBaseRepository{

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
    static String resultValue;

    SecureRandom random = new SecureRandom();

    public String getBarcode(String request, String columnLabel) {

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

    public String getSku() {
        sku = getBarcode(requestSku, "barcode");
        return sku;
    }

    public String getFalseSku() {
        falseSku = getBarcode(requestNotAvailableSku, "barcode");
        return falseSku;
    }

    public String getSum() {
        resultValue = getBarcode(requestSum + sku, "sale_price");
        sum = String.format(Locale.ENGLISH, "%(.2f",(parseDouble(resultValue))*2*1.0825);
        return sum;
    }

//  sum = String.format(Locale.ENGLISH, "%(.2f",(resultSet.getDouble("sale_price"))*2*1.0825);

}
