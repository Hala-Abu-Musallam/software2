package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.ServiceProvider;

import static org.junit.Assert.*;
import java.util.Optional;
import org.example.Venue;
import org.junit.Test;

public class placemanagment {
    private ServiceProvider serviceProvider=new ServiceProvider();
    private Venue venue=new Venue();
    private boolean operationResult;
    @Test

    @Given("I am logged in as a service provider")
    public void iAmLoggedInAsAServiceProvider() {
        serviceProvider.login("username", "password"); // Simulate logging in
        assertTrue("Service provider should be logged in", serviceProvider.isLoggedIn());

    }
    @Test

    @When("I choose to create a new wedding venue")
    public void iChooseToCreateANewWeddingVenue() {

        venue = new Venue("Venue Name", "Owner Name", "Location", 100, 5000.00);
        assertNotNull("Venue should not be null after creation", venue);
    }
    @Test

    @Then("I should be able to specify the venue's name, my name \\(as the owner), the location, and the asking price")
    public void iShouldBeAbleToSpecifyTheVenueSNameMyNameAsTheOwnerTheLocationAndTheAskingPrice() {
        // Here, you would set the details for the venue
        venue.setName("Grand Hall");
        venue.setOwnerName("Alice");
        venue.setLocation("Downtown");
        venue.setPricing(100);
        venue.setPricing(5000.00);
        serviceProvider.addVenue(venue);
        assertTrue(serviceProvider.containsVenue(venue));
    }
    @Test

    @Given("I have previously created a wedding venue")
    public void iHavePreviouslyCreatedAWeddingVenue() {
        venue = new Venue("Grand Hall", "Alice", "Downtown", 200, 5000.00);
        serviceProvider.addVenue(venue);
    }
    @Test

    @When("I choose to edit this venue")
    public void iChooseToEditThisVenue() {
        // Here, you might simulate selecting the venue to edit
        // The actual update happens in the next step
    }
    @Test

    @Then("I should be able to update the venue's name, location, and asking price")
    public void iShouldBeAbleToUpdateTheVenueSNameLocationAndAskingPrice() {

        venue.setName("lilati venue");
        venue.setLocation("New Downtown");
        venue.setPricing(5500.00);

    }
    @Test

    @When("I choose to delete this venue")
    public void iChooseToDeleteThisVenue() {

        operationResult = serviceProvider.deleteVenue(venue); // Assuming deleteVenue returns a boolean indicating success
    }
    @Test

    @Then("the venue should be removed from the system")
    public void theVenueShouldBeRemovedFromTheSystem() {
        // Verify the venue was removed
        assertFalse(serviceProvider.containsVenue(venue)); // As before, you'll need a method to check if the venue is present
    }
}

