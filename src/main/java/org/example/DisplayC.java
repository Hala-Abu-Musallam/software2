package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class DisplayC {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public static String username;
    public static boolean addToCalen;

    ArrayList<DisplayC> displayC = new ArrayList<>();

    public void sorting(String date, String time) {
        fromFile();

        Collections.sort(displayC, new Comparator<DisplayC>() {
            @Override
            public int compare(DisplayC o1, DisplayC o2) {
                try {
                    Date date1 = dateFormat.parse(o1.date);
                    Date date2 = dateFormat.parse(o2.date);
                    Date time1 = timeFormat.parse(o1.time);
                    Date time2 = timeFormat.parse(o2.time);

                    int dateComparison = date1.compareTo(date2);
                    if (dateComparison != 0) {
                        return dateComparison;
                    }

                    return time1.compareTo(time2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });

        saveToFile(date, time);
        addToCalen = true;
    }

    private void saveToFile(String date, String time) {
        try {
            File file = new File("src/calender.txt");

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                for (DisplayC event : displayC) {
                    event.username = username;
                    event.date = date;
                    event.time = time;
                    String Calen = username + ", " + date + ", " + time + "\n";
                    bufferedWriter.write(Calen);
                }
            }
            System.out.println("Information stored successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void fromFile() {
        try {
            displayC.clear();
            File file = new File("src/eventList.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String info;
                while ((info = bufferedReader.readLine()) != null) {
                    String[] data = info.split(",");
                    username = data[0];
                    String date = data[1];
                    String time = data[2];
                    DisplayC event = new DisplayC();
                    event.username = username;
                    event.date = date;
                    event.time = time;
                    displayC.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Add getters and setters for username, date, and time if needed
    private String date;
    private String time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}