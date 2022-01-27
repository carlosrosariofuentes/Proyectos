Feature: articles list
  he how user registed,  he need articles list

  Scenario: create article
    Given carlos is a user registed, and can created a article
    When Enter the article data
    Then article created

  Scenario: global feed
    Given carlos is a user registed, and can articles list
    When he makes a request
    Then List of articles


  Scenario: one article
    Given carlos is a user registed, and he need look a artilce
    When list of articles, choose the one from position zero
    Then article listed

  Scenario: delete
    Given  carlos is a user registed, and he need delete a artilce
    When   when you get the item, delete it
    Then  article delete

  Scenario: edit article
    Given carlos is a user registed, and he need edit a artilce
    When Enter the article data to edit
    Then article edited