package org.example.User;

import org.example.DB.SQLCommands;

import java.util.Scanner;

public class YourProfile {
    SQLCommands sql = new SQLCommands();
    Scanner scan = new Scanner(System.in);
    Regist reg = new Regist();
    public void ShowProfile(int ID) {
       sql.showProfile(ID);
       if (!sql.checkAdditionalInfo(ID)) {
           System.out.println("Do you want add an additional information ");
           System.out.println("If yes press 1 , else press 2");
           int i = scan.nextInt();
           if (i == 1) {
               reg.additionalInfo();
           }
       }
    }
}
