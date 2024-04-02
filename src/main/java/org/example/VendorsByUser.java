
package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;

public class VendorsByUser{

    public static String email;
    public static String type;

    public static String username;

    public static String date;
    public static String time;
    public static boolean addSuccess;

    public static int vendor_type;
    private static final Logger logger = LoggerFactory.getLogger(VendorsByUser.class);

    ArrayList<VendorsByUser> vendors = new ArrayList<>();
    public void getVendorsFromFile() {
        try {

            vendors.clear();
            File file = new File("src/vendor.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String EmailPriceType;
                while ((EmailPriceType = bufferedReader.readLine()) != null) {
                    String[] data = EmailPriceType.split(",");
                    email=data[0];
                    type=data[2];

                    vendors.add(this);
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }


    public void writeVendors(String username, String date, String time, String email,String type) {
        try {
            File file = new File("src/waitList.txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                String vendor = username +  "," + date + "," + time + ","+ email +"," + type +"\n";
                bufferedWriter.write(vendor);
                addSuccess = true;
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }



    }

    public void addVendor (String usernamee,String datee,String timee,String email,String typee ){
        date=datee;
        type=typee;
        time=timee;
        username=usernamee;


        getVendorsFromFile();
        if ( typee.equals ( "decoration" ) ){
            vendor_type = 1;

            writeVendors (username , date , time ,email,typee);

        }
        else if (typee.equals("DJ")){
            vendor_type = 2 ;
            writeVendors (username , date , time ,email,typee );
        }
        else if (typee.equals("photographer")){
            vendor_type = 3 ;
            writeVendors (username , date , time ,email,typee);
        }
        else{
            vendor_type=-1;

        }
    }
}
