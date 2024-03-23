package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.CheckEvent;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddByUser {
CheckEvent checkEvent=new CheckEvent();
String username;
    String time;
    @Given("username is {string}")
    public void username_is(String string) {
        // Write code here that turns the phrase above into concrete actions
   username=string;

    }

    @When("Date and Time are available {string}'{int}-{int}")
    public void dateAndTimeAreAvailable(String string, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions

        time=String.valueOf(int1)+'-'+String.valueOf(int2);
        checkEvent.checkEvent(string,time,username);

    }

    @Then("add event to waiting list")
    public void addEventToWaitingList() {
        assertTrue(CheckEvent.addSuccess);

    }

    @When("Date or Time is unavailable or wrong {string}{string}")
    public void dateOrTimeIsUnavailableOrWrong(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

     checkEvent.checkEvent(string,string2,username);
    }

    @Then("failed to add event")
    public void failedToAddEvent() {
        assertFalse(CheckEvent.addSuccess);
    }
}
