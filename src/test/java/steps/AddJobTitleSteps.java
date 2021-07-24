    package steps;

    import Pages.AddJobTitlePage;
    import io.cucumber.java.en.Then;
    import io.cucumber.java.en.When;
    import utils.CommonMethods;
    import utils.GlobalVariables;

    public class AddJobTitleSteps extends CommonMethods {

        @When("user navigates to add job title page and clicks on add button")
        public void user_navigates_to_add_job_title_page_and_clicks_on_add_button() {
            AddJobTitlePage addJobPage = new AddJobTitlePage();
            click( addJobPage.AdminBtn );
            click( addJobPage.JobBtn );
            click( addJobPage.jobTitleBtn );
            click( addJobPage.AddBtn );

        }


        @When("user enters job title {string} and description {string}")
        public void user_enters_job_title_and_description(String job_title, String job_description) {
            AddJobTitlePage addJobPage = new AddJobTitlePage();
            sendText( addJobPage.jobTitleBox, job_title );
            sendText( addJobPage.jobDescriptionBox, job_description);
            GlobalVariables.jobTitle = job_title;
            GlobalVariables.jobDescription = job_description;
            GlobalVariables.dbJobTitle = job_title;
            GlobalVariables.dbJobDescription = job_description;
        }


        @When("capture the job title and clicks save button")
        public void capture_the_job_title_and_clicks_save_button() {
            AddJobTitlePage addJobPage = new AddJobTitlePage();
           GlobalVariables.jobTitle = addJobPage.jobTitleBox.getAttribute( "value" );
            click( addJobPage.saveBtn );
        }


        @Then("verify job title from frontend")
        public void verify_job_title_from_frontend() {
            System.out.println("Backend:");
            System.out.println("Job Title : "+GlobalVariables.dbJobTitle);
            System.out.println("Job Description : "+GlobalVariables.dbJobDescription);
            System.out.println("Frontend:");
            System.out.println("Job Title : "+GlobalVariables.dbJobTitle);
            System.out.println("Job Description : "+GlobalVariables.dbJobDescription);
        }


    }
