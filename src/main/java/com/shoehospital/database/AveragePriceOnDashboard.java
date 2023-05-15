package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class AveragePriceOnDashboard {

    private static Connection connection;
    private static Statement statement;

    static String resultValue;

    public static String getAverageValue() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT estimation_cost FROM app.tickets WHERE store_id = " + TestConfigDB.testConfig.storeId() + " AND (drop_off_date between adddate(now(),-8) and now()) and estimation_cost <> 0");

            List<Double> costs = new ArrayList<>();
            while (resultSet.next()) {
                costs.add(resultSet.getDouble("estimation_cost"));
            }

            DoubleSummaryStatistics stats = costs.stream()
                    .mapToDouble(Double::doubleValue)
                    .summaryStatistics();

            resultValue = String.valueOf(stats);

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
}
