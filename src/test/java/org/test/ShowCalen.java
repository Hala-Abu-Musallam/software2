package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DisplayC;


import static org.junit.Assert.assertTrue;
//test sonarcloud
public class ShowCalen {
    String username;

    @Given("Admin name is {string}")
    public void adminNameIs(String string) {
        // Write code here that turns the phrase above into concrete actions
        username=string;
    }
    @When("the event is earlier according to time and date {string} {string}")
    public void theEventIsEarlierAccordingToTimeAndDate(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        DisplayC displayC=new DisplayC();
        displayC.sorting(string,string2);

    }
    @Then("sorting events in calender file")
    public void sortingEventsInCalenderFile() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(DisplayC.addToCalendar);
    }

}
