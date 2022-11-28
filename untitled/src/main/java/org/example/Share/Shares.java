package org.example.Share;

import org.example.DB.SQLCommands;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shares {
    SQLCommands sql = new SQLCommands();

    private int ID;

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
    public void showUnBuyShares() {
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
    public void buyShares(String shareName , int quantity , int id) {
            shareName = shareName.toLowerCase();
            //TODO check if share with shareName exists
            sql.buyShares(shareName , quantity , id);
    }
}