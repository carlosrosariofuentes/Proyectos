Feature: login user
  he  as user registed, and  i am need  log in

  Scenario Outline: login
    Given carlos is a user registed  and can login
    When he send the credencials of acces "<email>" and "<password>"
    Then he access to the page
    Examples:
      | email                 | password |
      | charlyrosariofuentes@ | 12345678 |




