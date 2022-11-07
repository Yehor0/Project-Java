package org.example;

import java.sql.*;

public class Shares {
    int ID;
    public void ShowYourShares(int ID) {
        this.ID = ID;
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select Name , Price , ID From Shares Where OwnerID = " + ID + ";" );
            System.out.println("Your Shares : ");
            while (rs.next()) {
                System.out.println("Name : " + rs.getString(1));
                System.out.println("Price : " + rs.getInt(2) + "$");
                System.out.println("Write if you want to sell  : " + rs.getInt(3) + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ShowUnBuyShares() {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select Name , Price , ID From Shares Where OwnerID Is Null;" );
            System.out.println("You can buy : ");
            while (rs.next()) {
                System.out.println("Name : " + rs.getString(1));
                System.out.println("Price : " + rs.getInt(2) + "$\n");
                System.out.println("Write " + rs.getInt(3) + " to buy \n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void BuyShares(int ID , int IDShares) {
        int price = 0;
        int account = 0;
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = con.createStatement();
            boolean rs = stmt.execute("Update Shares Set OwnerID = " + ID + " Where ID =" + IDShares + ";");
            System.out.println("Successful");
        } catch (SQLException e) {
            System.out.println(e);
        }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = con.createStatement();
            ResultSet resPrice = stmt.executeQuery("Select * From Shares Where ID =" + IDShares );
            while (resPrice.next()) {
                price = resPrice.getInt(3);
            }
            ResultSet resAccount = stmt.executeQuery("Select * From Profile Where ID =" + ID );
            while (resAccount.next()) {
                account = resAccount.getInt(4);
            }
            System.out.println("Your Account after buying : " + (account - price));
            try {
                boolean rs = stmt.execute("Update Profile Set Account = " + (account - price) + " Where ID=" + ID );
            }catch (Exception e) {
                System.out.println("Insufficient funds");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void SellShares(int ID , int IDShares) {
        int price = 0;
        int account = 0;
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = con.createStatement();
            boolean rs = stmt.execute("Update Shares Set OwnerID = null  Where ID =" + IDShares + ";");
            System.out.println("Successful");
        } catch (SQLException e) {
            System.out.println(e);
        }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
            Statement stmt = con.createStatement();
            ResultSet resPrice = stmt.executeQuery("Select * From Shares Where ID =" + IDShares );
            while (resPrice.next()) {
                price = resPrice.getInt(3);
            }
            ResultSet resAccount = stmt.executeQuery("Select * From Profile Where ID =" + ID );
            while (resAccount.next()) {
                account = resAccount.getInt(4);
            }
            System.out.println("Your Account after buying : " + (account + price));
            try {
                boolean rs = stmt.execute("Update Profile Set Account = " + (account + price) + " Where ID=" + ID );
            }catch (Exception e) {
                System.out.println("Insufficient funds");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
