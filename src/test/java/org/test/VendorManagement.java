package org.test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ServiceProvider;
import org.example.Vendor;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorManagement {

        private ServiceProvider serviceProvider=new ServiceProvider();
        private Vendor vendor=new Vendor();


    @When("I choose to create a new vendor")
    public void iChooseToCreateANewVendor() {
        vendor = new Vendor("moh", "Catering", "example@example.com", 10, 2024);

        serviceProvider.addVendor(vendor);
        assertNotNull("Venue should not be null after creation", vendor);


    }


    @Then("I should see a confirmation message indicating the vendor has been created successfully")
    public void iShouldSeeAConfirmationMessageIndicatingTheVendorHasBeenCreatedSuccessfully() {

    serviceProvider.containsVendor(vendor);

    }






    @Given("I have selected an existing vendor to edit")
    public void iHaveSelectedAnExistingVendorToEdit() {

    }

    @When("I update the vendor's information such as name, service type, or pricing")
    public void iUpdateTheVendorSInformationSuchAsNameServiceTypeOrPricing() {
        vendor.setName("New Catering Co");
        vendor.setServiceType("Premium Catering");
        vendor.setEmail("newemail@example.com");
        vendor.setTime(12);
        vendor.setDate(2025);
        serviceProvider.updateVendor(vendor);
    }


    @Then("I should see a confirmation message indicating the vendor's information has been updated successfully")
    public void iShouldSeeAConfirmationMessageIndicatingTheVendorSInformationHasBeenUpdatedSuccessfully() {
        Vendor updatedVendor = serviceProvider.findVendorByName(vendor.getName());
        assertEquals("Vendor's name should be updated", "New Catering Co", updatedVendor.getName());
        assertEquals("Vendor's service type should be updated", "Premium Catering", updatedVendor.getServiceType());
        assertEquals("Vendor's email should be updated", "newemail@example.com", updatedVendor.getEmail());
        assertEquals("Vendor's time should be updated", 12, updatedVendor.getTime());
        assertEquals("Vendor's date should be updated", 2025, updatedVendor.getDate());
    }


    @Given("I have selected an existing vendor to delete")
    public void iHaveSelectedAnExistingVendorToDelete() {


    }

    @When("I confirm the deletion of the vendor")
    public void iConfirmTheDeletionOfTheVendor() {
        serviceProvider.deleteVendor(vendor.getName());
    }

    @Then("I should see a confirmation message indicating the vendor has been deleted successfully")
    public void iShouldSeeAConfirmationMessageIndicatingTheVendorHasBeenDeletedSuccessfully() {

        assertFalse("Vendor should be deleted", serviceProvider.containsVendor(vendor));
    }

}
