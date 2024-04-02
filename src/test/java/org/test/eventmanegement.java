package org.test;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Event;
import org.example.ServiceProvider;
import io.cucumber.java.en.Given;
import org.junit.Before;
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
        serviceProvider.saveEventDetails("Wedding", "2024-05-20", "10:00-12:00", 5000.0, "VendorName");
        Event savedEvent = ServiceProvider.getEventFromDatabase("Wedding");
        assertNotNull(savedEvent);
        assertEquals("Wedding", savedEvent.getName());
        assertEquals("2024-05-20", savedEvent.getDate());
        assertEquals("10:00-12:00", savedEvent.getTime());
        assertEquals(5000.0, savedEvent.getPrice(), 0.01);
        assertEquals("VendorName", savedEvent.getVendorName());
    }



    @When("the service provider chooses to delete the event.")
    public void theServiceProviderChoosesToDeleteTheEvent() {
        serviceProvider.saveEventDetails("Birthday", "2024-06-15", "10:00-15:00", 500.0, "Catering, DJ");
        assertTrue(serviceProvider.deleteEvent("Birthday"));
        assertNull(ServiceProvider.getEventFromDatabase("Birthday"));
    }

    @Then("the system should remove the event from the database.")
    public void theSystemShouldRemoveTheEventFromTheDatabase() {
        assertNull(ServiceProvider.getEventFromDatabase("Birthday"));
    }

    @When("the service provider chooses to edit the event.")
    public void theServiceProviderChoosesToEditTheEvent() {
        serviceProvider.saveEventDetails("Birthday", "2024-06-15", "10:00-15:00", 500.0, "Catering, DJ");
        assertTrue(serviceProvider.modifyEventDetails("Birthday", "2024-06-16", "10:00-15:00", 600.0, "Catering, DJ, Entertainment"));
    }


    @Then("the system should allow the service provider to modify event details such as event name, date, and services required.")
    public void theSystemShouldAllowTheServiceProviderToModifyEventDetailsSuchAsEventNameDateAndServicesRequired() {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.saveEventDetails("Birthday", "2024-06-15", "10:00-15:00", 500.0, "Catering, DJ");

        assertTrue(serviceProvider.modifyEventDetails("Birthday", "2024-06-16", "10:00-15:00", 600.0, "Catering, DJ, Entertainment"));

        Event modifiedEvent = ServiceProvider.getEventFromDatabase("Birthday");
        assertNotNull(modifiedEvent);
        assertEquals("2024-06-16", modifiedEvent.getDate());
        assertEquals("10:00-15:00", modifiedEvent.getTime());
        assertEquals(600.0, modifiedEvent.getPrice(), 0.01);
        assertEquals("Catering, DJ, Entertainment", modifiedEvent.getVendorName());
    }
    @Then("the system should allow the service provider to modify event details such as event name, date, location, and services required.")
    public void theSystemShouldAllowTheServiceProviderToModifyEventDetailsSuchAsEventNameDateLocationAndServicesRequired() {

    }


}
