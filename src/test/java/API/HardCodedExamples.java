package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

    /**
     * Rest Assured is DSL (Domain Specific Language)
     * Its a Java Library used to automate REST APIs
     * Rest Assured follows a behaviour driven development (BDD) approach
     * Given-Preparing a request
     * When- making the request / making the call / hitting the endpoint
     * Then- verifications / assertions
     *
     * BDD is designed to test an application's behavior from the end user's standpoint,
     * whereas TDD is focused on testing smaller pieces of functionality in isolation
     *
     * Principles of Rest Assured???
     * ---> Follows BDD approach
     * ----> Need to make some imports manually
     * ----> BaseURI is same as the BaseURL
     */
 @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class HardCodedExamples {


       String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
       String token ="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjY2MTc5NjcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYyNjY2MTE2NywidXNlcklkIjoiMjg1NyJ9.cnPGpKXPuJvsYwYEv8jkBqNhPP_A3zQEfcQStHF8UJg";
        static String employee_id;

       //   @Test
        public void sampleTest() {
            RequestSpecification preparedRequest = given().header( "Authorization", token )
                                                                         .header( "Content-Type", "application/json" ).queryParam( "employee_id", "21204A" );
            Response response = preparedRequest.when().get( "/getOneEmployee.php" );
            /*
             * Printing response using aString() method to convert JSON object to a String
             * and printing using System.Out.Println()
             */
            System.out.println( response.asString() );
        }

        @Test
        public void apostCreateEmployee() {
            RequestSpecification preparedRequest = given().header( "Authorization", token ).header( "Content-Type", "application/json" )
                    .body("{\n" +
                            "  \"emp_firstname\": \"AnushaSyntax\",\n" +
                            "  \"emp_lastname\": \"API\",\n" +
                            "  \"emp_middle_name\": \"hrms\",\n" +
                            "  \"emp_gender\": \"F\",\n" +
                            "  \"emp_birthday\": \"2021-07-10\",\n" +
                            "  \"emp_status\": \"Employee\",\n" +
                            "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                            "}"); //.log().all();
            /*
             * log().all() will log and print all information being sent with the request
             */
            Response response = preparedRequest.when().post("/createEmployee.php");
        //   prettyPrint() does the same as System.out.println(response.asString());
        //    response.prettyPrint();
            //jsonPath() ALLOWS UST O RETREIVE SPECIFIC DATA FROM A JSON OBJECT - JUST LIKE AN XPATH WITH SELENIUM
          employee_id =  response.jsonPath().getString( "Employee.employee_id" );
         //   System.out.println(employee_id);

            //PERFORMING ASSERTIONS:
            response.then().assertThat().statusCode( 201 ); //if  passed doesn't print anything in console. If failed, it will throw assertion error

            //using Hamcrest Matchers class equalTo()
            response.then().assertThat().body( "Message", equalTo("Employee Created") );

            //assert that the response body has the name of emoloyee you created
            response.then().assertThat().body( "Employee.emp_firstname", equalTo("AnushaSyntax") );

            //verifying server
            response.then().assertThat().header( "Server","Apache/2.4.39 (Win64) PHP/7.2.18" );
        }

       @Test
        public void bgetCreatedEmployee(){

            RequestSpecification preparedRequest = given().header( "Authorization", token ).header( "Content-Type", "application/json" ).queryParam( "employee_id", employee_id );
            Response response = preparedRequest.when().get("/getOneEmployee.php");
            response.prettyPrint();

          String empID =  response.jsonPath().getString("employee.employee_id");
          boolean comparingEmpIDs = empID.contentEquals( employee_id );
            Assert.assertTrue(comparingEmpIDs);

 //Task: Retreive the first name and assert that the firstname is the name of  the employee you created
 //     DO NOT USE HAMCREST MATCHERS
            String firstName=response.jsonPath().getString("employee.emp_firstname");
            Assert.assertTrue(firstName.contentEquals("AnushaSyntax"));

        }

     //   @Test
        public void cgetAllEmployees(){
            RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json");
            Response response = preparedRequest.when().get("/getAllEmployees.php");
            String allEmployees = response.prettyPrint();

            //Creating object of JsonPath class
            JsonPath js = new JsonPath(allEmployees);

            int count = js.getInt( "Employees.size()" );
            System.out.println(count);

    //PRINT ALL EMPLOYEE ID FROM THE RESPONSE
            for(int i = 0; i<count; i++){
                String employeeIDs = js.getString( "Employees["+ i + "].employee_id" );
                System.out.println(employeeIDs);

 //Verify stored employee ID from previous call is in response body
                if(employeeIDs.contentEquals( employee_id )){
   //printing out the employee ID
                    System.out.println("Employee ID " + employee_id + " is present in response body");
                    String firstName = js.getString("Employees["+ i + "].emp_firstname");
       // Printing out the first name of the employee that we created
                    System.out.println("Employee name is  "+ firstName);
                    break;
                }
            }
        }

        @Test
        public void dUpdateEmployee(){

            RequestSpecification preparedRequest = given().header("Authorization", token)
                    .header("Content-Type", "application/json").body("{\n" + "  \"employee_id\": \"" + employee_id + "\",\n"
                            + "  \"emp_firstname\": \"Anusha\",\n"
                            + "  \"emp_lastname\": \"myLastname\",\n"
                            + "  \"emp_middle_name\": \"noMiddlename\",\n" + "  \"emp_gender\": \"F\",\n"
                            + "  \"emp_birthday\": \"2000-07-11\",\n" + "  \"emp_status\": \"Employee\",\n"
                            + "  \"emp_job_title\": \"ScrumMaster\"\n" + "}");

            Response response = preparedRequest.when().put("/updateEmployee.php");
            response.prettyPrint();
            ValidatableResponse checkMessage = response.then().assertThat().body( "Message", equalTo( "Employee record Updated" ) );
            ValidatableResponse checkFirstname = response.then().assertThat().body( "emp_firstname", equalTo("Anusha") );
        }



    }
