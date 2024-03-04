package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServiceProvider {
    private Event event;
    private static final Map<String, Event> savedevent = new HashMap<>();
    private String username;
    private String password;
    private boolean isChoosingToEditEvent;


    private boolean isLoggedIn;
    private boolean choosingToCreateEvent;
    public void login(String username, String password) {
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


    }

    public void saveEventDetails(String eventName, String date, String location, String services) {

        event = new Event(eventName, date, location, services);
    }

    public Event getEvent() {
        return event;
    }



    public void chooseToEditEvent() {
        isChoosingToEditEvent = true;
    }

    public boolean isChoosingToEditEvent() {
        return isChoosingToEditEvent;
    }

    public void displayCurrentEventDetails(Event event) {
        System.out.println("Current Event Details:");
        System.out.println("Name: " + event.getName());
        System.out.println("Date: " + event.getDate());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Services: " + event.getServices());
    }

    public void modifyEventDetails(Event event, String newName, String newDate, String newLocation, String newServices) {

        event.setName(newName);
        event.setDate(newDate);
        event.setLocation(newLocation);
        event.setServices(newServices);
    }

    public void saveUpdatedEventDetails(Event event) {

        savedevent.put(event.getName(), event);
    }

    public static Event getEventFromDatabase(String eventName) {
        return savedevent.get(eventName);
    }
}
