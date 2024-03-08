package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class User {

    public  Map< String, String > users = new HashMap<>();
    public String  username;
    public String  password;
    public static boolean loginFlag;
    public static int user_type; //1 admin , 2 user , 3 service provider
    public String email;

    public HashMap<String,User>userDatabase = new HashMap<>();

    public User() {
        getUsersFromFile();

    }


    public void login(String username, String password) {
        if(password.equals(users.get(username))){
            if(username.contains("@admin.com")) {
                user_type = 1;
                System.out.println("welcome Admin :)");
            }

            else if (username.contains("@user.com")){
                user_type = 2;
                System.out.println("welcome User :)");
            }
            else if(username.contains("@serviceprovider.com")){
                user_type = 3;
                System.out.println("welcome service provider :)");

            }
            else{
                user_type=-1;
                System.out.println("check your username  :(");
            }

        }
        else{
            user_type=-1;
            System.out.println("check your username or password:(");}
    }


    public void adduser (String password, String email){
    if ( users.containsKey ( email ) ){
        loginFlag = false;
        return ;
    }



    if ( email.contains ( "@admin" ) ){
            user_type = 1;
        users.put(email,password);
        loginFlag = true;}
    else if (email.contains("@user")){
            user_type = 2 ;
        users.put(email,password);
        loginFlag = true;}
    else if (email.contains("@serviceprovider")){
            user_type = 3 ;
        users.put(email,password);
        loginFlag = true;}
    else{
        user_type=-1;
    loginFlag=false;
    }
    }

    void getUsersFromFile ( ) {
        try {
            users.clear ( );
            File file = new File ( "src/users.txt" );
            try (BufferedReader bufferedReader = new BufferedReader ( new FileReader( file ) )) {
                String nameAndPass;
                while ( (nameAndPass = bufferedReader.readLine ( )) != null ) {
                    String[] data = nameAndPass.split ( "," );//يقسم السترينج من عند الفاصلة
                    users.put ( data[ 0 ] , data[ 1 ] );
                }
            }
        }
        catch ( IOException e ) {
            System.out.println(e.getMessage());
        }
    }

    }




