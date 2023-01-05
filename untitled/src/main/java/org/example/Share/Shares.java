package org.example.Share;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.ConsoleManager;
import org.example.DB.SQLCommands;

import java.sql.ResultSet;
import java.sql.SQLException;
@RequiredArgsConstructor()
public class Shares {
    @NonNull
    private SQLCommands sql;
    @NonNull
    private ConsoleManager consoleManager;

    private int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void showYourShares() {
        try {
            ResultSet rs = sql.getYourShares(ID);
            while (rs.next()) {
                consoleManager.showShareInfo(rs.getString(1) , rs.getInt(2) , rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showUnBuyShares() {
        try {
            ResultSet rs = sql.getUnBuyShares(ID);
            while (rs.next()) {
                consoleManager.showShareInfo(rs.getString(1) , rs.getInt(2) , rs.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sellShares(String shareName , int quantity , int id){
        if (sql.checkIsShareExist(shareName)) {
            sql.sellShares(shareName.toLowerCase(), quantity, id);
        }else {
            consoleManager.wrongNameOfShare();
        }
    }
    public void buyShares(String shareName , int quantity , int id) {
            if (sql.checkIsShareExist(shareName)) {
                shareName = shareName.toLowerCase();
                //TODO check if share with shareName exists
                sql.buyShares(shareName, quantity, id);
            }else {
                consoleManager.wrongNameOfShare();
            }
    }
}