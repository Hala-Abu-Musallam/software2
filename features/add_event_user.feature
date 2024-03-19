Feature: add event by user

  Scenario :add succeed
    When Date and Time are available '<Date>''<Time>'
    Then add event to waiting list

  Scenario : add failed
    When Date or Time is unavailable or wrong '<Date>''<Time>'
    Then failed to add event