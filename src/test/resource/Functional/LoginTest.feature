Feature: Log into home.fedex.com



@LoginTest1 
Scenario: Log into Dock only pass
Given User on login page


       




@LoginTest 
Scenario Outline: Log into Dock & Yard successfully
Given User on login page
When User enters "<username>" and "<password>" and clicks on login button
Then User closes browser

Examples:
       |username  | password |
       |3795600   | 3795600  |
       |3795600   | 3791600  |