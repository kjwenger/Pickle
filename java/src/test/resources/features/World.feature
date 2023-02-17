Feature: World object

  Scenario Outline: World objects
    Given I have a World object
    When I set the object named 'Test' to '<Set>'
    Then I should get the object named 'Test' as '<Get>'
    Examples:
    | Set  | Get  |
    | Test | Test |