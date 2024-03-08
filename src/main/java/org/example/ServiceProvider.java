package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




public class ServiceProvider {
    private static final Map<String, Event> savedEvents = new HashMap<>();
    private String username;
    private String password;
    private boolean isLoggedIn;

    private Event event;


    private boolean choosingToCreateEvent;

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
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Services Required: ");
        String services = scanner.nextLine();
        event = new Event(eventName, date, location, services);
    }

    public void saveEventDetails(String eventName, String date, String location, String services) {
        Event event = new Event(eventName, date, location, services);
        savedEvents.put(eventName, event);
        System.out.println("Event saved successfully!");
    }


    public Event getEvent() {
        return event;
    }


    public void editEvent(String eventName) {
        // Debugging: Print out all keys in savedEvents
        System.out.println("Keys in savedEvents: " + savedEvents.keySet());

        if (savedEvents.containsKey(eventName)) {
            event = savedEvents.get(eventName);
            System.out.println("Current event details:");
            System.out.println(event);

            System.out.println("Enter new event details:");
            promptForEventDetails(); // Reusing the promptForEventDetails method for editing

            // Update the event in the savedEvents map
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
            return true; // Return true indicating successful deletion
        } else {
            System.out.println("Event not found!");
            return false; // Return false indicating event not found
        }
    }

    public boolean modifyEventDetails(String eventName, String newDate, String newLocation, String newServices) {
        if (savedEvents.containsKey(eventName)) {
            Event eventToUpdate = savedEvents.get(eventName);
            eventToUpdate.setDate(newDate);
            eventToUpdate.setLocation(newLocation);
            eventToUpdate.setServices(newServices);
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


}


