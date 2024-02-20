package org.example;

public class User {
    public void login(String username, String password) {
        if(username.equals("Hala")&&password.equals("1234hala"))
            login_flag=true;
        else
            login_flag=false;
    }
    public static boolean login_flag;

}
