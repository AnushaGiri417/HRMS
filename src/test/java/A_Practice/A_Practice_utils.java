package A_Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;

public class A_Practice_utils {
    public static void main(String[] args) throws SQLException {
        //create a connection
        Connection connection = DriverManager.getConnection( "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql","syntax_hrm","syntaxhrm123" );
        WebDriver driver = new ChromeDriver();
        driver.get( "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login" );

        //create statement/query
            Statement statement = connection.createStatement();
            String query = "select id, firstName, lastName from person;";
            ResultSet resultSet = statement.executeQuery( query );
            while(resultSet.next()){
               int id = resultSet.getInt( "id" );
               String firstname = resultSet.getString( "firstName" );
               String lastName = resultSet.getString( "lastName" );
                System.out.println(id+" "+firstname+" "+lastName);
            }
           connection.close();
        System.out.println("Query executed");


    }
}
