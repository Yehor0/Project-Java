package org.example.DB;

import java.sql.*;

public class ReadAndWriteDB {
    private static final String nameDB = "Project";
    static private Connection con = null;
    protected ResultSet ConnectRead(String command) {
        try {
            PreparedStatement stmt = con.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void ConnectWrite(String command) {
        try {
            PreparedStatement stmt = con.prepareStatement(command);
            boolean rs = stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            con = DriverManager.getConnection(("jdbc:mysql://localhost:3306/"  + nameDB), "root", "561151181Yehor*");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
