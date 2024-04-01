Feature: show calender

  Background: Admin
    Given Admin name is 'halakhaled@admin.com'

  Scenario: display calender
    When admin choose display calender 'calender'
    Then display calender file
