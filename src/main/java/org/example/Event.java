package org.example;

public class Event {

        private String name;
        private String date;
        private String location;
        private String services;

        public Event(String name, String date, String location, String services) {
            this.name = name;
            this.date = date;
            this.location = location;
            this.services = services;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }
    }


