
Feature:eventmanegment

Scenario: Service Provider creates a new event.

Given a service provider is logged in.
When the service provider chooses to create a new event.
Then the system should save the event details.





Scenario: Service Provider edits an existing event.

Given a service provider is logged in.
And there exists an event that the service provider manages.
When the service provider chooses to edit the event.
Then the system should display the current event details.
And the system should allow the service provider to modify event details such as event name, date, location, and services required.
And the system should save the updated event details.



  Scenario: Service Provider deletes an existing event.

    Given a service provider is logged in.
    And there exists an event that the service provider manages.
    When the service provider chooses to delete the event.
    Then the system should confirm the service provider's intention to delete the event.
    And the system should remove the event from the database.
