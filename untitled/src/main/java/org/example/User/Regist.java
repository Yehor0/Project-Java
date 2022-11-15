package org.example.User;

import org.example.DB.SQLCommands;

import java.util.Scanner;

public class Regist {
    private int ID;
    private String name;
    private String password;
    private SQLCommands sql = new SQLCommands();
    private Scanner scan = new Scanner(System.in);

    public void regist() {
        System.out.println("Write your nickname : ");
        this.name = scan.nextLine();
        System.out.println("Write your password : ");
        this.password = scan.nextLine();
        sql.regist(name, password);
        this.ID = sql.takeID(name);
    }

    public void additionalInfo() {
        String city;
        String country;
        System.out.println("Your city : ");
        city = scan.nextLine();
        System.out.println("Your country : ");
        country = scan.nextLine();
        sql.additionalInfo(city, country);
    }
    public int getID() {
        return ID;
    }
}
