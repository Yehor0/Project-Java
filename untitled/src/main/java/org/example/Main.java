package org.example;

import lombok.NonNull;
import org.example.DB.ReadAndWriteDB;
import org.example.Share.Shares;
import org.example.User.LogIn;
import org.example.User.Regist;
import org.example.User.YourProfile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        LogIn logIn = new LogIn();
        Regist reg = new Regist();
        Shares shares = new Shares();
        YourProfile yourProfile = new YourProfile();
        ReadAndWriteDB close = new ReadAndWriteDB();
        String shareName;

        boolean isLogIn = false;
        boolean isReg = false;
        int yourID = 0;
        int scanRes = 0;


        do {
            if (isLogIn || isReg) {
                if (isReg) {
                   yourID = reg.getID();
                }else {
                   yourID =  logIn.getID();
                }
                shares.setID(yourID);
                System.out.println("3 exit");
                System.out.println("4 see shares, what you bought");
                System.out.println("5 see shares, what you can bought");
                System.out.println("6 see your profile");
                System.out.println("7 sell your shares");
                System.out.println("8 buy shares");
                try {
                    scanRes = scan.nextInt();
                    //TODO Check negative numbers
                    //TODO Check if number is between 3-8
                } catch (Exception e) {
                    System.out.println("Please write a number");
                    continue;
                }
                switch(scanRes) {
                    case 4 :
                        shares.showYourShares();
                        break;
                    case 5 :
                        shares.showUnBuyShares();
                        break;
                    case 6 :
                        yourProfile.showProfile(yourID);
                        break;
                    case 7 :
                        shares.showYourShares();
                        System.out.println("What do you want sell : " );
                        scan.nextLine();
                        shareName = scan.nextLine();
                        System.out.println("How many : ");
                        scanRes = scan.nextInt();
                        shares.sellShares(shareName , scanRes , yourID);
                        scanRes = 0;
                        break;
                    case 8 :
                        shares.showUnBuyShares();
                        System.out.println("What do you want buy : " );
                        scan.nextLine();
                        shareName = scan.nextLine();
                        System.out.println("How many : ");
                        scanRes = scan.nextInt();
                        shares.buyShares(shareName , scanRes , yourID);
                        scanRes =0;
                        break;
                    default:
                        System.out.println("Wrong num");
                        break;
                }
            } else {
                System.out.println("Press : ");
                System.out.println("1 login");
                System.out.println("2 registration");
                System.out.println("3 exit");
                try {
                    scanRes = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Please write a number");
                    break;
                }
                switch (scanRes) {
                    case 1:
                        wh:while (isLogIn != true || isReg != true) {
                            if (logIn.logInFunc() == false) {
                                System.out.println("Password or nickname incorrect");
                                System.out.println("If want registration write 1 or if you want go back write 2");
                                scanRes = scan.nextInt();
                                switch (scanRes) {
                                    case 1 :
                                        reg.regist();
                                        System.out.println("Do you want add an additional information ");
                                        System.out.println("If yes press 1 , else press 2");
                                        int i = scan.nextInt();
                                        if (i == 1) {
                                            reg.additionalInfo();
                                        }
                                        isReg = true;
                                        scanRes = 0;
                                        break;
                                    case 2 :
                                        scanRes =0;
                                        break wh;
                                }
                            } else {
                                System.out.println("Successful");
                                isLogIn = true;
                                break;
                            }
                        }
                        break;
                    case 2:
                            reg.regist();
                            System.out.println("Do you want add an additional information ");
                            System.out.println("If yes press 1 , else press 2");
                            int i = scan.nextInt();
                            if (i == 1) {
                                reg.additionalInfo();
                            }
                        break;
                    default:
                        System.out.println("Wrong num");
                        break;
                }
            }
            if (scanRes == 3) {
                close.closeCon();
                break;
            }
        }while (true);
    }
}