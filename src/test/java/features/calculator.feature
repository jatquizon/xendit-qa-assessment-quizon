Feature: Test online calculator scenarios
  Scenario Outline: Test addition, subtraction, and division functionalities
    Given Open chrome browser and start application
    When I enter following values and press the Equals button
      | value1 | <value1>|
      | value2 | <value2>|
      | operator | <operator>|
    Then I should be able to see
      |	expected |<expected>|
    Examples:
      | value1     | value2      | operator | expected   |
      | 2          | 2           | /		| 1		     |
      | 356        | 0           | /        | Error      |
      | -87349     | 1           | /	    | -87349     |
      | 0          | 248         | /	    | 0		     |
      | 435567.57  | 84          | /	    | 5185.32821 |
      | 544        | 51.3        | /        | 10.6042885 |
      | 1          | 999999999   | /        | 1e-9       |
      | 5          | 7           | +		| 12         |
      | 0          | 587         | +        | 587        |
      | 635        | 0           | +        | 635        |
      | -1000      | 1           | +	    | -999       |
      | 999999998  | 1           | +	    | 999999999  |
      | 345.76     | 73          | +	    | 481.76     |
      | 879.1      | 21.356      | +        | 900.636    |
      | 222.44     | 777.56      | +        | 1000       |
      | 999999999  | 999999999   | +        | 2e+9       |
      | 999999999  | 1           | +		| 1e+9	     |
      | 10         | 7           | -		| 3          |
      | 7          | 10          | -		| -3         |
      | 0          | 1           | -		| -1         |
      | 1          | 0           | -		| 1          |
      | -1000      | 1           | -	    | -1001      |
      | 1          | 999999999   | -	    | -999999998 |
      | 512.23     | 84          | -	    | 428.23     |
      | 879.37     | 0.37        | -        | 879        |
      | 1111.11    | 1111.11     | -        | 0          |
      | 999999999  | 999999999   | -        | 0          |
      | 999999999  | 1           | -		| 999999998  |