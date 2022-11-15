package org.example.User;

import org.example.DB.SQLCommands;

import java.util.Scanner;

public class LogIn {
    private Scanner scan = new Scanner(System.in);
    private SQLCommands sql = new SQLCommands();
    private String name;
    private String password;
    private int ID;

    public boolean logInFunc() {
        System.out.println("Your nickname : ");
        this.name = scan.nextLine();
        System.out.println("Your password : ");
        this.password = scan.nextLine();
        this.ID = sql.takeID(name);
        return sql.logIn(name, password);
    }
    public int getID() {
        return ID;
    }
}