package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceProvider {
    private Map<String, Vendor> vendors = new HashMap<>();
    public static final String VENDORS_FILE_PATH = "src/vendor.txt";

    public static final String VENUES_FILE_PATH = "src/Venues.txt";
    private static final Map<String, Event> savedEvents = new HashMap<>();

    private String username;
    private String password;
    private boolean isLoggedIn;
    private boolean choosingToCreateVenue;
    private Event event;


    private boolean choosingToCreateEvent;
    private List<Venue> venues;

    public ServiceProvider() {
        this.venues = new ArrayList<>();
    }

    public void login(String username, String password) {
        this.username = username;
        this.password = password;
        isLoggedIn = true;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void chooseToCreateEvent() {
        choosingToCreateEvent = true;
    }

    public boolean isChoosingToCreateEvent() {
        return choosingToCreateEvent;
    }

    public void promptForEventDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter event details:");
        System.out.print("Event Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Date: ");
        String date = scanner.nextLine();
        System.out.print("Time: ");
        String time = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Vendor Name: ");
        String vendorName = scanner.nextLine();
        event = new Event(eventName, date, time, price, vendorName);
    }


    public void saveEventDetails(String eventName, String date, String time, double price, String vendorName) {
        Event event = new Event(eventName, date, time, price, vendorName);
        savedEvents.put(eventName, event);
        System.out.println("Event saved successfully!");
    }

    public Event getEvent() {
        return event;
    }


    public void editEvent(String eventName){
        System.out.println("Keys in savedEvents: " + savedEvents.keySet());

        if (savedEvents.containsKey(eventName)) {
            event = savedEvents.get(eventName);
            System.out.println("Current event details:");
            System.out.println(event);

            System.out.println("Enter new event details:");
            promptForEventDetails();
            savedEvents.put(eventName, event);

            System.out.println("Event details updated successfully!");
        } else {
            System.out.println("Event not found!");
        }
    }


    public boolean deleteEvent(String eventName) {
        if (savedEvents.containsKey(eventName)) {
            savedEvents.remove(eventName);
            System.out.println("Event deleted successfully!");
            return true;
        } else {
            System.out.println("Event not found!");
            return false;
        }
    }

    public boolean modifyEventDetails(String eventName, String newDate,String newtime,double newprice, String newServices) {
        if (savedEvents.containsKey(eventName)) {
            Event eventToUpdate = savedEvents.get(eventName);
            eventToUpdate.setDate(newDate);
            eventToUpdate.setVendorName(newServices);
            savedEvents.put(eventName, eventToUpdate);
            return true;
        }
        return false;
    }


    public static Event getEventFromDatabase(String eventName) {
        return savedEvents.get(eventName);
    }

    public void displayAllEvents() {
        if (savedEvents.isEmpty()) {
            System.out.println("No events found.");
        } else {
            System.out.println("All Events:");
            for (Map.Entry<String, Event> entry : savedEvents.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }


    public void addVenue(Venue venue) {
        venues.add(venue);
        saveVenuesToFile();

    }

    public boolean deleteVenue(Venue venue) {
        boolean removed = venues.remove(venue);
        if (removed) {
            saveVenuesToFile();
        }
        return removed;
    }

    public Venue findVenueByName(String name) {
        return venues.stream()
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean updateVenue(String name,Venue updatedVenue) {
        for (int i = 0; i < venues.size(); i++) {
            if (venues.get(i).getName().equals(name)) {
                venues.set(i, updatedVenue);
                saveVenuesToFile();
                return true;
            }
        }
        return false;
    }

    public boolean containsVenue(Venue venue) {

        return venues.contains(venue);
    }
    public void displayVenuesFromFile() {
        try {
            Files.lines(Paths.get(VENUES_FILE_PATH)).forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error displaying venues from file: " + e.getMessage());
        }
    }

    public void loadVenuesFromFile() {
        try {
            venues.clear();
            Path path = Paths.get(VENUES_FILE_PATH);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] details = line.split(",");
                if (details.length == 5) {
                    Venue venue = new Venue(details[0], details[1], details[2],
                            Integer.parseInt(details[3]), Double.parseDouble(details[4]));
                    venues.add(venue);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading venues from file: " + e.getMessage());
        }
    }

    public void saveVenuesToFile() {
        try {
            Path path = Paths.get(VENUES_FILE_PATH);
            List<String> lines = venues.stream()
                    .map(v -> String.join(",", v.getName(), v.getOwnerName(), v.getLocation(),
                            String.valueOf(v.getCapacity()), String.valueOf(v.getPricing())))
                    .collect(Collectors.toList());
            Files.write(path, lines);
        } catch (IOException e) {
            System.err.println("Error saving venues to file: " + e.getMessage());
        }
    }

    public void addVendor(Vendor vendor) {
        vendors.put(vendor.getName(), vendor);
    }

    public Vendor findVendorByName(String name) {
        return vendors.get(name);
    }

    public void updateVendor(Vendor vendor) {
        vendors.put(vendor.getName(), vendor);
    }

    public void deleteVendor(String name) {
        vendors.remove(name);
    }

    public boolean containsVendor(Vendor vendor) {
        return vendors.containsKey(vendor.getName());
    }



    public void saveVendorsToFile() {
        List<String> lines = new ArrayList<>();
        for (Vendor vendor : vendors.values()) {
            String line = String.join(",",
                    vendor.getName(),
                    vendor.getServiceType(),
                    vendor.getEmail(),
                    String.valueOf(vendor.getTime()),
                    String.valueOf(vendor.getDate()));
            lines.add(line);
        }
        try {
            Files.write(Paths.get(VENDORS_FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("Error saving vendors to file: " + e.getMessage());
        }
    }


    public void loadVendorsFromFile() {
        vendors.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get(VENDORS_FILE_PATH));
            for (String line : lines) {
                String[] details = line.split(",");
                if (details.length == 5) {
                    Vendor vendor = new Vendor(details[0], details[1], details[2], Integer.parseInt(details[3]), Integer.parseInt(details[4]));
                    vendors.put(vendor.getName(), vendor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading vendors from file: " + e.getMessage());
        }
    }


    public Collection<Vendor> getAllVendors() {
        return vendors.values();
    }

    public void saveEventDetailsToFile(String event) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/events.txt", true))) {
            bw.write(event);
            bw.newLine();
            System.out.println("Event added to events.txt successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to events.txt: " + e.getMessage());
        }
    }

    public void editEventInFile(String eventName, Scanner scanner) {
        Path path = Paths.get("src/events.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            int index = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains(eventName)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                System.out.println("Editing Event: " + lines.get(index));
                System.out.println("Enter new details (eventName,date,time,price,vendorName):");
                String newDetails = scanner.nextLine();
                lines.set(index, newDetails);
                Files.write(path, lines);
                System.out.println("Event updated successfully.");
            } else {
                System.out.println("Event not found.");
            }
        } catch (IOException e) {
            System.err.println("Error when editing an event: " + e.getMessage());
        }
    }
    public void deleteEventFromFile(String eventName) {
        Path path = Paths.get("src/events.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            lines.removeIf(line -> line.contains(eventName));
            Files.write(path, lines);
            System.out.println("Event deleted successfully, if it existed.");
        } catch (IOException e) {
            System.err.println("Error when deleting an event: " + e.getMessage());
        }
    }




}
