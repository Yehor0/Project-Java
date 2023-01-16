package org.example;

import org.example.DB.SQLCommands;
import org.example.Share.Shares;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        ConsoleManager consoleManager = initializer.getConsoleManager();
        SQLCommands sql = initializer.getSql();
        Scanner scan = new Scanner(System.in);
        UserObj user = initializer.getUser();
        Shares shares = initializer.getShares();
        SwitchCaseMain caseWork = initializer.getSwitchCaseMain();

        boolean isLogIn = false;
        int yourId = 0;
        int scanRes = 0;



        all:
        do {
            if (isLogIn) {
                yourId = user.getId();
                shares.setID(yourId);
                consoleManager.showMenuSecond();
                try {
                    scanRes = scan.nextInt();
                } catch (Exception e) {
                    consoleManager.writeNum();
                    scanRes = 0;
                    continue;
                }
                switch (scanRes) {
                    case 1:
                        caseWork.first();
                        break;
                    case 2:
                        caseWork.second();
                        break;
                    case 4:
                        caseWork.fourth();
                        break;
                    case 5:
                        caseWork.fifth(yourId);
                        break;
                    case 6:
                        caseWork.sixth(yourId);
                        break;
                    case 3:
                        break all;
                    default:
                        consoleManager.wrongNum();
                        scanRes = 0;
                        continue;
                }
            } else {
                consoleManager.showMenuFirst();
                try {
                    scanRes = scan.nextInt();
                } catch (Exception e) {
                    consoleManager.writeNum();
                    scanRes = 0;
                    break;
                }
                switch (scanRes) {
                    case 1:
                        isLogIn = caseWork.firstLogIn();
                        break;
                    case 2:
                        caseWork.secondLogIn();
                        break;
                    case 3:
                        break all;
                    default:
                        consoleManager.wrongNum();
                        scanRes = 0;
                        continue;
                }
            }
        } while (true);
    }
}