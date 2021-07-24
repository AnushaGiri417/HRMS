Feature: Modify employee details
 @testertag
  Scenario: select the employee from employee list and edit the employee details
    And user is logged in with valid admin credentials
    And user navigates to employee list page
    When user enters firstname "Anusha2025" and clicks on search button
    And user clicks on employee name and clicks edit button
   And user changes firstname to "Anusha2026" and clicks on save button
    Then user is able to successfully modify employee details
