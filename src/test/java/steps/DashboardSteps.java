package steps;

import Pages.DashBoardPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("verify the following tabs on dashboard")
    public void verify_the_following_tabs_on_dashboard(DataTable dataTable) {

        List<String > expectedtabs = dataTable.asList();

        DashBoardPage dash = new DashBoardPage();
        List<String > actualtabs = new ArrayList<>();
        for(WebElement ele: dash.dashboardtabs){
            actualtabs.add( ele.getText() );
        }
        System.out.println(expectedtabs);
        System.out.println(actualtabs);

        Assert.assertEquals( expectedtabs, actualtabs );
        System.out.println("My test case is passed");
    }

}
