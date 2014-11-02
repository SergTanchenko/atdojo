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
public class Suite2 extends AbstractSuite {

    private WebDriver webDriver;
    private NavigationSteps navigationSteps;
    private ProductListSteps listSteps;

    final private static int SCENARIO_NUMBER = 2;

    @Before
    public void setUp() {
        //Please change false to true during dojo
        webDriver = new HtmlUnitDriver(true);
        initSteps();
    }

    @Test
    @Scenario(SCENARIO_NUMBER)
    public void whenUserEnterLessThan() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "2";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();
        searchBoxPage.userSelectLessThan();
        searchBoxPage.userTypesPrice("50");
        searchBoxPage.userClickSearchButton();

        List<String> list = prodListPage.lookupProductListName();
        List<String> listPrice = prodListPage.lookupProductListPrice();
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(1, listPrice.size());

        //Assert.assertEquals(list.get(0), "'Mouse 1'");
        Assert.assertEquals(list.get(0), "'Mouse 2'");

        //Assert.assertEquals(listPrice.get(0), "30.0$");
        Assert.assertEquals(listPrice.get(0), "50.0$");
    }


    @Test
    @Scenario(SCENARIO_NUMBER)
    public void whenUserEnterMoreThan() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);

        final String keyWord = "2";

        //Given search box
        navigationSteps.openPage(APP_LINK);

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        searchBoxPage.userSelectMoreThan();
        searchBoxPage.userTypesPrice("60");
        searchBoxPage.userClickSearchButton();

        List<String> listName = prodListPage.lookupProductListName();
        List<String> listPrice = prodListPage.lookupProductListPrice();

        Assert.assertEquals(1, listName.size());
        Assert.assertEquals(1, listPrice.size());
        Assert.assertEquals(listName.get(0), "'Monitor 2'");
        Assert.assertEquals(listPrice.get(0), "120.0$");
    }


    @Test
    @Scenario(SCENARIO_NUMBER)
    public void whenSelectsLessOrMoreThan() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        final String keyWord = "2";
        //Given search box
        navigationSteps.openPage(APP_LINK);
        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();
        searchBoxPage.userSelectLessThan();
        //'less than' Should be selected
        Assert.assertEquals("less than", searchBoxPage.getSelectedMoreLessOption());
        searchBoxPage.userSelectMoreThan();
        //'more than' Should be selected
        Assert.assertEquals("more than", searchBoxPage.getSelectedMoreLessOption());
    }


    @Test
    @Scenario(SCENARIO_NUMBER)
    public void validationInfoIsDisplyed() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        final String keyWord = "qweqwrwe";
        //Given search box
        navigationSteps.openPage(APP_LINK);
        //When search by [keyword]
        searchBoxPage.userTypesPrice(keyWord);
        searchBoxPage.userClickSearchButton();
        searchBoxPage.userSelectLessThan();
        Assert.assertTrue(searchBoxPage.validationInfoIsDisplyed());
        Assert.assertTrue(searchBoxPage.searchBtnIsDispalyed());
    }




    public void initSteps() {
        navigationSteps = new NavigationSteps(webDriver);
        listSteps = new ProductListSteps(webDriver);
    }
}
