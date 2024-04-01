package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CheckEvent {


    ArrayList<String> events = new ArrayList<>();
    static SimpleDateFormat[] simpleDateFormats = {
            new SimpleDateFormat("dd/MM/yyyy"),
            new SimpleDateFormat("dd/M/yyyy"),
            new SimpleDateFormat("d/MM/yyyy"),
            new SimpleDateFormat("d/M/yyyy")
    };
    public static boolean addSuccess;
    public static String username;

    private static void checkIfDateValid(Date inputDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        if (month > 12) {
            throw new IllegalArgumentException("Invalid month in the date.");
        }

        int monthDays = getDaysInMonth(month, year);
        if (day > monthDays) {
            throw new IllegalArgumentException("Invalid day in this month.");
        }
    }

    private static int getDaysInMonth(int month, int year) {
        return switch (month) {
            case 2 -> {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) yield 29;
                yield 28;
            }
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }

    public static boolean checkDate(String date) {
        for (SimpleDateFormat simpleDateFormat : simpleDateFormats) {
            simpleDateFormat.setLenient(false);
            try {
                Date inputDate = simpleDateFormat.parse(date);
                checkIfDateValid(inputDate);
                Date currentDate = new Date();
                if (!inputDate.before(currentDate)) {
                    addSuccess = true;
                    return true;
                } else {
                    System.out.println("You cant make an appointment in passed days :/");
                    addSuccess = false;
                    return false;
                }
            } catch (ParseException | IllegalArgumentException e) {
                System.out.println("Date Format Wrong or Try another date ;)");
                addSuccess = false;
                return false;
            }
        }
        return false;
    }

    public void checkEvent(String date, String time, String username) {

        if (!checkDate(date)) {
            return;
        }
        if (!checkTime(time)) {
            addSuccess = false;
            return;
        }


        for (int i = 0; i < events.size(); i++) {


            if (events.get(i).equals(date)) {
                if (events.get(i + 1).equals((time)))
                    addSuccess = false;
            } else {
                //writeUsers(username, date, time);
                i++;
                addSuccess=true;
            }
        }

        writeUsers(username, date, time);


    }

    private static boolean checkTime(String time) {
        String[] array = time.split("-");
        int startTime = Integer.parseInt(array[0]);
        int endTime = Integer.parseInt(array[1]);
        if (startTime < 0 || startTime > 12 || endTime < 0 || endTime > 12)
            return false;
        return true;
    }

    public void getEventfromFile() {
        try {
            events.clear();
            File file = new File("src/events.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String dateAndTime;
                while ((dateAndTime = bufferedReader.readLine()) != null) {
                    String[] data = dateAndTime.split(",");
                    events.add(data[1]);
                    events.add(data[2]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeUsers(String username, String date, String time) {
        try {
            File file = new File("src/waitList.txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                String event = username + "," + date + "," + time + "\n";

                bufferedWriter.write(event);
                //System.out.println(event);
                addSuccess = true;
            }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }



    }



}