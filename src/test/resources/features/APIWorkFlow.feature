#Author :  Anusha
  Feature: Syntax HRMS API Workflow
    Description: This feature files tests Syntax HRMS API Workflow

  # Scenarios are used to describe business rules/requiremnts or the behavior that user will take on an application
  # Example: Give a user is on the home page, When the user logs in, Then user sees landing page (Not best Way in Industry)
  # TDD : Recommended way
    Background:
    Given  a JWT is generated

    @APIWorkflow
      Scenario: Creating an Employee
          Given a request is prepared to create an employee
          When a POST call is made to create an employee
          Then the status code for creating an employee is 201
          And the employee created contains key "Message" and value "Employee Created"
          And the employeeID "Employee.employee_id" is stored as a global variable to be used for other calls

    @APIWorkflow
  Scenario: Retreiving created employee
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the created employee
    Then the status code for retrieving the created employee is 200
    And the retrieved employee ID "employee.employee_id" matches the globally stored employee ID
    And the retrieved data at "employee" matches the data used to create an employee with employee ID "employee.employee_id"
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title | emp_status |
      | AnushaSyntax        | hrms          | API   | 2021-07-10   | Female     | Cloud Consultant          | Employee   |