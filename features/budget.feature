Feature: Budget Planning

  Background: Admin
    Given user name is 'halakhaled@admin.com'

  Scenario: display budget planner
    When admin choose display budget planner 'budget'
    Then display budget file
