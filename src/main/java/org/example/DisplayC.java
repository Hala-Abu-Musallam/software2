package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DisplayC {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH-HH");

    public static String date;
    public static String time;
    public static String username;
    public static boolean addToCalen;
    private static final Logger logger = LoggerFactory.getLogger(DisplayC.class);
    ArrayList<DisplayC> displayC = new ArrayList<>();

    public void sorting(String date,String time) {
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

        for (int i = 0; i < displayC.size(); i++) {
            DisplayC event = displayC.get(i);
            logger.info(event.username + ", " + event.date + ", " + event.time);

            saveToFile(username,date,time);
            addToCalen=true;
        }

        saveToFile(username,date,time);
        addToCalen=true;
    }

    private void saveToFile(String username,String date ,String time) {

        try {
            File file = new File("src/calender.txt");

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                for (DisplayC event : displayC) {
                    event.username=username;
                    event.date=date;
                    event.time=time;
                    String Calen = username + ", " +date + ", " + time + "\n";
                    bufferedWriter.write(Calen);
                }
            }
            logger.info("Information stored successfully.");
        } catch (IOException e) {
            logger.info(e.getMessage());
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
                    time = data[2];
                    displayC.add(this);
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}

