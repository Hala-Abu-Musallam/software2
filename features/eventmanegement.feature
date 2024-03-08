
Feature:eventmanegment

Scenario: Service Provider creates a new event.

  Scenario: Managing Events as a Service Provider

    Given a service provider is logged in.
    When the service provider chooses to create a new event.
    Then the system should save the event details.






Scenario: Service Provider edits an existing event.

Given a service provider is logged in.
  When the service provider chooses to edit the event.
  Then the system should allow the service provider to modify event details such as event name, date, location, and services required.



  Scenario: Service Provider deletes an existing event.

    Given a service provider is logged in.
    When the service provider chooses to delete the event.
    Then the system should remove the event from the database.
