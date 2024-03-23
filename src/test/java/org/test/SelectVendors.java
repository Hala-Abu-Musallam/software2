package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SelectVendors {
String date;
String time;


    @Given("date is {string} and time is'{int}-{int}'")
    public void dateIsAndTimeIs(String string, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
     date=string;
     time=String.valueOf(int1)+'-'+String.valueOf(int2);
    }
    @When("user choose vendors for'decoration@serviceprovider.com'{string}{string}")
    public void userChooseVendorsForDecorationServiceproviderCom(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("book vendor to the event")
    public void bookVendorToTheEvent() {
        // Write code here that turns the phrase above into concrete actions

    }
}
