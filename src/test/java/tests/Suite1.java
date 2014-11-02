package tests;

import junit.framework.Assert;
import lib.AbstractSuite;
import org.automation.dojo.DojoTestRunner;
import org.automation.dojo.ReportTo;
import org.automation.dojo.Scenario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import page.objects.ProductListPageObject;
import page.objects.SearchAppPageObject;
import steps.NavigationSteps;
import steps.ProductListSteps;

@RunWith(DojoTestRunner.class)
@ReportTo(server = AbstractSuite.SERVER, userName = AbstractSuite.USER_NAME)
public class Suite1 extends AbstractSuite {

    private WebDriver webDriver;
    private NavigationSteps navigationSteps;
    private ProductListSteps listSteps;

    final private static int SCENARIO_NUMBER = 1;


    @Before
    public void setUp() {
        //Please change false to true during dojo
        webDriver = new HtmlUnitDriver(true);
        initSteps();
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void scriptInjection() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "<script>alert('Hello, World!')</script>";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        //Then I see all products
        Assert.assertEquals(7, prodListPage.lookupProductListName().size());
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void productListShouldBeShown() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "1";

        //Given open page
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        final String expectedText = "List:";
        Assert.assertTrue(prodListPage.searchInfoContains(expectedText));

        Assert.assertTrue("Product list should be shown", prodListPage.productListTableIsDisplyed());
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void whenUserSearchesForEmptyStringThenAllResultsShouldBeShown() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        //Then I see all products
        Assert.assertEquals(7, prodListPage.lookupProductListName().size());
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void whenUserSearchesForEmptyStringThenMessageAndAllResultsShouldBeShown() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);

        final String keyWord = "aaaaaaggg23";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        final String expectedText = "Sorry no results for your request, but we have another devices:";
        Assert.assertTrue(prodListPage.searchInfoContains(expectedText));

        //Then I see all products
        Assert.assertEquals(7, prodListPage.lookupProductListName().size());
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void resultListShouldContainsKeyWord() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "Mouse";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        //Then all items contains [keyword]
        Assert.assertTrue(listSteps.itemsContainsKeyWord(prodListPage.lookupProductListName(), keyWord));
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void maxLengthIs200Symbols() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);

        //Given search box
        navigationSteps.openPage(APP_LINK);

        Assert.assertEquals("200", searchBoxPage.getMaxLength());
    }



    public void initSteps() {
        navigationSteps = new NavigationSteps(webDriver);
        listSteps = new ProductListSteps(webDriver);
    }
}
