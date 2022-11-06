package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int scanRes = 0;
        LogInAndRegist logOrReg = new LogInAndRegist();
        boolean isLogIn = false;
        int yourID;
        do {
            if (isLogIn){
                yourID = logOrReg.TakeID();
                System.out.println(yourID);
                System.out.println("3 for exit");
                System.out.println("4 for see your shares");
                System.out.println("5 for buy shares");
                System.out.println("6 for buy shares");
                try {
                    scanRes = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Please write a number");
                    break;
                }
            }else {
                System.out.println("Press : ");
                System.out.println("1 for login");
                System.out.println("2 for registration");
                System.out.println("3 for exit");
                try {
                    scanRes = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Please write a number");
                    break;
                }
                if (scanRes == 1) {
                    while (isLogIn != true){
                        if (logOrReg.LogIn() == false) {
                            System.out.println("Password or nickname incorrect");
                            System.out.println("If would registration write 1 or if you would go back write 2");
                            scanRes = scan.nextInt();
                            if (scanRes == 1 ) {
                                scanRes = 2;
                                break;
                            } else {
                                scanRes = 1;
                                break;
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
            }
            if (scanRes == 3) {
                break;
            }
        }while (true);
    }
}