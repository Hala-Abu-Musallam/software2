package org.example;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class DisplayC {
    String username;
    int date;
    int time;
    double price;

    public Event(String username, int date, int time, double price) {
        this.username = username;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    @Override
    public String toString() {
        return username + ", " + date + ", " + time + ", " + price;
    }
}


