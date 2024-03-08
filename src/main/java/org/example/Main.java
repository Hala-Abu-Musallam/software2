package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        User user = new User();
while(true) {
    System.out.println("choose 1 for login , 2 for sign up:");
    int userinput = scanner.nextInt();
    if (userinput == 1) {
        System.out.println("enter username");

        String username = scanner.next();
        System.out.println("enter password");
        String password = scanner.next();
        user.login(username, password);
    } else {
        System.out.println("enter username");
        String username = scanner.next();
        System.out.println("enter password");
        String password = scanner.next();
        user.login(password, username);
    }
}

    }


}