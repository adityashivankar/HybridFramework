Feature: Verify sign in scenarios

  @PositiveScenario
  Scenario Outline: Verify that user is able to navigate to the sign in page
    Given User navigates to the Ironclad home page
    When User click on the Sign In button
    Then Verify that "<SignInText>" is displayed on the Sign In page

    Examples: 
      | SignInText           |
      | Sign in to Ironclade |

  @NegativeScenario
  Scenario Outline: Verify that proper error message is displayed for the invalid email ID
    Given User navigates to the Ironclad home page
    When User click on the Sign In button
    And User enters invalid "<emailID>"
    And User click on the continue button
    Then Verify that "<errorMessageText>" is displayed

    Examples: 
      | emailID           | errorMessageText                  |
      | ironclad@ironclad | This is not a valid email address |
      |                   | This is not a valid email address |
