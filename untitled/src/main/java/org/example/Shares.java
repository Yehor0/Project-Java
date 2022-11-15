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
    public void buyShares() {

    }
}
//    public void BuyShares(int ID , int IDShares) {
//        int price = 0;
//        int account = 0;
//        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
//            Statement stmt = con.createStatement();
//            boolean rs = stmt.execute("Update Shares Set OwnerID = " + ID + " Where ID =" + IDShares + ";");
//            System.out.println("Successful");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
//            Statement stmt = con.createStatement();
//            ResultSet resPrice = stmt.executeQuery("Select * From Shares Where ID =" + IDShares );
//            while (resPrice.next()) {
//                price = resPrice.getInt(3);
//            }
//            ResultSet resAccount = stmt.executeQuery("Select * From Profile Where ID =" + ID );
//            while (resAccount.next()) {
//                account = resAccount.getInt(4);
//            }
//            System.out.println("Your Account after buying : " + (account - price));
//            try {
//                boolean rs = stmt.execute("Update Profile Set Account = " + (account - price) + " Where ID=" + ID );
//            }catch (Exception e) {
//                System.out.println("Insufficient funds");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void SellShares(int ID) {
//        Scanner scan = new Scanner(System.in);
//        int price = 0;
//        int account = 0;
//        String name = scan.nextLine();
//        name.toLowerCase();
//        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
//            Statement stmt = con.createStatement();
//            boolean rs = stmt.execute("Update Shares Set OwnerID = null  Where Name ='" + name + "';");
//            System.out.println("Successful");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
//            Statement stmt = con.createStatement();
//            ResultSet resPrice = stmt.executeQuery("Select * From Shares Where Name ='" + name + "';" );
//            while (resPrice.next()) {
//                price = resPrice.getInt(3);
//            }
//            ResultSet resAccount = stmt.executeQuery("Select * From Profile Where ID =" + ID );
//            while (resAccount.next()) {
//                account = resAccount.getInt(4);
//            }
//            System.out.println("Your Account after buying : " + (account + price));
//            try {
//                boolean rs = stmt.execute("Update Profile Set Account = " + (account + price) + " Where ID=" + ID );
//            }catch (Exception e) {
//                System.out.println("Insufficient funds");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
