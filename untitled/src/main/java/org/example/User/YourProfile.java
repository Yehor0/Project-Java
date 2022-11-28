package org.example.User;

import org.example.DB.SQLCommands;

import java.util.Scanner;

public class YourProfile {
    private static final String _EXAMPLE_FOR_ME = "";
    private SQLCommands sql = new SQLCommands();
    private Scanner scan = new Scanner(System.in);
    private Regist reg = new Regist();
    public void showProfile(int id) {
       sql.showProfile(id);
       if (!sql.checkAdditionalInfo(id)) {
           System.out.println("Do you want add an additional information ");
           System.out.println("If yes press 1 , else press 2");
           int i = scan.nextInt();
           if (i == 1) {
               reg.additionalInfo();
           }
       }
    }
}
