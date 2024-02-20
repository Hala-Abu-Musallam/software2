Feature: login
Scenario Outline: :vaild login
 #parameters:<>
  When username and password are true '<username>' '<password>'
  Then user succesfull loged in
  Examples:
    | username| password |
    |Hala     |1234hala  |


  Scenario Outline: invalid login
    When username or password is false '<username>' '<password>'
    Then user failed log in
    Examples:
      |username | password |
      |Rahaf    |1234rahaf |