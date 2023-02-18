Feature: Scenario meta-data handling

  Scenario Outline: Check scenario name
    When I set the scenario name as World object '<Set Key>'
    Then I should get the World object '<Get Key>' as 'Check scenario name'
    Examples:
      | Set Key                 | Get Key                 | Comment                                     |
      | String@string           | String@string           | Gotten as set                               |
      | String@string           | String@                 | Gotten by class only                        |
      | String@string           | string                  | Gotten by name only                         |
      | string                  | String@string           | Set by name only but gotten fully qualified |
      | string                  | String@                 | Set by name only but gotten by class only   |
      | string                  | string                  | Set by name only and gotten by name only    |
      | String@                 | String@                 | Set by class only and gotten by class only  |
      | String@_12345           | String@_12345           | Checked permitted name                      |
      | _12345                  | String@_12345           | Checked permitted name                      |
      | String@_12345           | _12345                  | Checked permitted name                      |
      | java.lang.String@string | java.lang.String@string | Checked fully qualified class               |
      | String@string           | java.lang.String@string | Checked fully qualified class at get        |
      | java.lang.String@string | String@string           | Checked fully qualified class at set        |
      | pickle.World$Key@_12345 | java.lang.String@_12345 | Checked for real class at set               |
