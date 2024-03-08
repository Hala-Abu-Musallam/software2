package org.example;

import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServiceProvider serviceProvider = new ServiceProvider();

        // Perform login for the service provider
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        serviceProvider.login(username, password);
        boolean loggedIn = serviceProvider.isLoggedIn();

        if (loggedIn) {
            System.out.println("Logged in as Service Provider.");
            while (true) {

                System.out.println("Choose an option:");
                System.out.println("1. Create Event");
                System.out.println("2. Edit Event");
                System.out.println("3. Delete Event");
                System.out.println("4. Display All Events");
                System.out.println("5. Exit");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        serviceProvider.promptForEventDetails();
                        serviceProvider.saveEventDetails(serviceProvider.getEvent().getName(),
                                serviceProvider.getEvent().getDate(),
                                serviceProvider.getEvent().getLocation(),
                                serviceProvider.getEvent().getServices());
                        break;
                    case 2:
                        System.out.println("Enter the name of the event to edit:");
                        scanner.nextLine();
                        String eventNameToEdit = scanner.nextLine();
                        serviceProvider.editEvent(eventNameToEdit);
                        break;
                    case 3:
                        System.out.println("Enter the name of the event to delete:");
                        scanner.nextLine();
                        String eventNameToDelete = scanner.nextLine();
                        serviceProvider.deleteEvent(eventNameToDelete);
                        break;
                    case 4:
                        serviceProvider.displayAllEvents();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }
}



