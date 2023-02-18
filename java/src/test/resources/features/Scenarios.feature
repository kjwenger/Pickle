Feature: Scenario meta-data handling

  Scenario: Check scenario name
    When I set the scenario name as World object 'Scenario Name'
    Then I should get the World object 'Scenario Name' as 'Check scenario name'