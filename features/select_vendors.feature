Feature: Select Vendors

Background: vendors
  Given date is '15/7/2024' and time is'6-8'

  Scenario Outline: successfully add vendors
    When user choose vendors for'<decoration>''<DJ>''<photographer>'
    Then book vendor to the event
    Examples:
      | decoration                    | DJ                   | photographer                   |
      |decoration@serviceprovider.com |DJ@serviceprovider.com|photographer@serviceprovider.com|

