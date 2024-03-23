package org.example;

import java.io.*;
import java.util.ArrayList;

public class VendorsByUser{

   public static String email;
   public static String type;
   public static int price;

   public static String username;

   public static int date;
    public static int time;
   public static boolean addSuccess;

    public static int vendor_type;

    ArrayList<VendorsByUser> vendors = new ArrayList<>();
    public void getVendorsfromFile() {
        try {

            vendors.clear();
            File file = new File("src/vendor.txt");
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


    public void writeVendors(String username, int date, int time,String email,String type) {
        try {
            File file = new File("src/waitList.txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                String vendor = username + "," + date + "," + time + "+"+email + "," + type+"\n";

                bufferedWriter.write(vendor);
                System.out.println(vendor);
                addSuccess = true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }

    public void addvendor (String type){
        getVendorsfromFile();
        if ( type.contains ( "decoration" ) ){
            vendor_type = 1;
            writeVendors (username , date , time ,email,type );
            }
        else if (type.contains("DJ")){
            vendor_type = 2 ;
            writeVendors (username , date , time ,email,type );
            }
        else if (type.contains("photographer")){
            vendor_type = 3 ;
            writeVendors (username , date , time ,email,type);
        }
        else{
            vendor_type=-1;
        }
    }
}
