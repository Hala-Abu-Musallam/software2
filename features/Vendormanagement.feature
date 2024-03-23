Feature: Vendor Management
  As a service provider, I want to manage vendors so that I can keep track of catering, decorations, entertainment, etc., services.

  Scenario: Creating a New Vendor
    When I choose to create a new vendor
    Then I should see a confirmation message indicating the vendor has been created successfully

  Scenario: Editing an Existing Vendor
    When I have selected an existing vendor to edit
    When I update the vendor's information such as name, service type, or pricing


  Scenario: Deleting an Existing Vendor

    When I have selected an existing vendor to delete
    When I confirm the deletion of the vendor
    Then I should see a confirmation message indicating the vendor has been deleted successfully
