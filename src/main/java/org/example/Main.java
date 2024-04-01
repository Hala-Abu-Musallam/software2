package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static org.example.VendorsByUser.date;
import static org.example.VendorsByUser.time;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String EVENTS_FILE_PATH = "src/events.txt"; // Define a constant for the events file path
    private static final String WAITLIST_FILE_PATH = "src/waitList.txt";
    public static boolean flag=true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        ServiceProvider serviceProvider = new ServiceProvider();
        boolean isRunning = true;

        while (isRunning) {
            flag=true;
            logger.info("\nPlease choose your role: 1-admin 2-user 3-service provider");
            int role = scanner.nextInt();
            scanner.nextLine();

            switch (role) {
                case 1:
                    logger.info("Admin actions here.");

                    break;
                case 2:
                    performAuthentication( scanner,  user);
                    displayFile("src/Venues.txt");
                    logger.info("Here is the venues list please choose one by its name:");
                    String venue = scanner.nextLine();

                    chooseEvent(scanner, user);


                    while (flag) {
                        logger.info("Displaying vendors list:");
                        displayFile("src/vendor.txt");
                        logger.info("Please choose a vendor by typing its name:");
                        String selectedVendor = scanner.nextLine();
                        logger.info("Please enter a vendor by typing its email");
                        String selectedVendorEmail = scanner.nextLine();


                        VendorsByUser vendorByUser = new VendorsByUser();
                        vendorByUser.addVendor(user.username, selectedVendor, date, time, selectedVendorEmail);

                        logger.info("Vendor added to the wait list successfully!");
                        logger.info("Do you want to select another one? (yes/no)");
                        String chooseAnother = scanner.nextLine();
                        if (!chooseAnother.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }

                    break;

                case 3:
                    performAuthentication( scanner,  user);
                    serviceManagementMenu(scanner, serviceProvider);
                    break;
                case 4:
                    isRunning = false;
                    logger.info("Exiting program.");
                    break;
                default:
                    logger.info("Invalid role selected. Please try again.");
                    break;
            }


            if (!User.loginFlag) {
                logger.info("Returning to login...");
            }
        }
    }

    private static void chooseEvent(Scanner scanner, User user) {
        while(true) {
            logger.info("please enter the date to check if it's available:");
            String date = scanner.nextLine();
            logger.info("please enter the time to check if it's available:");
            String time = scanner.nextLine();
            CheckEvent checker = new CheckEvent();

            checker.checkEvent(date, time, user.username);
            if (CheckEvent.addSuccess) {
                logger.info("Event has been successfully added to wait list!");

            } else if (!CheckEvent.addSuccess) {
                logger.info("Failed to add event. Would you like to try another date and time? (yes/no)");
                String tryAgain = scanner.nextLine();
                if (!tryAgain.equalsIgnoreCase("yes"))
                   flag=false;
                    return;
            }
        }
    }


    private static void displayFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (IOException e) {
            logger.error("There is an error happened while displaying list.", e);
        }
    }




    private static void serviceManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
        boolean running = true;

        while (running) {
            logger.info("Select a management option:");
            logger.info("1. Vendor Management");
            logger.info("2. Venue Management");
            logger.info("3. Event Management");
            logger.info("4. Log out");
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
                    eventManagementMenu(scanner);
                    break;
                case 4:
                    User.loginFlag = false;
                    running = false;
                    logger.info("Logged out successfully.");
                    return;
                default:
                    logger.info("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }


    private static void vendorManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
        boolean inVendorMenu = true;
        while (inVendorMenu) {
            logger.info("\nVendor Management:");
            logger.info("1. Add Vendor");
            logger.info("2. Edit Vendor");
            logger.info("3. Delete Vendor");
            logger.info("4. Display Vendors");
            logger.info("5. Return to Main Menu");
            logger.info("Select an action: "); // Note: This will not pause the program for input.
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    logger.info("Enter Vendor Name: ");
                    String name = scanner.nextLine();
                    logger.info("Enter Service Type: ");
                    String serviceType = scanner.nextLine();

                    scanner.nextLine();
                    logger.info("Enter Email: ");
                    String email = scanner.nextLine();
                    logger.info("Enter Time: ");
                    int time = scanner.nextInt();
                    logger.info("Enter Date: ");
                    int date = scanner.nextInt();
                    scanner.nextLine();

                    Vendor vendor = new Vendor(name, serviceType, email, time, date);

                    serviceProvider.addVendor(vendor);
                    serviceProvider.saveVendorsToFile();
                    logger.info("Vendor added successfully.");
                    break;
                case 2:
                    logger.info("Enter the name of the vendor you wish to edit: ");
                    String editName = scanner.nextLine();
                    logger.info("Enter new Service Type:");
                    String newServiceType = scanner.nextLine();
                    logger.info("Enter new Email:");
                    String newEmail = scanner.nextLine();
                    logger.info("Enter new Time:");
                    int newTime = scanner.nextInt();
                    logger.info("Enter new Date:");
                    int newDate = scanner.nextInt();
                    scanner.nextLine();
                    Vendor updatedVendor = new Vendor(editName, newServiceType, newEmail, newTime, newDate);

                    serviceProvider.updateVendor(updatedVendor);
                    serviceProvider.saveVendorsToFile();
                    logger.info("Vendor updated successfully.");
                    break;
                case 3:
                    logger.info("Enter the name of the vendor you wish to delete: ");
                    String deleteName = scanner.nextLine();
                    serviceProvider.deleteVendor(deleteName);
                    serviceProvider.saveVendorsToFile();
                    logger.info("Vendor deleted successfully.");
                    break;
                case 4:
                    serviceProvider.loadVendorsFromFile();
                    logger.info("Vendors:");
                    for (Vendor v : serviceProvider.getAllVendors()) {
                        logger.info(v.toString()); // Assuming the Vendor class has a meaningful toString implementation.
                    }
                    break;
                case 5:
                    inVendorMenu = false;
                    break;
                default:
                    logger.info("Invalid action. Please select a valid option.");
                    break;
            }
        }
    }


    private static void venueManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
        boolean inVenueMenu = true;
        while (inVenueMenu) {
            logger.info("Venue Management:");
            logger.info("1. Add Venue");
            logger.info("2. Edit Venue");
            logger.info("3. Delete Venue");
            logger.info("4. Display Venues");
            logger.info("5. Return to Main Menu");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    logger.info("Enter Venue Name:");
                    String name = scanner.nextLine();
                    logger.info("Enter Owner Name:");
                    String ownerName = scanner.nextLine();
                    logger.info("Enter Location:");
                    String location = scanner.nextLine();
                    logger.info("Enter Capacity:");
                    int capacity = scanner.nextInt();
                    logger.info("Enter Pricing:");
                    double pricing = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Venue venue = new Venue(name, ownerName, location, capacity, pricing);
                    serviceProvider.addVenue(venue);
                    serviceProvider.saveVenuesToFile();
                    logger.info("Venue added successfully.");
                    break;
                case 2:
                    logger.info("Enter the name of the venue you wish to edit:");
                    String editVenueName = scanner.nextLine();
                    Venue venueToEdit = serviceProvider.findVenueByName(editVenueName);
                    if (venueToEdit != null) {
                        logger.info("Editing Venue: {}", venueToEdit.getName());
                        logger.info("Enter new owner name (or press Enter to skip):");
                        String newOwnerName = scanner.nextLine();
                        if (!newOwnerName.isEmpty()) {
                            venueToEdit.setOwnerName(newOwnerName);
                        }

                        logger.info("Enter new location (or press Enter to skip):");
                        String newLocation = scanner.nextLine();
                        if (!newLocation.isEmpty()) {
                            venueToEdit.setLocation(newLocation);
                        }

                        logger.info("Enter new capacity (or enter 0 to skip):");
                        int newCapacity = scanner.nextInt();
                        if (newCapacity > 0) {
                            venueToEdit.setCapacity(newCapacity);
                        }
                        scanner.nextLine();

                        logger.info("Enter new pricing (or enter -1 to skip):");
                        double newPricing = scanner.nextDouble();
                        scanner.nextLine();
                        if (newPricing >= 0) {
                            venueToEdit.setPricing(newPricing);
                        }

                        if (serviceProvider.updateVenue(editVenueName, venueToEdit)) {
                            logger.info("Venue updated successfully.");
                            serviceProvider.saveVenuesToFile();
                        } else {
                            logger.info("Failed to update venue.");
                        }
                    } else {
                        logger.info("Venue not found.");
                    }
                    break;

                case 3:
                    logger.info("Enter Venue Name to Delete:");
                    String venueNameToDelete = scanner.nextLine();
                    Venue venueToDelete = serviceProvider.findVenueByName(venueNameToDelete);
                    if (venueToDelete != null && serviceProvider.deleteVenue(venueToDelete)) {
                        logger.info("Venue deleted successfully.");
                    } else {
                        logger.info("Venue not found or could not be deleted.");
                    }
                    break;
                case 4:
                    serviceProvider.displayVenuesFromFile();
                    break;
                case 5:
                    inVenueMenu = false;
                    break;
                default:
                    logger.info("Invalid action. Please select a valid option.");
                    break;
            }
        }
    }


    private static void eventManagementMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            logger.info("\nEvent Management Menu:");
            logger.info("1. Accept/Refuse Events from Waitlist");
            logger.info("2. Edit Event");
            logger.info("3. Delete Event");
            logger.info("4. Return to Main Menu");
            logger.info("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    acceptOrRefuseEvents(scanner);
                    break;
                case 2:
                    editEvent(scanner);
                    break;
                case 3:
                    deleteEvent(scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    logger.info("Invalid selection. Please select a valid option.");
                    break;
            }
        }
    }


    private static void acceptOrRefuseEvents(Scanner scanner) {
        List<String> waitListEvents;
        try {
            waitListEvents = Files.readAllLines(Paths.get(WAITLIST_FILE_PATH));
        } catch (IOException e) {
            logger.error("Error reading waitList.txt: {}", e.getMessage());
            return;
        }

        if (waitListEvents.isEmpty()) {
            logger.info("No events in waitlist.");
            return;
        }

        Iterator<String> iterator = waitListEvents.iterator();
        while (iterator.hasNext()) {
            String event = iterator.next();
            logger.info("Event: {}", event);
            logger.info("Do you want to accept this event? (yes/no)");
            String decision = scanner.nextLine().trim();

            if ("yes".equalsIgnoreCase(decision)) {
                try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(EVENTS_FILE_PATH), StandardOpenOption.APPEND)) {
                    writer.write(event);
                    writer.newLine();
                    logger.info("Event accepted and added to events.txt.");
                } catch (IOException e) {
                    logger.error("Error writing to events.txt: {}", e.getMessage());
                }
                iterator.remove();
            } else if ("no".equalsIgnoreCase(decision)) {
                logger.info("Event refused.");
                iterator.remove();
            } else {
                logger.info("Invalid response. Skipping...");
            }
        }

        try {
            Files.write(Paths.get(WAITLIST_FILE_PATH), waitListEvents);
        } catch (IOException e) {
            logger.error("Error updating waitList.txt: {}", e.getMessage());
        }
    }


    private static void editEvent(Scanner scanner) {
        List<String> events;
        try {
            events = Files.readAllLines(Paths.get(EVENTS_FILE_PATH));
        } catch (IOException e) {
            logger.error("Error reading events.txt: {}", e.getMessage());
            return;
        }

        if (events.isEmpty()) {
            logger.info("No events to edit.");
            return;
        }

        logger.info("Select an event to edit:");
        for (int i = 0; i < events.size(); i++) {
            logger.info("{}: {}", (i + 1), events.get(i));
        }

        int eventChoice = scanner.nextInt();
        scanner.nextLine();

        if (eventChoice < 1 || eventChoice > events.size()) {
            logger.info("Invalid event selection.");
            return;
        }

        String selectedEventStr = events.get(eventChoice - 1);
        logger.info("Editing event: {}", selectedEventStr);

        logger.info("Enter new date (dd/MM/yyyy):");
        String newDate = scanner.nextLine();
        logger.info("Enter new time (HH:mm):");
        String newTime = scanner.nextLine();

        // Assuming the format of the event string is consistent and includes at least three parts separated by commas
        String[] parts = selectedEventStr.split(",");
        if (parts.length >= 3) {
            String newName = parts[0]; // Assuming the first part is the name
            String newEventStr = newName + "," + newDate + "," + newTime;
            events.set(eventChoice - 1, newEventStr);
        }

        try {
            Files.write(Paths.get(EVENTS_FILE_PATH), events);
            logger.info("Event updated successfully.");
        } catch (IOException e) {
            logger.error("Error updating events.txt: {}", e.getMessage());
        }
    }

    private static void deleteEvent(Scanner scanner) {
        List<String> events;
        try {
            events = Files.readAllLines(Paths.get(EVENTS_FILE_PATH));
        } catch (IOException e) {
            logger.error("Error reading events.txt: {}", e.getMessage());
            return;
        }

        if (events.isEmpty()) {
            logger.info("No events to delete.");
            return;
        }

        logger.info("Select an event to delete:");
        for (int i = 0; i < events.size(); i++) {
            logger.info("{}: {}", (i + 1), events.get(i));
        }

        int eventChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        if (eventChoice < 1 || eventChoice > events.size()) {
            logger.info("Invalid event selection.");
            return;
        }

        events.remove(eventChoice - 1);

        try {
            Files.write(Paths.get(EVENTS_FILE_PATH), events);
            logger.info("Event deleted successfully.");
        } catch (IOException e) {
            logger.error("Error updating events.txt: {}", e.getMessage());
        }
    }

    private static boolean performAuthentication(Scanner scanner, User user) {
        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            logger.info("Choose 1 for login, 2 for sign up:");
            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput == 1) {
                logger.info("Enter username:");
                String username = scanner.nextLine();
                logger.info("Enter password:");
                String password = scanner.nextLine();
                user.login(username, password);
            } else if (userInput == 2) {
                logger.info("Enter email:");
                String email = scanner.nextLine();
                logger.info("Enter password:");
                String password = scanner.nextLine();
                user.adduser(email, password);
            } else {
                logger.info("Invalid choice. Please try again.");
            }
            isAuthenticated = User.loginFlag;
            if (isAuthenticated) {
                logger.info("Authentication successful.");
            }
        }
        return isAuthenticated;
    }

}