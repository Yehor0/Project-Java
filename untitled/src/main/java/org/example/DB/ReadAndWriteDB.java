package org.example.DB;

import java.sql.*;

public class ReadAndWriteDB {
    private static final String NAME_DB = "Project";
    static private Connection con = null;
    protected ResultSet connectRead(String command) {
        try {
            PreparedStatement stmt = con.prepareStatement(command);
            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void connectWrite(String command) {
        try {
            PreparedStatement stmt = con.prepareStatement(command);
            boolean rs = stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeCon() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static {
        try {
            con = DriverManager.getConnection(("jdbc:mysql://localhost:3306/"  + NAME_DB), "root", "561151181Yehor*");
            //TODO Move configuration to separate file
            //TODO Store configuration in JSON format
            //TODO Use JSON library
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
