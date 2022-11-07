package org.example;

import java.sql.*;
import java.util.Scanner;

public class LogInAndRegist {
    private int lengthOfUsers = 0;
    private int ID;
    private String name;
    private String password;
    String city;
    String country;

    public boolean LogIn() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Your nickname : ");
        this.name = scan.nextLine();
        System.out.println("Your password : ");
        this.password = scan.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select Count(Name) from Users Where Name = '" + name + "' AND Password = '" + password + "';");
            int n = 0;
            if (rs.next()) {
                n = rs.getInt(1);
            }
            if (n == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void Regist() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your nickname : ");
        this.name = scan.nextLine();
        System.out.println("Write your password : ");
        this.password = scan.nextLine();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select ID from Users");
            while (rs.next()) {
                lengthOfUsers++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lengthOfUsers++;
        ID = lengthOfUsers;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute("Insert Into Profile (ID , Account) Values(" + lengthOfUsers + " , " + 0 + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute("Insert Into Users Values('" + name + "' , '" + password + "' , '" + lengthOfUsers + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int TakeID() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Users Where Name = '" + name + "';");
            while (rs.next()) {
                this.ID = rs.getInt(3);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ID;
    }
    public void AdditionalInfo() {
        Scanner scan = new Scanner(System.in);
//        System.out.println("Do you want add an additional information ");
//        System.out.println("If yes press 1 , else press 2");
//        int i = scan.nextInt();
            System.out.println("Your city : ");
            city = scan.nextLine();
            System.out.println("Your country : ");
            country = scan.nextLine();
            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
                Statement stmt = con.createStatement();
                boolean rs = stmt.execute("Update Profile Set City = '" + city + "' , Country = '" + country + "' Where ID = " + ID + ";");
                System.out.println("Successful");
            } catch (SQLException e) {
                System.out.println(e);
        }
    }
}
