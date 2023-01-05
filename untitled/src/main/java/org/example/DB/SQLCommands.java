package org.example.DB;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.ConsoleManager;

import java.sql.*;
@RequiredArgsConstructor()
public class SQLCommands extends ReadAndWriteDB{
    private int id;
    @NonNull
    ConsoleManager consoleManager;
    public int takeID(String name) {
//        ResultSet rs = readFromDB("Select ID from Users Where Name = '" + name + "'");
        ResultSet rs = readFromDB("Select ID from Users Where Name = ? " , name);
        try {
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        }catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public boolean logIn(String name, String password) {
        try {
            //ResultSet rs = readFromDB("Select Count(Name) from Users Where Name = '" + name + "' AND Password = '" + password + "';");
           ResultSet rs = readFromDB("Select Count(Name) from Users Where Name = ? AND Password =  ? ;" , name, password);
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
        ResultSet rs = readFromDB("Select Count(ID) from Users");
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        id++;
        writeToDB("Insert Into Profile (ID , Account) Values(? , 0);" , Integer.toString(id) );
        writeToDB("Insert Into Users Values(? , ? , ?);" , name , password , Integer.toString(id));
    }

    public void additionalInfo(String city, String country) {
        writeToDB("Update Profile Set City = ? , Country = ? Where ID = ? ;" , city , country , Integer.toString(id));
        System.out.println("Successful");
    }

    public void showProfile(int id) {
        ResultSet rs = readFromDB("Select Users.Name ,  Profile.City , Profile.Country ," +
                " Profile.Account from Profile Inner Join Users ON Profile.ID = Users.ID Where Profile.ID = ? " , Integer.toString(id));
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                consoleManager.showProfileInfo(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getInt(4));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public ResultSet getYourShares(int id) throws SQLException {
//        ResultSet rs = readFromDB("Select Shares.Name , Shares.Price , Purchase.Quantity from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id);
        ResultSet rs = readFromDB("Select Shares.Name , Shares.Price , Purchase.Quantity from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = ?" , Integer.toString(id));
        return rs;
    }
    public ResultSet getUnBuyShares(int id) throws SQLException {
        ResultSet rs = readFromDB("Select Name , Price , Quantity From Shares Where Quantity IS Not Null");
        return rs;
    }
    public void sellShares(String name , int quantity , int id) {
        try {
//            ResultSet rs = readFromDB("Select Shares.ID ,  Shares.Quantity , Purchase.Quantity  from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = " + id + " And Name = '" + name + "'");
            ResultSet rs = readFromDB("Select Shares.ID ,  Shares.Quantity , Purchase.Quantity  from Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Purchase.OwnerID = ? And Name = ? " , Integer.toString(id) , name );
            while (rs.next()) {
                if (rs.getInt(3) == quantity) {
                    writeToDB("Delete Purchase From Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Where Purchase.OwnerID = ? And Name = ?" , Integer.toString(id) , name);
                    writeToDB("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = ?" , name);
                    writeToDB("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account + (Shares.Price * " + quantity + ")) Where Profile.ID = ? AND Shares.Name = ?" , Integer.toString(id) , name);
                    consoleManager.successful();
                } else if (rs.getInt(3) < quantity) {
                    consoleManager.lessShares();
                } else if (rs.getInt(3) > quantity) {
                    writeToDB("Update Purchase Inner Join Shares ON Shares.ID = Purchase.SharesID Set Purchase.Quantity = " + (rs.getInt(3) - quantity) + " Where Purchase.OwnerID = ? And Shares.Name = ?" , Integer.toString(id) , name);
                    writeToDB("Update Shares Set Shares.Quantity = " + (rs.getInt(2) + quantity) + " Where Shares.Name = ?" , name);
                    writeToDB("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account + (Shares.Price * " + quantity + ")) Where Profile.ID = ? AND Shares.Name = ?" , Integer.toString(id) , name);
                    consoleManager.successful();
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void buyShares(String name , int quantity , int id) {
        boolean isEnough = false;
        boolean isBoughtThisBefore = false;
        int idShare = 0;
        try{
            ResultSet rsCheck = readFromDB("Select Shares.Price , Profile.Account From Profile Cross Join Shares Where Profile.ID = ? And Shares.Name = ?" , Integer.toString(id) , name);
            while (rsCheck.next()) {
                if (rsCheck.getInt(1) * quantity <= rsCheck.getInt(2)) {
                    isEnough = true;
                }
            }
//            rsCheck = readFromDB("Select * From Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Name = '" + name + "'");
            rsCheck = readFromDB("Select * From Purchase Inner Join Shares On Shares.ID = Purchase.SharesID Where Name = ?" , name);
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
//                ResultSet rs = readFromDB("Select ID , Quantity From Shares Where Name = '" + name + "'");
                ResultSet rs = readFromDB("Select ID , Quantity From Shares Where Name = ?" , name);
                try{
                    while (rs.next()) {
                        writeToDB("Update Purchase Set Quantity = Quantity + ? Where OwnerID = ?" , Integer.toString(quantity) , Integer.toString(id));
                        writeToDB("Update Shares Set Shares.Quantity = " + (rs.getInt(2) - quantity) + " Where Shares.Name = ?" , name);
                        writeToDB("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account - (Shares.Price * ?)) Where Profile.ID = ? AND Shares.Name = ?" , Integer.toString(quantity) , Integer.toString(id) , name);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }else {
                try {
                    //ResultSet rs = readFromDB("Select ID , Quantity From Shares Where Name = '" + name + "'");
                     ResultSet rs = readFromDB("Select ID , Quantity From Shares Where Name = ?" + name);
                    while (rs.next()) {
                        writeToDB("Insert Into Purchase (SharesID , OwnerID , Quantity) Values(" + rs.getInt(1) + " , ? , ? );" , Integer.toString(id) , Integer.toString(quantity));
                        writeToDB("Update Shares Set Shares.Quantity = " + (rs.getInt(2) - quantity) + " Where Shares.Name = ?" , name);
                        writeToDB("Update Profile Cross Join Shares Set Profile.Account = (Profile.Account - (Shares.Price * ?)) Where Profile.ID = ? AND Shares.Name = ?" , Integer.toString(quantity) , Integer.toString(id) , name);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            consoleManager.successful();
        }else {
            consoleManager.error();
        }
    }
    public boolean checkAdditionalInfo(int id) {
        int n = 0;
        boolean didAdd = false;
        try {
            //ResultSet rs = readFromDB("Select * From Profile Where ID = " + id + " And City Is Not Null");
            ResultSet rs = readFromDB("Select * From Profile Where ID = ? And City Is Not Null" , Integer.toString(id));
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
    public boolean checkIsShareExist(String name) {
        int n = 0;
        boolean isExist = false;
        try {
            ResultSet rs = readFromDB("Select * From Shares Where Name = ?"  , name);
            //ResultSet rs = readFromDB("Select * From Shares Where Name = '" + name + "';");
            while (rs.next()) {
                n++;
            }
            if (n >= 1) {
                isExist = true;
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return isExist;
    }
}
