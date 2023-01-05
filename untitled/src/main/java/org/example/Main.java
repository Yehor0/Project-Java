package org.example;

import org.example.DB.SQLCommands;
import org.example.Share.Shares;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleManager consoleManager = new ConsoleManager();
        SQLCommands sql = new SQLCommands(consoleManager);
        Scanner scan = new Scanner(System.in);
        UserObj user = new UserObj(consoleManager , sql);
        Shares shares = new Shares(sql , consoleManager);

        String shareName;
        boolean isLogIn = false;
        int yourId = 0;
        int scanRes = 0;


        all:do {
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
                switch(scanRes) {
                    case 1 :
                        shares.showYourShares();
                        break;
                    case 2 :
                        shares.showUnBuyShares();
                        break;
                    case 4 :
                        user.showProfile();
                        break;
                    case 5 :
                        shares.showYourShares();
                        consoleManager.whatSell();
                        scan.nextLine();
                        shareName = scan.nextLine();
                        consoleManager.howMany();
                        scanRes = scan.nextInt();
                        shares.sellShares(shareName , scanRes , yourId);
                        scanRes = 0;
                        break;
                    case 6 :
                        shares.showUnBuyShares();
                        consoleManager.whatSell();
                        scan.nextLine();
                        shareName = scan.nextLine();
                        consoleManager.howMany();
                        scanRes = scan.nextInt();
                        shares.buyShares(shareName , scanRes , yourId);
                        scanRes =0;
                        break;
                    case 3 :
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
                        wh:while (isLogIn != true) {
                            if (user.logInFunc() == false) {
                                consoleManager.incorrectLogIn();
                                scanRes = scan.nextInt();
                                switch (scanRes) {
                                    case 1 :
                                        user.regist();
                                        consoleManager.addAdditionalInfo();
                                        int i = scan.nextInt();
                                        if (i == 1) {
                                            user.additionalInfo();
                                        }
                                        isLogIn = true;
                                        scanRes = 0;
                                        break;
                                    case 2 :
                                        scanRes =0;
                                        break wh;
                                }
                            } else {
                                consoleManager.successful();
                                isLogIn = true;
                                break;
                            }
                        }
                        break;
                    case 2:
                            user.regist();
                            consoleManager.addAdditionalInfo();
                            int i = scan.nextInt();
                            if (i == 1) {
                                user.additionalInfo();
                            }
                        break;
                    case 3 :
                        break all;
                    default:
                        consoleManager.wrongNum();
                        scanRes = 0;
                        continue;
                }
            }
        }while (true);
    }
}