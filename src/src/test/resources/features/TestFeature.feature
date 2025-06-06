Feature: Google Search

  Scenario Outline: Search in Google
    Given Google page opens
    When User search for "<SearchText>"
    Then Search result is displayed containing "<SearchText>"

    Examples:
      | SearchText            |
      | Java Design Pattern   |
      | Software Architecture |
