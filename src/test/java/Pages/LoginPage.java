package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class
LoginPage extends CommonMethods {

    @FindBy(id = "txtUsername")
    public static WebElement userNameBox;

    @FindBy(id = "txtPassword")
    public static WebElement passwordBox;

    @FindBy(id = "btnLogin")
    public static WebElement loginBtn;

    @FindBy(id="spanMessage")
    public static WebElement errormessage;

//initialize elements using pageFactory by creating a constructor

    public LoginPage(){
        PageFactory.initElements( driver, this ); // all the variables are initialized by 'driver' and 'this' means elements in this class
    }

    public void login(String username, String password) {
        sendText(userNameBox, username);
        sendText(passwordBox, password);
        click(loginBtn);
    }


}
