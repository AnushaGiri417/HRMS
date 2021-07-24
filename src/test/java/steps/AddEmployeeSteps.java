package steps;

import Pages.AddEmployeePage;
import Pages.DashBoardPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;
import utils.GlobalVariables;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.pimOption);
    }
    @When("user clicks on Add employee button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.addEmployeeButton);
    }
    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, "Nelson1234");
        sendText(add.middleName, "MS");
        sendText(add.lastName, "MS1234");
    }
    @When("user enters first name {string} middle name {string} and last name {string}")
    public void user_enters_first_name_middle_name_and_last_name(String firstName, String middleName, String lastName) {
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, firstName);
        sendText(add.middleName, middleName);
        sendText(add.lastName, lastName);
        GlobalVariables.firstName = firstName;
        GlobalVariables.middleName = middleName;
        GlobalVariables.lastName = lastName;
    }
    @When("user enters {string} {string} and {string} in the application")
    public void user_enters_and_in_the_application(String FirstName, String MiddleName, String LastName) {
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, FirstName);
        sendText(add.middleName, MiddleName);
        sendText(add.lastName, LastName);
    }
    @When("user clicks on save button option")
    public void user_clicks_on_save_button_option() {
        AddEmployeePage add = new AddEmployeePage();
        click(add.saveBtn);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("add multiple employee and verify they are added successfully")
    public void add_multiple_employee_and_verify_they_are_added_successfully(DataTable employees) throws InterruptedException {

        List<Map<String, String>> employeeNames = employees.asMaps();
            for(Map<String,String > employeename : employeeNames){
                String firstnameValue = employeename.get( "Firstname" );
                String middlenameValue = employeename.get( "Middlename" );
                String lastnameValue = employeename.get( "lastname" );

                System.out.println(firstnameValue+" "+middlenameValue+" "+lastnameValue );

                AddEmployeePage addEmployeePage = new AddEmployeePage();
                sendText(addEmployeePage.firstName, firstnameValue);
                sendText(addEmployeePage.middleName, middlenameValue);
                sendText(addEmployeePage.lastName, lastnameValue);
                click( addEmployeePage.saveBtn );

                //Assert
                Thread.sleep( 2000 );
                DashBoardPage dash = new DashBoardPage();
                click( dash.addEmployeeButton );
        }
    }

    @When("user adds multiple employees from excel file from {string} sheet and verify they are added")
    public void user_adds_multiple_employees_from_excel_file_from_sheet_and_verify_they_are_added(String sheetname) throws InterruptedException {
            List<Map<String,String >> newemployees = ExcelReading.excelIntoListMap( Constants.TESTDATA_FILEPATH, sheetname );

            DashBoardPage dash = new DashBoardPage();
            AddEmployeePage addEmployeePage = new AddEmployeePage();

        Iterator<Map<String ,String >> itr = newemployees.iterator();
        while (itr.hasNext()){
            Map<String,String> mapNewEmp = itr.next();
            sendText( addEmployeePage.firstName, mapNewEmp.get( "Firstname" ) );
            sendText( addEmployeePage.middleName, mapNewEmp.get( "Middlename" ) );
            sendText( addEmployeePage.lastName, mapNewEmp.get( "Lastname" ) );
            click( addEmployeePage.saveBtn );
            Thread.sleep( 2000 );
            //Assert
        }
    }

    @When("capture the employeeID")
    public void capture_the_employee_id() {
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        GlobalVariables.empID = addEmployeePage.employeeId.getAttribute( "value" );
    }

    @Then("verify the data from frontend and backend")
    public void verify_the_data_from_frontend_and_backend() {
        System.out.println("Backend");
        System.out.println("DBFirstname"+" "+GlobalVariables.dbFirstName);
        System.out.println("DBMiddlename"+" "+GlobalVariables.dbMiddleName);
        System.out.println("DBLastname"+" "+GlobalVariables.dbLastName);
        System.out.println("Frontend");
        System.out.println("DBFirstname"+" "+GlobalVariables.firstName);
        System.out.println("DBMiddlename"+" "+GlobalVariables.middleName);
        System.out.println("DBLastname"+" "+GlobalVariables.lastName);

        Assert.assertEquals( GlobalVariables.dbFirstName, GlobalVariables.firstName );
        Assert.assertEquals( GlobalVariables.dbMiddleName, GlobalVariables.middleName );
        Assert.assertEquals( GlobalVariables.dbLastName, GlobalVariables.lastName );
    }



}
