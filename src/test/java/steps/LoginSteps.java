    package steps;

    import Pages.DashBoardPage;
    import Pages.LoginPage;
    import io.cucumber.datatable.DataTable;
    import io.cucumber.java.en.Then;
    import io.cucumber.java.en.When;
    import org.junit.Assert;
    import utils.CommonMethods;
    import utils.ConfigReader;

    import java.util.List;
    import java.util.Map;

    public class LoginSteps extends CommonMethods {

        @When("user enters valid admin username and password")
        public void user_enters_valid_admin_username_and_password() {
            LoginPage loginPage;
            loginPage = new LoginPage();
            sendText( LoginPage.userNameBox, ConfigReader.getPropertyValue( "username" ) );
            sendText( LoginPage.passwordBox, ConfigReader.getPropertyValue( "password" ) );
        }

        @When("user clicks on login button")
        public void user_clicks_on_login_button() {
            LoginPage loginPage = new LoginPage();
            click( LoginPage.loginBtn );
        }

        @Then("admin user is successfully logged in")
        public void admin_user_is_successfully_logged_in() {
            DashBoardPage dash = new DashBoardPage();
            //  Assert.assertTrue(dash.welcome.isDisplayed());
            String expected = "Welcome Admin";
            String actual = dash.welcome.getText();
            Assert.assertEquals( "Values do not match", expected, actual );
        }

        @When("user enters valid ess username and password")
        public void user_enters_valid_ess_username_and_password() {
            LoginPage loginPage = new LoginPage();
            sendText( LoginPage.userNameBox, "johnny1234560000" );
            sendText( LoginPage.passwordBox, "Syntax1253!!!!" );
        }

        @Then("ess user is successfully logged in")
        public void ess_user_is_successfully_logged_in() {
            DashBoardPage dash = new DashBoardPage();
            Assert.assertTrue( dash.welcome.isDisplayed() );
        }

        @When("user enters valid username and invalid password")
        public void user_enters_valid_username_and_invalid_password() {
            LoginPage loginPage = new LoginPage();
            sendText( LoginPage.userNameBox, "Admin" );
            sendText( LoginPage.passwordBox, "Syntax1253!!!!" );
        }

        @Then("user see invalid credentials text on login page")
        public void user_see_invalid_credentials_text_on_login_page() {
            System.out.println( "Error message is displayed" );
        }

        @When("user enters {string} and {string}")
        public void user_enters_and(String username, String password) {
            LoginPage loginPage = new LoginPage();
            sendText( loginPage.userNameBox, username );
            sendText( loginPage.passwordBox, password );
        }

        @When("{string} is successfully logged in")
        public void is_successfully_logged_in(String firstname) {
            System.out.println( "Working fine" );
        }

            @When("user enters valid username and invalid password and verify the error")
            public void user_enters_valid_username_and_invalid_password_and_verify_the_error(DataTable errordata) {
                List<Map<String, String>> employeeNames = errordata.asMaps();
                for (Map<String, String> employeename : employeeNames) {
                    String usernameValue = employeename.get( "username" );
                    String passwordValue = employeename.get( "password" );
                    String errorValue = employeename.get( "errormessage" );

                    System.out.println( usernameValue + " " + passwordValue + " " + errorValue );

                    LoginPage loginPage = new LoginPage();
                    sendText( LoginPage.userNameBox, employeename.get( "username" ) );
                    sendText( LoginPage.passwordBox, employeename.get( "password" ) );
                    click( LoginPage.loginBtn );

                    String actual = LoginPage.errormessage.getText();
                    Assert.assertEquals( "Values do not match", employeename.get( "errormessage" ), actual );
                    System.out.println( "My test case is passed" );
                }
            }

            @When("user enters different {string} and {string} and verify the {string} for all the combinations")
            public void user_enters_different_and_and_verify_the_for_all_the_combinations (String usernamevalue, String
                    passwordvalue, String error){
                LoginPage loginPage = new LoginPage();
                sendText( LoginPage.userNameBox, usernamevalue );
                sendText( LoginPage.passwordBox, passwordvalue );
                click( LoginPage.loginBtn );
        //Junit only one hard assertion
                String actual = LoginPage.errormessage.getText();
                Assert.assertEquals( "Values do not match", error, actual );
            }
    }
