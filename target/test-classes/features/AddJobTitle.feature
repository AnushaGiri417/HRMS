Feature: Add job title
  Background:
    And user is logged in with valid admin credentials
 @jobTitle
  Scenario: Admin can add employee job title
    When user navigates to add job title page and clicks on add button
    And user enters job title "SDET" and description "tests application-Manual and automated"
    And capture the job title and clicks save button
    Then query the HRMS database for job title
    And verify job title from frontend
