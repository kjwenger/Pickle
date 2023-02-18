Feature: World object

  Scenario Outline: World objects
    When I set the World object 'Test' to '<Set>'
    Then I should get the World object 'Test' as '<Get>'
    Examples:
      | Set  | Get  |
      | Test | Test |

  Scenario Outline: World keys
    Then I should get the class name '<Class Name>' and the object name '<Object Name>' for key '<Key>'
    Examples:
      | Class Name       | Object Name | Key                     |
      | java.lang.String | string      | String@string           |
      | java.lang.String | _12345      | String@_12345           |
      | java.lang.String | string      | java.lang.String@string |
      | java.lang.String | <NULL>      | String@                 |
      | <NULL>           | string      | string                  |
      | <NULL>           | <NULL>      | @                       |
      | pickle.World$Key | _12345      | pickle.World$Key@_12345 |
