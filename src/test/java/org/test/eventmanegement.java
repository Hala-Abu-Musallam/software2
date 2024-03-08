package org.test;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Event;
import org.example.ServiceProvider;
import org.example.User;
import io.cucumber.java.en.Given;
import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class eventmanegement {
    private ServiceProvider serviceProvider;
    @Before
    public void setUp() {
        serviceProvider = new ServiceProvider();
    }
    public eventmanegement(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }




    @Given("a service provider is logged in.")
    public void aServiceProviderIsLoggedIn() {
        serviceProvider.login("username", "password");
        assertTrue(serviceProvider.isLoggedIn());
    }

    @When("the service provider chooses to create a new event.")
    public void theServiceProviderChoosesToCreateANewEvent() {
        serviceProvider.chooseToCreateEvent();
        assertTrue(serviceProvider.isChoosingToCreateEvent());
    }

    @Then("the system should save the event details.")
    public void theSystemShouldSaveTheEventDetails() {
        serviceProvider.saveEventDetails("Wedding", "2024-05-20", "New York", "Catering, Photography");
        Event savedEvent = ServiceProvider.getEventFromDatabase("Wedding");
        assertNotNull(savedEvent);
        assertEquals("Wedding", savedEvent.getName());
        assertEquals("2024-05-20", savedEvent.getDate());
        assertEquals("New York", savedEvent.getLocation());
        assertEquals("Catering, Photography", savedEvent.getServices());
    }


    @When("the service provider chooses to delete the event.")
    public void theServiceProviderChoosesToDeleteTheEvent() {
        serviceProvider.saveEventDetails("Birthday", "2024-06-15", "Los Angeles", "Catering, DJ");
        assertTrue(serviceProvider.deleteEvent("Birthday"));
        assertNull(ServiceProvider.getEventFromDatabase("Birthday"));
    }

    @Then("the system should remove the event from the database.")
    public void theSystemShouldRemoveTheEventFromTheDatabase() {
        assertNull(ServiceProvider.getEventFromDatabase("Birthday"));;  // Pass event.getName() as the parameter
    }

    @When("the service provider chooses to edit the event.")
    public void theServiceProviderChoosesToEditTheEvent() {
        serviceProvider.saveEventDetails("Birthday", "2024-06-15", "Los Angeles", "Catering, DJ");
        assertTrue(serviceProvider.modifyEventDetails("Birthday", "2024-06-16", "San Francisco", "Catering, DJ, Entertainment"));
    }

    @Then("the system should allow the service provider to modify event details such as event name, date, location, and services required.")
    public void theSystemShouldAllowTheServiceProviderToModifyEventDetailsSuchAsEventNameDateLocationAndServicesRequired() {
        Event modifiedEvent = ServiceProvider.getEventFromDatabase("Birthday");
        assertNotNull(modifiedEvent);
        assertEquals("2024-06-16", modifiedEvent.getDate());
        assertEquals("San Francisco", modifiedEvent.getLocation());
        assertEquals("Catering, DJ, Entertainment", modifiedEvent.getServices());
    }


}
