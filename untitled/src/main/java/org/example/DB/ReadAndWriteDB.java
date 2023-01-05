package org.example.DB;

import org.example.JSONWork.JsonReader;

import java.sql.*;

public class ReadAndWriteDB {
    static private Connection con = null;

    protected ResultSet readFromDB(String command , String... arg) {
        try {
            PreparedStatement stmt = con.prepareStatement(command);
            //TODO stmt.setString();
            for (int i =1 ; i <= arg.length ;i++) {
                stmt.setString(i , arg[i-1]);
            }
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void writeToDB(String command , String... arg) {
        try {
            PreparedStatement stmt = con.prepareStatement(command);
            //TODO stmt.setString();
            for (int i =1 ; i <= arg.length ;i++) {
                stmt.setString(i , arg[i-1]);
            }
            boolean rs = stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        JsonReader jsonReader = new JsonReader();
        try {
            con = DriverManager.getConnection(jsonReader.getConnectionUrl() , jsonReader.getUserName() , jsonReader.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
