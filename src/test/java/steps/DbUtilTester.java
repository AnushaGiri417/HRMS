package steps;

import utils.DbUtils;

import java.util.List;
import java.util.Map;

public class DbUtilTester {
    public static void main(String[] args) {

        List<Map<String , String >> tableListData = DbUtils.getTableDataAsList("select emp_firstname,emp_middle_name,emp_lastname from hs_hr_employees" +
                " where emp_number='17551'");
        System.out.println(tableListData);

        List<Map<String, String>> tableListDataJobTitle = DbUtils.getTableDataAsList( "select job_title, job_description from ohrm_job_title where job_title = 'SDET';" );
        System.out.println(tableListDataJobTitle);




    }
}
