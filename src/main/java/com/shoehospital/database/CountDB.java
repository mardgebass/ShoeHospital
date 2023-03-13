package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountDB {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    static String resultValue;

    static String requestToday = "SELECT COUNT(*) FROM app.tickets where store_id = 4 and (created_at between adddate(CURDATE(), INTERVAL 6 HOUR) and now())";
    static String requestOnShelf = "SELECT COUNT(*) FROM app.tickets where store_id = 4 and status = 'Ready for Pick-Up'";
    static String requestWithoutDetails = "SELECT COUNT(*) FROM app.tickets where store_id = 4 and ticket_details_id IS NULL and status = 'In-Progress'";
    static String requestInProgress = "SELECT COUNT(*) FROM app.tickets where store_id = 4 and status = 'In-Progress'";

    static String withoutDetails;
    static String onShelf;
    static String inProgress;
    static String today;

    public static String getDataBaseValue(String request) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(request);

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
        today = getDataBaseValue(requestToday);
        return today;
    }

    public static String getOnShelf() {
        onShelf = getDataBaseValue(requestOnShelf);
        return onShelf;
    }

    public static String getInProgress() {
        inProgress = getDataBaseValue(requestInProgress);
        return inProgress;
    }

    public static String getWithoutDetails() {
        withoutDetails = getDataBaseValue(requestWithoutDetails);
        return withoutDetails;
    }
}
