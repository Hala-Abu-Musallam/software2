package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.CheckEvent;

import static org.junit.Assert.assertTrue;

public class AddByUser {
    CheckEvent checkEvent=new CheckEvent();
    String username;
    @Given("username is {string}")
    public void username_is(String string) {
        // Write code here that turns the phrase above into concrete actions
        username=string;
    }

    @When("Date and Time are available {string}{string}")
    public void dateAndTimeAreAvailableDateTime(String string, String string2) {

        checkEvent.checkEvent(string,string2,username);

    }

    @Then("add event to waiting list")
    public void addEventToWaitingList() {
        assertTrue(CheckEvent.addSuccess);

    }

    @When("Date or Time is unavailable or wrong {string}{string}")
    public void dateOrTimeIsUnavailableOrWrongDateTime(String string, String string2) {
        checkEvent.checkEvent(string,string2,username);
    }

    @Then("failed to add event")
    public void failedToAddEvent() {
        assertTrue(!CheckEvent.addSuccess);
    }
}