package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeListPage extends CommonMethods {

    @FindBy(id="empsearch_id")
    public WebElement idEmployee;

    @FindBy(id="searchBtn")
    public WebElement searchButton;

    @FindBy(id="empsearch_employee_name_empName")
    public WebElement employeenamefield;

    @FindBy(xpath = "//div[@id='tableWrapper']/table/tbody/tr/td[3]")
    public WebElement employeename;

    @FindBy(id="btnSave")
    public WebElement editButton;

    @FindBy(id="personal_txtEmpFirstName")
    public WebElement FirstName;

    @FindBy(id="btnSave")
     public WebElement saveButton;

    @FindBy(xpath = "//div[@id='profile-pic']/h1")
    public WebElement employeeDetails;

    public EmployeeListPage(){
        PageFactory.initElements(driver,this);
    }

}
