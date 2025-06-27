package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketsAmountOnDashboard {

    static String requestCommon = "SELECT COUNT(*) FROM app.tickets where store_id = " + TestConfigDB.testConfig.storeId() + " and canceled = 0 and status != 'Ordering' ";
    private static Connection connection;
    private static Statement statement;
    static String resultValue;

    public static String getDataBaseValue(String request) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            List<String> barcodes = new ArrayList<>();
            while (resultSet.next()) {
                barcodes.add(resultSet.getString("COUNT(*)"));
            }

            resultValue = barcodes.get(0);

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

    public static String getToday() {
        String sql = requestCommon + " AND DATE(CONVERT_TZ(created_at, 'UTC', 'America/Chicago')) = DATE(CONVERT_TZ(CURRENT_DATE(), 'UTC', 'America/Chicago'))";
        return getDataBaseValue(sql);
    }

    public static String getOnShelf() {
        return getDataBaseValue(requestCommon + " and status = 'Ready for Pick-Up'");
    }

    public static String getInProgress() {
        return getDataBaseValue(requestCommon + " and (status = 'In-Progress' or status = 'Dye in Houston')");
    }

    public static String getWithoutDetails() {
        return getDataBaseValue(requestCommon + " and ticket_details_id IS NULL and (status = 'In-Progress' or status = 'Dye in Houston' or status = 'Repaired') and drop_off_date between adddate(now(), INTERVAL -30 DAY) and now()");
    }
}
