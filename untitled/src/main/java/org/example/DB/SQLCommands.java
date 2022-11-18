package org.example.DB;

import java.sql.*;

public class SQLCommands extends ReadAndWriteDB{
    private int ID;
    public int takeID(String name) {
        ResultSet rs = connectRead("Select ID from Users Where Name = '" + name + "'");
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
            ResultSet rs = connectRead("Select Count(Name) from Users Where Name = '" + name + "' AND Password = '" + password + "';");
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
        ResultSet rs = connectRead("Select Count(ID) from Users");
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
        connectWrite("Insert Into Profile (ID , Account) Values(" + ID + " , " + 0 + ");");
        connectWrite("Insert Into Users Values('" + name + "' , '" + password + "' , '" + ID + "');");
    }

    public void additionalInfo(String city, String country) {
        connectWrite("Update Profile Set City = '" + city + "' , Country = '" + country + "' Where ID = " + ID + ";");
        System.out.println("Successful");
    }

    public void showProfile(int ID) {
        ResultSet rs = connectRead("Select Users.Name ,  Profile.City , Profile.Country , Profile.Account from Profile Inner Join Users ON Profile.ID = Users.ID Where Profile.ID = " + ID);
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
        ResultSet rs = connectRead("Select Shares.Name , Shares.Price , Purchase.Quantity from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id);
        return rs;
    }
    public ResultSet getUnBuyShares(int id) throws SQLException {
        ResultSet rs = connectRead("Select Name , Price , Quantity From Shares Where Quantity IS Not Null");
        return rs;
    }
    public void sellShares(String str , int quantity , int id) {
        try {
            ResultSet rs = connectRead("Select Shares.ID ,  Shares.Quantity , Purchase.Quantity  from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id + " And Name = '" + str + "'");
            while (rs.next()) {
                if (rs.getInt(3) == quantity) {
                    connectWrite("Delete Purchase From Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id + " And Name = '" + str + "'");
                    connectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = '" + str + "'");
                    connectWrite("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account + (Shares.Price * " + quantity + ")) Where Profile.ID = " + id + " AND Shares.Name = '" + str + "'");
                } else if (rs.getInt(3) < quantity) {
                    System.out.println("ERROR \n You don't have so many shares");
                } else if (rs.getInt(3) > quantity) {
                    connectWrite("Update Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Set Purchase.Quantity = " + (rs.getInt(3) - quantity) + " Where Purchase.OwnerID = " + id + " And Shares.Name = '" + str + "'");
                    connectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = '" + str + "'");
                    connectWrite("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account + (Shares.Price * " + quantity + ")) Where Profile.ID = " + id + " AND Shares.Name = '" + str + "'");
                }
            }
            System.out.println("\n Successful \n");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void buyShares(String str , int quantity , int id) {
        boolean isEnough = false;
        boolean isBoughtThisBefore = false;
        int idShare = 0;
        try{
        ResultSet rsCheck = connectRead("Select Shares.Price , Profile.Account From Profile Cross Join Shares Where Profile.ID = " + id + " And Shares.Name = '" + str + "'");
            while (rsCheck.next()) {
                if (rsCheck.getInt(1) * quantity <= rsCheck.getInt(2)) {
                    isEnough = true;
                }
            }
            rsCheck = connectRead("Select * From Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Name = '" + str + "'");
            try {
                int n = 0;
                while (rsCheck.next()){
                    n++;
                }
                if (n == 1) {
                    isBoughtThisBefore = true;
                }
            }catch (Exception e) {
                System.out.println(e);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        if (isEnough) {
            if (isBoughtThisBefore) {
                ResultSet rs = connectRead("Select ID , Quantity From Shares Where Name = '" + str + "'");
                try{
                    while (rs.next()) {
                        connectWrite("Update Purchase Set Quantity = Quantity + " + quantity + " Where OwnerID = " + id);
                        connectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) - quantity) + " Where Shares.Name = '" + str + "'");
                        connectWrite("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account - (Shares.Price * " + quantity + ")) Where Profile.ID = " + id + " AND Shares.Name = '" + str + "'");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else {
                try {
                    ResultSet rs = connectRead("Select ID , Quantity From Shares Where Name = '" + str + "'");
                    while (rs.next()) {
                        connectWrite("Insert Into Purchase (SharesID , OwnerID , Quantity) Values(" + rs.getInt(1) + " , " + id + " , " + quantity + " );");
                        connectWrite("Update Shares Set Shares.Quantity = " + (rs.getInt(2) - quantity) + " Where Shares.Name = '" + str + "'");
                        connectWrite("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account - (Shares.Price * " + quantity + ")) Where Profile.ID = " + id + " AND Shares.Name = '" + str + "'");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("\n Successful \n");
        }else {
            System.out.println("\n Don't enough money \n");
        }
    }
    public boolean checkAdditionalInfo(int id) {
        int n = 0;
        boolean didAdd = false;
        try {
            ResultSet rs = connectRead("Select * From Profile Where ID = " + id + " And City Is Not Null");
            while (rs.next()) {
                n++;
            }
            if (n == 1) {
                didAdd = true;
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return didAdd;
    }
}
