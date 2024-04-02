
package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.DisplayC;

import static org.junit.Assert.assertTrue;
//test sonarcloud
public class ShowCalender {
    String username;
    @Given("Admin name is {string}")
    public void admin_name_is(String string) {

        username=string;
    }
    @When("the event is earlier according to time and date {string} {string}")
    public void the_event_is_earlier_according_to_time_and_date(String string, String string2) {

        DisplayC displayC=new DisplayC();
        displayC.sorting(string,string2);

    }
    @Then("sorting events in calender file")
    public void sorting_events_in_calender_file() {

        assertTrue(DisplayC.addToCalen);
    }

}