package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "561151181Yehor*")) {
//            Statement stmt = connection.createStatement();
//            boolean rs = stmt.execute("Select * from Users Where Exists(SELECT * FROM Users WHERE Name = '" + "A" + "');");
//            System.out.println(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        Scanner scan = new Scanner(System.in);
        int scanRes = 0;
        LogInOrRegist logOrReg = new LogInOrRegist();
        boolean isLogIn = false;
        int yourID;
        do {
            if (isLogIn){
                yourID = logOrReg.getID();
                System.out.println("4 for see your shares");
                System.out.println("5 for buy shares");
                System.out.println("3 for exit");
                System.out.println(yourID);
            }else {
                System.out.println("Press : ");
                System.out.println("1 for login");
                System.out.println("2 for registration");
                System.out.println("3 for exit");
            }
            try {
                scanRes = scan.nextInt();
            } catch (Exception e) {};
            if(scanRes == 0) {
                System.out.println("Please write a number");
                scanRes = scan.nextInt();
            }else if (scanRes == 1) {
                    while (isLogIn != true){
                    if (logOrReg.LogIn() != true) {
                        System.out.println("Password or nickname incorrect");
                        System.out.println("If would registration write 1 or if you would log in write 2");
                        scanRes = scan.nextInt();
                        if (scanRes == 1 ) {
                            scanRes = 2;
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        System.out.println("Successful");
                        isLogIn = true;
                        break;
                    }
                }
                }
                if (scanRes == 2) {
                    logOrReg.Regist();
                }
        }while (true);
    }
}