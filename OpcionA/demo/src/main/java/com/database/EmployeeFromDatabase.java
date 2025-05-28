package com.database;

import com.model.Employee;
import java.sql.*;

public class EmployeeFromDatabase {

    private static final String URL = "jdbc:postgresql://dpg-d0ri4cqli9vc738l1vmg-a.oregon-postgres.render.com:5432/pds_empleados";
    private static final String USER = "user";
    private static final String PASSWORD = "n5tRrgGJP12mM9AGV7923S24oyRS1I4R";

    public Employee getByCode(String code) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = "SELECT * FROM employees WHERE code = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("position")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
