package org.example.DB;

import java.sql.*;

public class SQLCommands extends ReadAndWriteDB{
    private int ID;
    public int takeID(String name) {
        ResultSet rs = ConnectRead("Select ID from Users Where Name = '" + name + "'");
        try {
            while (rs.next()) {
                ID = rs.getInt(1);
            }
            return ID;
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public boolean logIn(String name, String password) {
        try {
            ResultSet rs = ConnectRead("Select Count(Name) from Users Where Name = '" + name + "' AND Password = '" + password + "';");
            int n = 0;
            if (rs.next()) {
                n = rs.getInt(1);
            }
            if (n == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public void regist(String name, String password) {
        ResultSet rs = ConnectRead("Select Count(ID) from Users");
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                ID = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        ID++;
        ConnectWrite("Insert Into Profile (ID , Account) Values(" + ID + " , " + 0 + ");");
        ConnectWrite("Insert Into Users Values('" + name + "' , '" + password + "' , '" + ID + "');");
    }

    public void additionalInfo(String city, String country) {
        ConnectWrite("Update Profile Set City = '" + city + "' , Country = '" + country + "' Where ID = " + ID + ";");
        System.out.println("Successful");
    }

    public void showProfile(int ID) {
        ResultSet rs = ConnectRead("Select Users.Name ,  Profile.City , Profile.Country , Profile.Account from Profile Inner Join Users ON Profile.ID = Users.ID Where Profile.ID = " + ID);
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println("Name : " + rs.getString(1));
                System.out.println("City : " + rs.getString(2));
                System.out.println("Country : " + rs.getString(3));
                System.out.println("Account: " + rs.getInt(4) + "\n");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public ResultSet getYourShares(int id) throws SQLException {
        ResultSet rs = ConnectRead("Select Shares.Name , Shares.Price , Purchase.Quantity from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id);
        return rs;
    }
    public ResultSet getUnBuyShares(int id) throws SQLException {
        ResultSet rs = ConnectRead("Select Name , Price , Quantity From Shares Where Quantity IS Not Null");
        return rs;
    }
    public void sellShares(String str , int quantity , int id) {
        try {
            ResultSet rs = ConnectRead("Select Shares.ID ,  Shares.Quantity , Purchase.Quantity  from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id + " And Name = '" + str + "'");
            while (rs.next()) {
                if (rs.getInt(3) == quantity) {
                    ConnectWrite("Delete Purchase From Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id + " And Name = '" + str + "'");
                    ConnectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = '" + str + "'");
                    ConnectWrite("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account + (Shares.Price * " + quantity + ")) Where Profile.ID = " + id + " AND Shares.Name = '" + str + "'");
                } else if (rs.getInt(3) < quantity) {
                    System.out.println("ERROR \n You don't have so many shares");
                } else if (rs.getInt(3) > quantity) {
                    ConnectWrite("Update Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Set Purchase.Quantity = " + (rs.getInt(3) - quantity) + " Where Purchase.OwnerID = " + id + " And Shares.Name = '" + str + "'");
                    ConnectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = '" + str + "'");
                    ConnectWrite("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account + (Shares.Price * " + quantity + ")) Where Profile.ID = " + id + " AND Shares.Name = '" + str + "'");
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
//    public void buyShares(String str , int quantity , int id) {
//        int idShare = 0;
//        try {
//            ResultSet rs = ConnectRead("Select Shares.ID ,  Shares.Quantity , Purchase.Quantity  from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id + " And Name = '" + str + "'");
//            while (rs.next()) {
//                if (rs.getInt(2) == quantity) {
//                    ConnectWrite("Update Shares Set Shares.Quantity = 0 Where Shares.Name = '" + str + "'");
//                    ResultSet resRead = ConnectRead("Select ID Shares Where Name = '" + str + "'");
//                    while (resRead.next()) {
//                        idShare = resRead.getInt(1);
//                    }
//                    ConnectWrite("Insert Into Purchase Inner Join Share (Purchase.SharesID , Purchase.OwnerId , Purchase.Quantity ) Values(" + idShare + " , " + id + " , '" + str + "'");
//
//                } else if (rs.getInt(2) < quantity) {
//                    System.out.println("ERROR \n You can't buy more shares than we have");
//                } else if (rs.getInt(2) > quantity) {
//                    ConnectWrite("Update Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Set Purchase.Quantity = " + (rs.getInt(3) - quantity) + " Where Purchase.OwnerID = " + id + " And Shares.Name = '" + str + "'");
//                    ConnectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = '" + str + "'");
//                }
//            }
//        }catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
