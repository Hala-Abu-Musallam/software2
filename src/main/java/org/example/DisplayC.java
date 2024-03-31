package org.example;

import java.io.*;
import java.util.ArrayList;

public class DisplayC {
    public static int date;
    public static int time;
    public static double price;
    public static double price1;
    public static double price2;
    public static double adminBudg;
    public static double serviceBudg;
    public static String username;
    public static boolean  addToBudget;

    public static String BudgCalen;
    ArrayList<DisplayB> display = new ArrayList<>();
    public void getInformationfromFile() {
        try {

            display.clear();
            File file = new File("src/eventList.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String information;
                while ((information = bufferedReader.readLine()) != null) {
                    String[] data = information.split(",");
                    username=data[0];
                    date=Integer.parseInt(data[1]);
                    time=Integer.parseInt(data[2]);
                    price=Double.parseDouble(data[3]);
                    display.add(this);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void BudgetDisplay(String BudgCalen){
        getInformationfromFile();
        if(BudgCalen.contains("budget")){
            price1=price;
            price2=price;
            serviceBudg = price1 *0.8;
            adminBudg = price2*0.2;
            writeInformation( username,date, time, price,serviceBudg, adminBudg);
            addToBudget = true;
        }
        else
            addToBudget=false;
        return;
    }


    public void writeInformation(String username, int date, int time, double price,double serviceBudg, double adminBudg) {
        try {
            File file = new File("src/budget.txt");

            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                bufferedWriter.write("Username: " + username);
                bufferedWriter.newLine();
                bufferedWriter.write("Date: " + date);
                bufferedWriter.newLine();
                bufferedWriter.write("Time: " + time);
                bufferedWriter.newLine();
                bufferedWriter.write("Price: " + price);
                bufferedWriter.newLine();
                bufferedWriter.write("Service Budget: " + serviceBudg);
                bufferedWriter.newLine();
                bufferedWriter.write("Admin Budget: " + adminBudg);
            }
            System.out.println("Information stored successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
