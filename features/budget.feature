Feature: Budget Planning

  Background: Admin
    Given user name is 'halakhaled@admin.com'

  Scenario Outline: display budget planner
    When admin choose display budget planner 'budget'
    Then display budget file
    Examples:
      |budget|
      |budget|