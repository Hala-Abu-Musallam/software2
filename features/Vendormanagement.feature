Feature: Vendor Management
  As a service provider, I want to manage vendors so that I can keep track of catering, decorations, entertainment, etc., services.

  Scenario: Creating a New Vendor
    Given I am logged in as a service provider
    When I choose to create a new vendor
    And I enter the vendor's name, service type, and pricing information
    Then I should see a confirmation message indicating the vendor has been created successfully

  Scenario: Editing an Existing Vendor
    Given I am logged in as a service provider
    And I have selected an existing vendor to edit
    When I update the vendor's information such as name, service type, or pricing
    Then I should see a confirmation message indicating the vendor's information has been updated successfully

  Scenario: Deleting an Existing Vendor
    Given I am logged in as a service provider
    And I have selected an existing vendor to delete
    When I confirm the deletion of the vendor
    Then I should see a confirmation message indicating the vendor has been deleted successfully
