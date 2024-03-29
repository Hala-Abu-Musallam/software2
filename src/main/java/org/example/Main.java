package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        ServiceProvider serviceProvider = new ServiceProvider();

        while (true) {

            System.out.println(" Please choose your role: 1-admin 2-user 3-service provider");
            int role = scanner.nextInt();
            scanner.nextLine();

            switch (role) {
                case 1:
                    System.out.println("Admin actions here.");
                    break;
                case 2:
                    performAuthentication( scanner,  user);
                    displayVenueNames();
                    addEventMenu(scanner);
                    break;
                case 3:
                    performAuthentication( scanner,  user);
                    serviceManagementMenu(scanner, serviceProvider);
                    break;
            }

            if (!User.loginFlag) {
                System.out.println("Returning to login...");
            }
        }
    }
    private static void serviceManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
        boolean running = true;

        while (running) {
            System.out.println("Select a management option:");
            System.out.println("1. Vendor Management");
            System.out.println("2. Venue Management");
            System.out.println("3. Event Management");
            System.out.println("4. Log out");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice) {
                case 1:
                    vendorManagementMenu(scanner, serviceProvider);
                    break;
                case 2:
                    venueManagementMenu(scanner, serviceProvider);
                    break;
                case 3:
                    eventManagementMenu(scanner, serviceProvider);
                    break;
                case 4:
                    User.loginFlag = false;
                    running = false;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

        private static void vendorManagementMenu (Scanner scanner, ServiceProvider serviceProvider){
            boolean inVendorMenu = true;
            while (inVendorMenu) {
                System.out.println("\nVendor Management:");
                System.out.println("1. Add Vendor");
                System.out.println("2. Edit Vendor");
                System.out.println("3. Delete Vendor");
                System.out.println("4. Display Vendors");
                System.out.println("5. Return to Main Menu");
                System.out.print("Select an action: ");
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 1:
                        System.out.print("Enter Vendor Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Service Type: ");
                        String serviceType = scanner.nextLine();
                        System.out.print("Enter Pricing: ");
                        double pricing = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Time: ");
                        int time = scanner.nextInt();
                        System.out.print("Enter Date: ");
                        int date = scanner.nextInt();
                        scanner.nextLine();

                        Vendor vendor = new Vendor(name, serviceType, email, time, date );

                        serviceProvider.addVendor(vendor);
                        serviceProvider.saveVendorsToFile();
                        System.out.println("Vendor added successfully.");
                        break;
                    case 2:
                        System.out.print("Enter the name of the vendor you wish to edit: ");
                        String editName = scanner.nextLine();
                        System.out.println("Enter new Service Type:");
                        String newServiceType = scanner.nextLine();
                        System.out.println("Enter new Email:");
                        String newEmail = scanner.nextLine();
                        System.out.println("Enter new Time:");
                        int newTime = scanner.nextInt();
                        System.out.println("Enter new Date:");
                        int newDate = scanner.nextInt();
                        scanner.nextLine();
                        Vendor updatedVendor = new Vendor(editName, newServiceType, newEmail, newTime, newDate);

                        serviceProvider.updateVendor(updatedVendor);
                        serviceProvider.saveVendorsToFile();
                        System.out.println("Vendor updated successfully.");
                        break;
                    case 3:
                        System.out.print("Enter the name of the vendor you wish to delete: ");
                        String deleteName = scanner.nextLine();
                        serviceProvider.deleteVendor(deleteName);
                        serviceProvider.saveVendorsToFile();
                        System.out.println("Vendor deleted successfully.");
                        break;
                    case 4:
                        serviceProvider.loadVendorsFromFile();
                        System.out.println("Vendors:");
                        for (Vendor v : serviceProvider.getAllVendors()) {
                            System.out.println(v);
                        }
                        break;
                    case 5:
                        inVendorMenu = false;
                        break;
                    default:
                        System.out.println("Invalid action. Please select a valid option.");
                        break;
                }
            }
        }


        private static void venueManagementMenu (Scanner scanner, ServiceProvider serviceProvider){
            boolean inVenueMenu = true;
            while (inVenueMenu) {
                System.out.println("Venue Management:");
                System.out.println("1. Add Venue");
                System.out.println("2. Edit Venue");
                System.out.println("3. Delete Venue");
                System.out.println("4. Display Venues");
                System.out.println("5. Return to Main Menu");
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 1:
                        System.out.println("Enter Venue Name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter Owner Name:");
                        String ownerName = scanner.nextLine();
                        System.out.println("Enter Location:");
                        String location = scanner.nextLine();
                        System.out.println("Enter Capacity:");
                        int capacity = scanner.nextInt();
                        System.out.println("Enter Pricing:");
                        double pricing = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        Venue venue = new Venue(name, ownerName, location, capacity, pricing);
                        serviceProvider.addVenue(venue);
                        serviceProvider.saveVenuesToFile();
                        System.out.println("Venue added successfully.");
                        break;
                    case 2:
                        System.out.println("Enter the name of the venue you wish to edit:");
                        String editVenueName = scanner.nextLine();
                        Venue venueToEdit = serviceProvider.findVenueByName(editVenueName);
                        if (venueToEdit != null) {
                            System.out.println("Editing Venue: " + venueToEdit.getName());
                            System.out.println("Enter new owner name (or press Enter to skip):");
                            String newOwnerName = scanner.nextLine();
                            if (!newOwnerName.isEmpty()) {
                                venueToEdit.setOwnerName(newOwnerName);
                            }

                            System.out.println("Enter new location (or press Enter to skip):");
                            String newLocation = scanner.nextLine();
                            if (!newLocation.isEmpty()) {
                                venueToEdit.setLocation(newLocation);
                            }

                            System.out.println("Enter new capacity (or enter 0 to skip):");
                            int newCapacity = scanner.nextInt();
                            if (newCapacity > 0) {
                                venueToEdit.setCapacity(newCapacity);
                            }
                            scanner.nextLine();

                            System.out.println("Enter new pricing (or enter -1 to skip):");
                            double newPricing = scanner.nextDouble();
                            scanner.nextLine();
                            if (newPricing >= 0) {
                                venueToEdit.setPricing(newPricing);
                            }


                            if (serviceProvider.updateVenue(editVenueName, venueToEdit)) {
                                System.out.println("Venue updated successfully.");
                                serviceProvider.saveVenuesToFile();
                            } else {
                                System.out.println("Failed to update venue.");
                            }
                        } else {
                            System.out.println("Venue not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Enter Venue Name to Delete:");
                        String venueNameToDelete = scanner.nextLine();
                        Venue venueToDelete = serviceProvider.findVenueByName(venueNameToDelete);
                        if (venueToDelete != null && serviceProvider.deleteVenue(venueToDelete)) {
                            System.out.println("Venue deleted successfully.");
                        } else {
                            System.out.println("Venue not found or could not be deleted.");
                        }
                        break;
                    case 4:
                        serviceProvider.displayVenuesFromFile();
                        break;
                    case 5:
                        inVenueMenu = false;
                        break;
                    default:
                        System.out.println("Invalid action. Please select a valid option.");
                        break;
                }
            }
        }

    private static void addEventMenu(Scanner scanner) {
        boolean continueAdding = true;

        while (continueAdding) {
            System.out.println("Enter Name:");
            String name = scanner.nextLine();
            System.out.println("Choose Venue:");
            String venueName = scanner.nextLine();
            System.out.println("Enter Date (dd/MM/yyyy):");
            String date = scanner.nextLine();
            System.out.println("Enter Time (HH:mm):");
            String time = scanner.nextLine();
            System.out.println("Enter Price:");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Vendor Name:");
            String vendorName = scanner.nextLine();


            Event event = new Event(name, date, time, price, vendorName);
            saveEventToWaitList(event);
            System.out.println("Event added successfully.");


            System.out.println("What would you like to do next?");
            System.out.println("1. Add another event");
            System.out.println("2. Log out");

            System.out.println("Please enter your choice:");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:

                    break;
                case 2:
                    User.loginFlag = false;
                    continueAdding = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");

                    continueAdding = false;
                    break;
            }
        }
    }

    private static void saveEventToWaitList(Event event) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/waitList.txt", true))) {
            bw.write(event.toString());
            bw.newLine();
            System.out.println("Event added to wait list successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to waitList.txt: " + e.getMessage());
        }
    }
    private static void eventManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
        boolean running = true;
        while (running) {
            System.out.println("\nEvent Management Menu:");
            System.out.println("1. Accept/Refuse Events from Waitlist");
            System.out.println("2. Edit Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Return to Main Menu");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    acceptOrRefuseEvents(scanner, serviceProvider);
                    break;
                case 2:
                    editEvent(scanner, serviceProvider);
                    break;
                case 3:
                    deleteEvent(scanner, serviceProvider);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please select a valid option.");
                    break;
            }
        }
    }

    private static void acceptOrRefuseEvents(Scanner scanner, ServiceProvider serviceProvider) {

        List<String> waitListEvents;
        try {
            waitListEvents = Files.readAllLines(Paths.get("src/waitList.txt"));
        } catch (IOException e) {
            System.out.println("Error reading waitList.txt: " + e.getMessage());
            return;
        }

        if (waitListEvents.isEmpty()) {
            System.out.println("No events in waitlist.");
            return;
        }


        Iterator<String> iterator = waitListEvents.iterator();
        while (iterator.hasNext()) {
            String event = iterator.next();
            System.out.println("Event: " + event);
            System.out.println("Do you want to accept this event? (yes/no)");
            String decision = scanner.nextLine().trim();

            if ("yes".equalsIgnoreCase(decision)) {

                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/events.txt"), StandardOpenOption.APPEND)) {
                    writer.write(event);
                    writer.newLine();
                    System.out.println("Event accepted and added to events.txt.");
                } catch (IOException e) {
                    System.out.println("Error writing to events.txt: " + e.getMessage());
                }


                iterator.remove();
            } else if ("no".equalsIgnoreCase(decision)) {

                System.out.println("Event refused.");
                ((Iterator<?>) iterator).remove();
            } else {
                System.out.println("Invalid response. Skipping...");
            }
        }


        try {
            Files.write(Paths.get("src/waitList.txt"), waitListEvents);
        } catch (IOException e) {
            System.out.println("Error updating waitList.txt: " + e.getMessage());
        }
    }

    private static void editEvent(Scanner scanner, ServiceProvider serviceProvider) {
        List<String> events;
        try {
            events = Files.readAllLines(Paths.get("src/events.txt"));
        } catch (IOException e) {
            System.out.println("Error reading events.txt: " + e.getMessage());
            return;
        }

        if (events.isEmpty()) {
            System.out.println("No events to edit.");
            return;
        }

        System.out.println("Select an event to edit:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ": " + events.get(i));
        }

        int eventChoice = scanner.nextInt();
        scanner.nextLine();

        if (eventChoice < 1 || eventChoice > events.size()) {
            System.out.println("Invalid event selection.");
            return;
        }

        String selectedEventStr = events.get(eventChoice - 1);
        System.out.println("Editing event: " + selectedEventStr);


        System.out.println("Enter new date (dd/MM/yyyy):");
        String newDate = scanner.nextLine();
        System.out.println("Enter new time (HH:mm):");
        String newTime = scanner.nextLine();


        String[] parts = selectedEventStr.split(",");
        if (parts.length >= 3) {
            String newName = parts[0];
            String newEventStr = newName + "," + newDate + "," + newTime;
            events.set(eventChoice - 1, newEventStr);
        }


        try {
            Files.write(Paths.get("src/events.txt"), events);
            System.out.println("Event updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating events.txt: " + e.getMessage());
        }
    }
    private static void deleteEvent(Scanner scanner, ServiceProvider serviceProvider) {
        List<String> events;
        try {
            events = Files.readAllLines(Paths.get("src/events.txt"));
        } catch (IOException e) {
            System.out.println("Error reading events.txt: " + e.getMessage());
            return;
        }

        if (events.isEmpty()) {
            System.out.println("No events to delete.");
            return;
        }

        System.out.println("Select an event to delete:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ": " + events.get(i));
        }

        int eventChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (eventChoice < 1 || eventChoice > events.size()) {
            System.out.println("Invalid event selection.");
            return;
        }


        events.remove(eventChoice - 1);


        try {
            Files.write(Paths.get("src/events.txt"), events);
            System.out.println("Event deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error updating events.txt: " + e.getMessage());
        }
    }



    public void saveEventDetailsToFile(String event) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/events.txt", true))) {
            bw.write(event);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to events.txt: " + e.getMessage());
        }
    }
    public static void displayVenueNames() {
        Path path = Paths.get("src/Venues.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            System.out.println("Available Venues:");
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String venueName = parts[0];
                    String pricing = parts[parts.length - 1];
                    System.out.println("- venueName: " + venueName + ", - Pricing: " + pricing);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read Venues.txt: " + e.getMessage());
        }
    }

    private static boolean performAuthentication(Scanner scanner, User user) {
        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            System.out.println("Choose 1 for login, 2 for sign up:");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                user.login(username, password);
            } else if (userInput == 2) {
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                user.adduser(email, password);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

            isAuthenticated = User.loginFlag;

            if (!isAuthenticated) {
                System.out.println("Authentication failed. Please try again.");
            }
        }
        return isAuthenticated;
    }


}



