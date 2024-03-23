package org.example;

import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        ServiceProvider serviceProvider = new ServiceProvider();

        while (true) {
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
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();
                user.adduser(username, password);
            }


            System.out.println("Enter your role (1-Admin, 2-User, 3-Service Provider):");
            int role = scanner.nextInt();
            scanner.nextLine();

            switch (role) {
                case 1:
                    System.out.println("Admin actions here.");
                    break;
                case 2:
                    System.out.println("User actions here.");
                    break;
                case 3:
                    boolean running = true;

                    while (running) {
                        System.out.println("Select a management option:");
                        System.out.println("1. Vendor Management");
                        System.out.println("2. Venue Management");
                        System.out.println("3. log out");
                        int mainChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (mainChoice) {
                            case 1: // Vendor Management
                                vendorManagementMenu(scanner, serviceProvider);
                                break;
                            case 2: // Venue Management
                                venueManagementMenu(scanner, serviceProvider);
                                break;
                            case 3: // log out
                                running = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                                break;
                        }
                    }



            }
        }


    }

    private static void vendorManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
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

                    Vendor vendor = new Vendor(name, serviceType, pricing);
                    serviceProvider.addVendor(vendor);
                    serviceProvider.saveVendorsToFile();
                    System.out.println("Vendor added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the name of the vendor you wish to edit: ");
                    String editName = scanner.nextLine();
                    System.out.println("Enter new Service Type:");
                    String newServiceType = scanner.nextLine();
                    System.out.println("Enter new Pricing:");
                    double newPricing = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Vendor updatedVendor = new Vendor(editName, newServiceType, newPricing);
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


    private static void venueManagementMenu(Scanner scanner, ServiceProvider serviceProvider) {
        boolean inVenueMenu = true;
        while (inVenueMenu) {
            System.out.println("Venue Management:");
            System.out.println("1. Add Venue");
            System.out.println("2. Edit Venue");
            System.out.println("3. Delete Venue");
            System.out.println("4. Display Venues");
            System.out.println("5. Return to Main Menu");
            int action = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (action) {
                case 1: // Add Venue
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


}
