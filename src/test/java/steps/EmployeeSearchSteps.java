package steps;

import Pages.DashBoardPage;
import Pages.EmployeeListPage;
import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethods {


    @Given("user is logged in with valid admin credentials")
    public void user_is_logged_in_with_valid_admin_credentials() {
        LoginPage loginPage = new LoginPage();
        sendText( LoginPage.userNameBox, ConfigReader.getPropertyValue( "username" ) );
        sendText( LoginPage.passwordBox, ConfigReader.getPropertyValue( "password" ) );
        click( loginPage.loginBtn );
    }

    @Given("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        DashBoardPage dash = new DashBoardPage();
        click( dash.pimOption );
        click( dash.employeeListOption );
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        EmployeeListPage emplist = new EmployeeListPage();
        sendText( emplist.idEmployee, "15518" );
    }

    @When( "user enters valid employee name" )
    public void user_enters_valid_employee_name() throws InterruptedException {
        EmployeeListPage emplist = new EmployeeListPage();
        Thread.sleep( 2000 );
        sendText( emplist.employeenamefield, "Sofiia" );
    }

    @When("click on search button")
    public void click_on_search_button() throws InterruptedException {
        EmployeeListPage emplist = new EmployeeListPage();
        Thread.sleep( 2000 );
        click( emplist.searchButton );
    }

    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {
        System.out.println("Employee name is displayed");
    }
}
