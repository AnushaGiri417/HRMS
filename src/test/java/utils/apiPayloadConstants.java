package utils;

public class apiPayloadConstants {


   public static String createEmployeePayload(){
       String createEmployeePayload = "{\n" +
               "  \"emp_firstname\": \"AnushaSyntax\",\n" +
               "  \"emp_lastname\": \"API\",\n" +
               "  \"emp_middle_name\": \"hrms\",\n" +
               "  \"emp_gender\": \"F\",\n" +
               "  \"emp_birthday\": \"2021-07-10\",\n" +
               "  \"emp_status\": \"Employee\",\n" +
               "  \"emp_job_title\": \"Cloud Consultant\"\n" +
               "}";
       return createEmployeePayload;
   }


}
