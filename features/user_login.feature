Feature: login and sign up


  Scenario Outline: vaild login
  #parameters:<>

    When username and password are true '<username>' '<password>'

    Then user succesfull loged in
    Examples:
      | username         | password |
      |Hala@user.com     |1234hala  |
      |Rahaf@user.com    |1234rahaf |
      |mohammed@user.com |1234mohammed|



  Scenario Outline: invalid login
    When username or password is false '<username>' '<password>'
    Then user failed log in

    Examples:
      |username | password |
      | Hala    |0000      |
      |         |          |

    Scenario Outline: valid sign up
      When user provides valid information '<password>' '<email>'
      Then user succesfully signs up

      Examples:
      |password |email               |
      |hala1234 |halakhaled@admin.com|

  Scenario Outline: invalid sign up
    When user provides invalid information '<password>' '<email>'
    Then user failed to sign up

    Examples:
       |password |email               |
       |0000     |halakhaled.com      |
       |         |                    |



