package tests;

import junit.framework.Assert;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(DojoTestRunner.class)
@ReportTo(server = "http://127.0.0.1:8080", userName = "Serhii")
public class SampleAutomationTest {
    private WebDriver webDriver;
    private NavigationSteps navigationSteps;
    private ProductListSteps listSteps;



    @Before
    public void setUp() {
        //Please change false to true during dojo
        webDriver = new HtmlUnitDriver(false);
        initSteps();
    }




    @Test
    @Scenario(1)
    //Search for a product with some keyword in name
    public void whenUserSearchesForProduct() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "1";

        //Given search box
        navigationSteps.openPage("http://127.0.0.1:8080/search");

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        final String expectedText = "List:";
        Assert.assertTrue(prodListPage.searchInfoContains(expectedText));

        Assert.assertTrue("Product list should be shown", prodListPage.productListTableIsDisplyed());
    }

    @Test
    @Scenario(1)
    //Search for a product with some keyword in name
    public void resultListShouldContainsKeyWord() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "Mouse";

        //Given search box
        navigationSteps.openPage("http://127.0.0.1:8080/search");

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        //Then all items contains [keyword]
        listSteps.itemsContainsKeyWord(prodListPage.lookupProductList(), keyWord);
    }

    @Test
     @Scenario(1)
     //Search for a product with some keyword in name
     public void whenUserSearchesForEmptyStringThenAllResultsShouldBeShown() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);
        final String keyWord = "";

        //Given search box
        navigationSteps.openPage("http://127.0.0.1:8080/search");

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        //Then I see all products
        Assert.assertEquals(7, prodListPage.lookupProductList().size());
    }

    @Test
    @Scenario(1)
    //Search for a product with some keyword in name
    public void whenUserSearchesForEmptyStringThenMessageAndAllResultsShouldBeShown() {
        SearchAppPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchAppPageObject.class);
        ProductListPageObject prodListPage = PageFactory.initElements(webDriver, ProductListPageObject.class);


        final String keyWord = "aaaaaaggg23";

        //Given search box
        navigationSteps.openPage("http://127.0.0.1:8080/search");

        //When search by [keyword]
        searchBoxPage.userTypesQuery(keyWord);
        searchBoxPage.userClickSearchButton();

        final String expectedText = "Sorry no results for your request, but we have another devices:";
        Assert.assertTrue(prodListPage.searchInfoContains(expectedText));

        //Then I see all products
        Assert.assertEquals(7, prodListPage.lookupProductList().size());
    }


//    @Test
//    @Scenario(1)
//    public void searchForItem() {
//        SearchBoxPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchBoxPageObject.class);
//        //Given open google.com
//        navigationSteps.openPage("http://google.com");
//
//        // When I search by "automated testing dojo"
//        searchBoxPage.searchBy("automated testing dojo idea");
//
//        //Then I see a link to Sergey's blog
//        assertContainsLink("szelenin.blogspot.com");
//    }
//
//    @Test
//    @Scenario(1)
//    public void shouldBeAbleToClick() {
//        SearchBoxPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchBoxPageObject.class);
//        //Given open google.com
//        navigationSteps.openPage("http://google.com");
//
//        // When I search by "automated testing dojo"
//        searchBoxPage.searchBy("automated testing dojo idea");
//
//        // When I click on link for Sergey's blog
//        clickOn("szelenin.blogspot.com");
//
//        //Then Sergey's blog is open
//        assertWeAreAt("http://szelenin.blogspot.com");
//    }
//
//    private void assertWeAreAt(String linkAddress) {
//        assertTrue(webDriver.getCurrentUrl().contains(linkAddress));
//    }
//
//    private void clickOn(String linkAddress) {
//        List<WebElement> links = findLinks(linkAddress);
//        links.get(0).click();
//    }
//
//
//
//    private void assertContainsLink(String address) {
//        assertFalse(findLinks(address).isEmpty());
//    }
//
//    private List<WebElement> findLinks(String address) {
//        return webDriver.findElements(By.xpath("//a[contains(@href, '" + address + "')]"));
//    }

    private void initSteps() {
        navigationSteps = new NavigationSteps(webDriver);
        listSteps = new ProductListSteps(webDriver);
    }


}
