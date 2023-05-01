Feature: Trello API test feature

  Scenario: Add new list to the test board after changing
    Given The board exists and contains the correct information
    When I change the board title to "New Title"
    And I check that board name was updated to "New Title"
    Then I add a list with a name "Example list" to the board