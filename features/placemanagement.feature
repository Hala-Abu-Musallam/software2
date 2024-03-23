Feature: Venue Management
  As a service provider
  I want to be able to create, edit, and delete my wedding venue


  Scenario 1: Creating a new wedding venue
    Given I am logged in as a service provider
    When I choose to create a new wedding venue
    Then I should be able to specify the venue's name, my name (as the owner), the location, and the asking price

  Scenario 2: Editing an existing wedding venue
    Given I have previously created a wedding venue
    When I choose to edit this venue
    Then I should be able to update the venue's name, location, and asking price

  Scenario 3: Deleting a wedding venue
    When I choose to delete this venue
    Then the venue should be removed from the system