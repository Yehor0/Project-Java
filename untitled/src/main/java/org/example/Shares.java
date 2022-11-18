package org.example;

import org.example.DB.SQLCommands;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shares {
    SQLCommands sql = new SQLCommands();

    int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void showYourShares() {
        try {
            ResultSet rs = sql.getYourShares(ID);
            while (rs.next()) {
                System.out.println("Name : " + rs.getString(1));
                System.out.println("Price one : " + rs.getInt(2));
                System.out.println("Quantity : " + rs.getInt(3));
                System.out.println("All price : " + rs.getInt(2) * rs.getInt(3) + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ShowUnBuyShares() {
        try {
            ResultSet rs = sql.getUnBuyShares(ID);
            while (rs.next()) {
                System.out.println("Name : " + rs.getString(1));
                System.out.println("Price one : " + rs.getInt(2));
                System.out.println("Quantity : " + rs.getInt(3) + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sellShares(String name , int quantity , int id){
            sql.sellShares(name.toLowerCase() , quantity, id);
    }
    public void buyShares(String str , int quantity , int id) {
            str = str.toLowerCase();
            sql.buyShares(str , quantity , id);
    }
}