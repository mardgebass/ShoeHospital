package com.shoehospital.database;

import com.shoehospital.config.TestConfigDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class AveragePriceOnDashboard {

    public static String getAverageValue() {

        String resultValue = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(TestConfigDB.testConfig.url(), TestConfigDB.testConfig.user(), TestConfigDB.testConfig.password());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT estimation_cost FROM app.tickets WHERE store_id = " + TestConfigDB.testConfig.storeId() + " AND (drop_off_date between adddate(now(),-7) and now()) and estimation_cost <> 0 and canceled = 0");

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
        }
        return resultValue;
    }
}
