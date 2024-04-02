Feature: Show Calender

  Background: Admin
    Given Admin name is 'halakhaled@admin.com'

  Scenario: display calender
    When the event is earlier according to time and date '<date>' '<time>'
    Then sorting events in calender file
