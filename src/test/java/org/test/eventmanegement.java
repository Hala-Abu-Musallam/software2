package org.test;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Event;
import org.example.ServiceProvider;
import org.example.User;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class eventmanegement {
    @Given("a service provider is logged in.")
    public void aServiceProviderIsLoggedIn() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.login("username", "password");
        boolean isLoggedIn = serviceProvider.isLoggedIn();
        assertTrue(isLoggedIn);
    }
    @When("the service provider chooses to create a new event.")
    public void theServiceProviderChoosesToCreateANewEvent() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.chooseToCreateEvent();
        assertTrue(serviceProvider.isChoosingToCreateEvent());
    }

    @Then("the system should save the event details.")
    public void theSystemShouldSaveTheEventDetails() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.login("username", "password");
        serviceProvider.chooseToCreateEvent();

        serviceProvider.promptForEventDetails();
        serviceProvider.saveEventDetails("Wedding", "2024-05-20", "New York", "Catering, Photography");

        Event event = serviceProvider.getEvent();
        assertEquals("Wedding", event.getName());
        assertEquals("2024-05-20", event.getDate());
        assertEquals("New York", event.getLocation());
        assertEquals("Catering, Photography", event.getServices());
    }







    @When("the service provider chooses to edit the event.")
    public void theServiceProviderChoosesToEditTheEvent() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.login("username", "password");
        serviceProvider.chooseToEditEvent();
        assertTrue(serviceProvider.isChoosingToEditEvent());

    }
    @Then("the system should display the current event details.")
    public void theSystemShouldDisplayTheCurrentEventDetails() {
        ServiceProvider serviceProvider = new ServiceProvider();
        Event event = new Event("Birthday", "2024-06-15", "Los Angeles", "Catering, DJ");
        serviceProvider.displayCurrentEventDetails(event);
    }
    @Then("the system should allow the service provider to modify event details such as event name, date, location, and services required.")
    public void theSystemShouldAllowTheServiceProviderToModifyEventDetailsSuchAsEventNameDateLocationAndServicesRequired() {
        ServiceProvider serviceProvider = new ServiceProvider();
        Event event = new Event("Birthday", "2024-06-15", "Los Angeles", "Catering, DJ");
        serviceProvider.modifyEventDetails(event, "Wedding", "2024-05-20", "New York", "Catering, Photography");

        assertEquals("Wedding", event.getName());
        assertEquals("2024-05-20", event.getDate());
        assertEquals("New York", event.getLocation());
        assertEquals("Catering, Photography", event.getServices());
    }
    @Then("the system should save the updated event details.")
    public void theSystemShouldSaveTheUpdatedEventDetails() {
        ServiceProvider serviceProvider = new ServiceProvider();
        Event event = new Event("Wedding", "2024-05-20", "New York", "Catering, Photography");

        serviceProvider.saveUpdatedEventDetails(event);

        Event savedEvent = ServiceProvider.getEventFromDatabase("Wedding");
        assertNotNull(savedEvent);
        assertEquals("Wedding", savedEvent.getName());
        assertEquals("2024-05-20", savedEvent.getDate());
        assertEquals("New York", savedEvent.getLocation());
        assertEquals("Catering, Photography", savedEvent.getServices());
    }




    @Given("there exists an event that the service provider manages.")
    public void thereExistsAnEventThatTheServiceProviderManages() {

    }
    @When("the service provider chooses to delete the event.")
    public void theServiceProviderChoosesToDeleteTheEvent() {

    }
    @Then("the system should confirm the service provider's intention to delete the event.")
    public void theSystemShouldConfirmTheServiceProviderSIntentionToDeleteTheEvent() {

    }
    @Then("the system should remove the event from the database.")
    public void theSystemShouldRemoveTheEventFromTheDatabase() {

    }
    @Then("the system should confirm successful event deletion to the service provider.")
    public void theSystemShouldConfirmSuccessfulEventDeletionToTheServiceProvider() {

    }

}
