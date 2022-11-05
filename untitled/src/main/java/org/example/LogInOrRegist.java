package org.example;

import java.sql.*;
import java.util.Scanner;

public class LogInOrRegist {
    int lengthOfUsers = 0;
    int ID;
    private String name;
    private String password;

    public boolean LogIn() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Your nickname : ");
        this.name = scan.nextLine();
        System.out.println("Your password : ");
        this.password = scan.nextLine();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute("Select * from Users Where Exists (Select * from Users Where Name = '" + name + "' AND Password = '" + password + "');");
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void Regist() {
        this.lengthOfUsers++;
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
        ID = lengthOfUsers;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute("Insert Into Users Values('" + name + "' , '" + password + "' , '" + lengthOfUsers + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getID() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select ID from Users Where Name = '" + this.name + "';");
            this.ID = rs.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return ID;
        }
    }
