    package Pages;

    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;
    import utils.CommonMethods;

    public class AddJobTitlePage extends CommonMethods {

        @FindBy(id = "menu_admin_viewAdminModule")
        public WebElement AdminBtn;
        @FindBy(id = "menu_admin_Job")
        public WebElement JobBtn;
        @FindBy(id = "menu_admin_viewJobTitleList")
        public WebElement jobTitleBtn;
        @FindBy(id = "btnAdd")
        public WebElement AddBtn;
        @FindBy(id = "jobTitle_jobTitle")
        public WebElement jobTitleBox;
        @FindBy(id = "jobTitle_jobDescription")
        public WebElement jobDescriptionBox;
        @FindBy(id = "btnSave")
        public WebElement saveBtn;

        public AddJobTitlePage(){
            PageFactory.initElements( driver, this );
        }
    }
