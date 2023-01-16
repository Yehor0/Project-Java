package org.example;

import lombok.Getter;
import org.example.DB.SQLCommands;
import org.example.Share.Shares;

import java.util.Scanner;

@Getter
public class Initializer {
    private UserObj user;
    private SQLCommands sql;
    private ConsoleManager consoleManager;
    private Shares shares;
    private SwitchCaseMain SwitchCaseMain;
//    public UserObj user() {
//        user = new UserObj(consoleManager, sql);
//        return user;
//    }
//    public ConsoleManager consoleManager() {
//        consoleManager = new ConsoleManager();
//        return consoleManager;
//    }
//    public SQLCommands sql() {
//        sql = new SQLCommands(consoleManager);
//        return sql;
//    }
//    public Shares shares(){
//        shares = new Shares(sql, consoleManager);
//        return shares;
//    }
//    public SwitchCaseMain caseWork() {
//        caseWork = new SwitchCaseMain(shares , user , consoleManager , new Scanner(System.in));
//        return caseWork;
   // }
{
        consoleManager = new ConsoleManager();
        sql = new SQLCommands(consoleManager);
        user = new UserObj(consoleManager, sql);
        shares = new Shares(sql, consoleManager);
        SwitchCaseMain = new SwitchCaseMain(shares , user , consoleManager , new Scanner(System.in));
    }
}
