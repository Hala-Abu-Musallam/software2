
package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.VendorsByUser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SelectVendors {
    String date;
    String time;
    String username;


    @Given("date is {string} and time is'{int}-{int}'")
    public void dateIsAndTimeIs(String string, int int1, int int2) {
        // Write code here that turns the phrase above into concrete actions
        username="Hala@user.com";
        date=string;
        //    time=String.valueOf(int1)+'-'+String.valueOf(int2);
        time=Integer.toString(int1)+'-'+Integer.toString(int2);


    }

    @When("user choose vendors  {string}{string}")
    public void user_choose_vendors(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        VendorsByUser vendor =new VendorsByUser();
        vendor.addVendor(username,string,date,time,string2);
    }

    @Then("book vendor to the event")
    public void bookVendorToTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(VendorsByUser.vendor_type>0 && VendorsByUser.vendor_type<4);
    }
}
