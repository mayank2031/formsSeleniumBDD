Feature: Form Testing
  Testing various form elements on the TipTop web application

  Background:
    Given I navigate to the form page

  Scenario: Verify disabled input field
    Then the disabled input field should be disabled

  Scenario: Verify readonly input field
    Then the readonly input field should be readonly

  Scenario: Verify color dropdown options
    Then the color dropdown should have 8 options

  Scenario: Verify submit button is disabled when name is empty
    Then the submit button should be disabled

  Scenario: Verify submit button is enabled when required fields are filled
    When I enter "TestUser" in the name field
    And I enter "Password123" in the password field
    Then the submit button should be enabled

  Scenario: Verify received text is shown after form submission
    When I enter "TestUser" in the name field
    And I enter "Password123" in the password field
    And I click the submit button
    Then the page should show "Received" text

  Scenario: Verify form data is passed to URL
    When I enter "TestUser" in the name field
    And I enter "Password123" in the password field
    And I select "blue" color from the dropdown
    And I click the submit button
    Then all the data should be passed to the URL