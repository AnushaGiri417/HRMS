    package steps;

    import io.cucumber.java.en.Then;
    import utils.DbUtils;
    import utils.GlobalVariables;

    import java.util.List;
    import java.util.Map;

    public class dbSteps {
        @Then("query the HRMS database")
        public void query_the_hrms_database() {
            String  query = "select emp_firstname,emp_middle_name,emp_lastname "+
                    "from hs_hr_employees where emp_number=" + GlobalVariables.empID;
           List<Map<String , String >> tableDataAsList = DbUtils.getTableDataAsList( query );
           GlobalVariables.dbFirstName = tableDataAsList.get( 0 ).get( "emp_firstname" );
           GlobalVariables.dbMiddleName = tableDataAsList.get( 0 ).get( "emp_middle_name" );
           GlobalVariables.dbLastName = tableDataAsList.get( 0 ).get( "emp_lastname" );

        }

        @Then("query the HRMS database for job title")
        public void query_the_hrms_database_for_job_title() {
            String query1 = "select job_title, job_description from ohrm_job_title where job_title = "+GlobalVariables.jobTitle;
            List<Map<String , String >> tableDataAsList = DbUtils.getTableDataAsList( query1 );
            GlobalVariables.dbJobTitle = tableDataAsList.get( 0 ).get( "job_title" );
            GlobalVariables.dbJobDescription = tableDataAsList.get( 0 ).get( "job_description" );
          //  GlobalVariables.dbJobId = tableDataAsList.get( 0 ).get( "id" );

        }




//String query= "select job_title, job_description from ohrm_job_title" +
//        " where job_title="+ "'"+GlobalVariable.jobTitleValue+"'";









    }
