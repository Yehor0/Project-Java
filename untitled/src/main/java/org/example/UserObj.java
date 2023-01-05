package org.example;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.DB.SQLCommands;

import java.beans.ConstructorProperties;
import java.util.Scanner;
@RequiredArgsConstructor()
public class UserObj {
    @NonNull
    ConsoleManager consoleManager;
    String name;
    String password;
    @Getter
    int id;
    @NonNull
    SQLCommands sql;
    Scanner scan = new Scanner(System.in);
    public boolean logInFunc() {
        consoleManager.yourNick();
        this.name = scan.nextLine();
        consoleManager.yourPassword();
        this.password = scan.nextLine();
        this.id = sql.takeID(name);
        return sql.logIn(name, password);
    }
    public void regist() {
        consoleManager.yourNick();
        this.name = scan.nextLine();
        consoleManager.yourPassword();
        this.password = scan.nextLine();
        sql.regist(name, password);
        this.id = sql.takeID(name);
    }

    public void additionalInfo() {
        String city;
        String country;
        consoleManager.yourCity();
        city = scan.nextLine();
        System.out.println("Your country : ");
        country = scan.nextLine();
        sql.additionalInfo(city, country);
    }
    public void showProfile() {
        sql.showProfile(id);
        if (!sql.checkAdditionalInfo(id)) {
            consoleManager.addAdditionalInfo();
            int i = scan.nextInt();
            if (i == 1) {
                additionalInfo();
            }
        }
    }
}
