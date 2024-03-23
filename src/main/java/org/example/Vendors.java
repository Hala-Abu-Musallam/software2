package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vendors {

 public static String email;
 public static String type;
 public static int price;

    ArrayList<Vendors> vendors = new ArrayList<>();
    public void getVendorsfromFile() {
        try {

            vendors.clear();
            File file = new File("src/vendors.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String EmailPriceType;
                while ((EmailPriceType = bufferedReader.readLine()) != null) {
                    String[] data = EmailPriceType.split(",");
                   email=data[0];
                   price=Integer.parseInt(data[1]);
                   type=data[2];
                   vendors.add(this);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
