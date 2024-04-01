package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;


public class ShowCalender   {

    String adminname;

    @Given("Admin name is {string}")
    public void admin_name_is(String string) {
        // Write code here that turns the phrase above into concrete actions\
        adminname=string;
    }


    @When("admin choose display calender {string}")
    public void admin_choose_display_calender(String string) {
        // Write code here that turns the phrase above into concrete actions

    }



    @Then("display calender file")
    public void display_calender_file() {
        // Write code here that turns the phrase above into concrete actions
     //   assertTrue(DisplayB.addToBudget);
    }

}
