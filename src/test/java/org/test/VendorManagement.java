package org.test;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.ServiceProvider;
import org.example.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VendorManagement {

        private ServiceProvider serviceProvider=new ServiceProvider();
        private Vendor vendor=new Vendor();





    @When("I choose to create a new vendor")
    public void iChooseToCreateANewVendor() {

    }
    @When("I enter the vendor's name, service type, and pricing information")
    public void iEnterTheVendorSNameServiceTypeAndPricingInformation() {


        vendor = new Vendor("vendor1", "Catering Co", "Catering", 1500.0);
        serviceProvider.addVendor(vendor);




    }

    @Then("I should see a confirmation message indicating the vendor has been created successfully")
    public void iShouldSeeAConfirmationMessageIndicatingTheVendorHasBeenCreatedSuccessfully() {
        assertTrue(serviceProvider.containsVendor(vendor));
    }






    @Given("I have selected an existing vendor to edit")
    public void iHaveSelectedAnExistingVendorToEdit() {
        // Assuming you have a method to select a vendor by ID or name
        vendor = serviceProvider.findVendorByName("Catering Co");
        assertTrue("Vendor should exist", vendor != null);
    }

    @When("I update the vendor's information such as name, service type, or pricing")
    public void iUpdateTheVendorSInformationSuchAsNameServiceTypeOrPricing() {
        // Assuming you have methods to update vendor details
        vendor.setName("New Catering Co");
        vendor.setServiceType("Premium Catering");
        vendor.setPricing(2000.0);
        serviceProvider.updateVendor(vendor);
    }

    @Then("I should see a confirmation message indicating the vendor's information has been updated successfully")
    public void iShouldSeeAConfirmationMessageIndicatingTheVendorSInformationHasBeenUpdatedSuccessfully() {
        Vendor updatedVendor = serviceProvider.findVendorById(vendor.getId());
        assertTrue("Vendor's name should be updated", "New Catering Co".equals(updatedVendor.getName()));
        assertTrue("Vendor's service type should be updated", "Premium Catering".equals(updatedVendor.getServiceType()));
        assertTrue("Vendor's pricing should be updated", 2000.0 == updatedVendor.getPricing());
    }

    @Given("I have selected an existing vendor to delete")
    public void iHaveSelectedAnExistingVendorToDelete() {
        // This could be similar to selecting a vendor to edit
        vendor = serviceProvider.findVendorByName("New Catering Co");
        assertTrue("Vendor should exist", vendor != null);
    }

    @When("I confirm the deletion of the vendor")
    public void iConfirmTheDeletionOfTheVendor() {
        serviceProvider.deleteVendor(vendor.getId());
    }

    @Then("I should see a confirmation message indicating the vendor has been deleted successfully")
    public void iShouldSeeAConfirmationMessageIndicatingTheVendorHasBeenDeletedSuccessfully() {
        assertFalse("Vendor should be deleted", serviceProvider.containsVendor(vendor));
    }

}
