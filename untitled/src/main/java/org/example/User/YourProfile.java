package org.example.User;

import org.example.DB.SQLCommands;

public class YourProfile {
    SQLCommands sql = new SQLCommands();
    public void ShowProfile(int ID) {
       sql.showProfile(ID);
    }
}
