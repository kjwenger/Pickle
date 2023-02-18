Feature: World object

  Scenario Outline: World objects
    When I set the World object '<Set Key>' to '<Set>'
    Then I should get the World object '<Get Key>' as '<Get>'
    Examples:
      | Set Key | Get Key | Set  | Get  |
      | Test    | Test    | Test | Test |
