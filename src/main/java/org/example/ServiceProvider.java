package org.example;


import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.logging.Logger;



public class ServiceProvider {

    private Map<String, Vendor> vendors = new HashMap<>();
    public static final String VENDORS_FILE_PATH = "src/vendor.txt";

    public static final String VENUES_FILE_PATH = "src/Venues.txt";
    private static final Map<String, Event> savedEvents = new HashMap<>();
    private static final Logger logger = Logger.getLogger(ServiceProvider.class.getName());

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
        logger.info("Please enter event details:");
        logger.info("Event Name: ");
        String eventName = scanner.nextLine();
        logger.info("Date: ");
        String date = scanner.nextLine();
        logger.info("Time: ");
        String time = scanner.nextLine();
        logger.info("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        logger.info("Vendor Name: ");
        String vendorName = scanner.nextLine();

        event = new Event(eventName, date, time, price, vendorName);
    }


    public void saveEventDetails(String eventName, String date, String time, double price, String vendorName) {
        Event event = new Event(eventName, date, time, price, vendorName);
        savedEvents.put(eventName, event);
        logger.info("Event saved successfully!");
    }

    public Event getEvent() {
        return event;
    }


    public void editEvent(String eventName) {
        logger.info("Keys in savedEvents: " + savedEvents.keySet());

        if (savedEvents.containsKey(eventName)) {
            event = savedEvents.get(eventName);
            logger.info("Current event details: " + event);

            logger.info("Enter new event details:");
            promptForEventDetails();
            savedEvents.put(eventName, event);

            logger.info("Event details updated successfully!");
        } else {
            logger.warning("Event not found!");
        }
    }



    public boolean deleteEvent(String eventName) {
        if (savedEvents.containsKey(eventName)) {
            savedEvents.remove(eventName);
            logger.info("Event deleted successfully!");
            return true;
        } else {
            logger.warning("Event not found!"); // Logging a warning when the event is not found
            return false;
        }
    }


    public boolean modifyEventDetails(String eventName, String newDate, String newTime, double newPrice, String newVendorName) {
        boolean[] modified = {false};

        savedEvents.computeIfPresent(eventName, (key, eventToUpdate) -> {
            eventToUpdate.setDate(newDate);
            eventToUpdate.setTime(newTime);
            eventToUpdate.setPrice(newPrice);
            eventToUpdate.setVendorName(newVendorName);
            modified[0] = true;
            return eventToUpdate;
        });

        return modified[0];
    }


    public static Event getEventFromDatabase(String eventName) {
        return savedEvents.get(eventName);
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
        try (Stream<String> lines = Files.lines(Paths.get(VENUES_FILE_PATH))) {
            lines.forEach(logger::info);
        } catch (IOException e) {
            logger.severe("Error displaying venues from file: " + e.getMessage());        }
    }



    public void saveVenuesToFile() {
        try {
            Path path = Paths.get(VENUES_FILE_PATH);
            List<String> lines = venues.stream()
                    .map(v -> String.join(",", v.getName(), v.getOwnerName(), v.getLocation(),
                            String.valueOf(v.getCapacity()), String.valueOf(v.getPricing())))
                    .toList();
            Files.write(path, lines);
        } catch (IOException e) {
            logger.severe("Error saving venues to file: " + e.getMessage());
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
            logger.severe("Error saving vendors to file: " + e.getMessage());
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
            logger.severe("Error loading vendors from file: " + e.getMessage());
        }
    }


    public Collection<Vendor> getAllVendors() {
        return vendors.values();
    }







}
