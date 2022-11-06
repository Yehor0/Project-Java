package org.example;

import java.sql.*;

public class YourProfile {
    public void Show(int ID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select Users.Name ,  Profile.City , Profile.Country , Profile.Account from Profile Inner Join Users ON Profile.ID =Users.ID Where Profile.ID = " + ID );
            while (rs.next()) {
                System.out.println("Name : " + rs.getString(1));
                System.out.println("City : " + rs.getString(2));
                System.out.println("Country : " + rs.getString(3));
                System.out.println("Account: " + rs.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
