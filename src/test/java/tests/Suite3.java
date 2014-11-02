package tests;

import junit.framework.Assert;
import lib.AbstractSuite;
import org.automation.dojo.DojoTestRunner;
import org.automation.dojo.ReportTo;
import org.automation.dojo.Scenario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import page.objects.ProductListPageObject;
import page.objects.SearchAppPageObject;
import steps.NavigationSteps;
import steps.ProductListSteps;

import java.util.List;

@RunWith(DojoTestRunner.class)
@ReportTo(server = AbstractSuite.SERVER, userName = AbstractSuite.USER_NAME)
public class Suite3 extends AbstractSuite {

    private WebDriver webDriver;
    private NavigationSteps navigationSteps;
    private ProductListSteps listSteps;

    final private static int SCENARIO_NUMBER = 3;


    @Before
    public void setUp() {
        //Please change false to true during dojo
        webDriver = new HtmlUnitDriver(true);
        initSteps();
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    //Search for a product with some keyword in name
    public void whenUserSelectSorting() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        final String keyWord = "2";
        //Given search box
        navigationSteps.openPage(APP_LINK);
        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        searchBoxPage.userSelectDescSorting();
        //Desc Should be selected
        Assert.assertEquals("descending", searchBoxPage.getSelectedPriceOption());
    }


    @Test
    @Scenario(SCENARIO_NUMBER)
    //Search for a product with some keyword in name
    public void whenClicksSortForNames() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "2";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();
        searchBoxPage.userSelectDescSorting();

        List<String> list = prodListPage.lookupProductListName();
        Assert.assertEquals(list.get(0), "'Monitor 2'");
        Assert.assertEquals(list.get(1), "'Mouse 2'");
    }

    public void initSteps() {
        navigationSteps = new NavigationSteps(webDriver);
        listSteps = new ProductListSteps(webDriver);
    }
}
