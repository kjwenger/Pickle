Feature: World object

  Scenario Outline: World objects
    When I set the World object 'Test' to '<Set>'
    Then I should get the World object 'Test' as '<Get>'
    Examples:
      | Set  | Get  |
      | Test | Test |