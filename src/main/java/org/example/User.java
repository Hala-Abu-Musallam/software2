
package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;



public class User {

    public  Map< String, String > users = new HashMap<>();
    public String  username;

    public static boolean loginFlag;
    public static int user_type; //1 admin , 2 user , 3 service provider

    public HashMap<String,User>userDatabase = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(User.class);


    public User() {
        getUsersFromFile();

    }


    public boolean login(String username, String password) {
        if (users.containsKey(username) && password.equals(users.get(username))) {
            this.username = username;
            if (username.contains("@admin.com")) {
                user_type = 1;
                logger.info("Welcome Admin :)");
            } else if (username.contains("@user.com")) {
                user_type = 2;
                logger.info("Welcome User :)");
            } else if (username.contains("@serviceprovider.com")) {
                user_type = 3;
                logger.info("Welcome Service Provider :)");
            }
            loginFlag = true;
        } else {
            logger.info("Check your username or password.");
            loginFlag = false;
        }
        return loginFlag;
    }


    public void adduser (String email, String password){
        if ( users.containsKey ( email ) ){
            loginFlag = false;
            return ;
        }

        if ( email.contains ( "@admin" ) ){
            user_type = 1;
            writeUsers (  email , password , users );
            loginFlag = true;}
        else if (email.contains("@user")){
            user_type = 2 ;
            writeUsers (  email , password , users );
            loginFlag = true;}
        else if (email.contains("@serviceprovider")){
            user_type = 3 ;
            writeUsers (  email , password , users );
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
            logger.info(e.getMessage());
        }
    }

    public void writeUsers ( String username , String password , Map < String, String > users ) {
        try {
            users.clear ( );
            File file = new File ( "src/users.txt" );
            try (BufferedWriter bufferedWriter = new BufferedWriter ( new FileWriter ( file , true ) )) {
                String nameAndPass = "\n"+ username + "," + password;
                bufferedWriter.write ( nameAndPass+ "\n" );

            }
            getUsersFromFile ();
        }
        catch ( IOException e ) {
            logger.info(e.getMessage());
        }
    }



}
