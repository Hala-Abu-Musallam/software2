package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class DisplayC {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private String username;
    private String date;
    private String startTime;
    private String endTime;

    public static boolean addToCalendar;

    ArrayList<DisplayC> displayC = new ArrayList<>();

    public void sorting(String date, String time) {
        fromFile();

        Collections.sort(displayC, new Comparator<DisplayC>() {
            @Override
            public int compare(DisplayC o1, DisplayC o2) {
                try {
                    Date date1 = dateFormat.parse(o1.date);
                    Date date2 = dateFormat.parse(o2.date);
                    Date time1 = timeFormat.parse(o1.startTime);
                    Date time2 = timeFormat.parse(o2.startTime);

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
        addToCalendar = true;
    }

    private void saveToFile(String date, String time) {
        try {
            File file = new File("src/calendr.txt");

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                for (DisplayC event : displayC) {
                    event.username = username;
                    event.date = date;
                    event.startTime = time;
                    event.endTime = time; // Assuming the end time is the same as start time
                    String Calen = username + ", " + date + ", " + event.startTime + "-" + event.endTime + "\n";
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
                    date = data[1];
                    String[] times = data[2].split("-");
                    startTime = times[0];
                    endTime = times[1];
                    DisplayC event = new DisplayC();
                    event.username = username;
                    event.date = date;
                    event.startTime = startTime;
                    event.endTime = endTime;
                    displayC.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Add getters and setters for username, date, startTime, and endTime if needed
}