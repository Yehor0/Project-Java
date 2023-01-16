package org.example;

import lombok.AllArgsConstructor;
import org.example.Share.Shares;

import java.util.Scanner;

@AllArgsConstructor
public class SwitchCaseMain {
    Shares shares;
    UserObj user;
    ConsoleManager consoleManager;
    Scanner scan;

    public void first() {
        shares.showYourShares();
    }
    public void second() {
        shares.showUnBuyShares();
    }
    public void fourth() {
        user.showProfile();
    }
    public void fifth(int yourId) {
        shares.showYourShares();
        consoleManager.whatSell();
        scan.nextLine();
        String shareName = scan.nextLine();
        consoleManager.howMany();
        int scanRes = scan.nextInt();
        shares.sellShares(shareName, scanRes, yourId);
    }
    public void sixth(int yourId) {
        shares.showUnBuyShares();
        consoleManager.whatSell();
        scan.nextLine();
        String shareName = scan.nextLine();
        consoleManager.howMany();
        int scanRes = scan.nextInt();
        shares.buyShares(shareName, scanRes, yourId);
    }
    public void secondLogIn() {
        user.regist();
        consoleManager.addAdditionalInfo();
        int i = scan.nextInt();
        if (i == 1) {
            user.additionalInfo();
        }
    }
    public boolean firstLogIn() {
        boolean isLogIn = false;
        int scanRes;
        wh:
        while (isLogIn != true) {
            if (user.logInFunc() == false) {
                consoleManager.incorrectLogIn();
                scanRes = scan.nextInt();
                switch (scanRes) {
                    case 1:
                        user.regist();
                        consoleManager.addAdditionalInfo();
                        int i = scan.nextInt();
                        if (i == 1) {
                            user.additionalInfo();
                        }
                        isLogIn = true;
                        scanRes = 0;
                        break;
                    case 2:
                        scanRes = 0;
                        break wh;
                }
            } else {
                consoleManager.successful();
                isLogIn = true;
                break;
            }
        }
        return isLogIn;
    }
}
