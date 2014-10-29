package tests;

import org.automation.dojo.DojoTestRunner;
import org.automation.dojo.ReportTo;
import org.automation.dojo.Scenario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import page.objects.SearchBoxPageObject;
import steps.NavigationSteps;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(DojoTestRunner.class)
@ReportTo(server = "http://127.0.0.1:8080", userName = "JohnDoe")
public class SampleAutomationTest {
    private WebDriver webDriver;
    private NavigationSteps navigationSteps;


    @Before
    public void setUp() {
        //Please change false to true during dojo
        webDriver = new HtmlUnitDriver(false);
        navigationSteps = new NavigationSteps(webDriver);
    }

    @Test
    @Scenario(1)
    public void searchForItem() {
        SearchBoxPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchBoxPageObject.class);
        //Given open google.com
        navigationSteps.openPage("http://google.com");

        // When I search by "automated testing dojo"
        searchBoxPage.searchBy("automated testing dojo idea");

        //Then I see a link to Sergey's blog
        assertContainsLink("szelenin.blogspot.com");
    }

    @Test
    @Scenario(1)
    public void shouldBeAbleToClick() {
        SearchBoxPageObject searchBoxPage = PageFactory.initElements(webDriver, SearchBoxPageObject.class);
        //Given open google.com
        navigationSteps.openPage("http://google.com");

        // When I search by "automated testing dojo"
        searchBoxPage.searchBy("automated testing dojo idea");

        // When I click on link for Sergey's blog
        clickOn("szelenin.blogspot.com");

        //Then Sergey's blog is open
        assertWeAreAt("http://szelenin.blogspot.com");
    }

    private void assertWeAreAt(String linkAddress) {
        assertTrue(webDriver.getCurrentUrl().contains(linkAddress));
    }

    private void clickOn(String linkAddress) {
        List<WebElement> links = findLinks(linkAddress);
        links.get(0).click();
    }



    private void assertContainsLink(String address) {
        assertFalse(findLinks(address).isEmpty());
    }

    private List<WebElement> findLinks(String address) {
        return webDriver.findElements(By.xpath("//a[contains(@href, '" + address + "')]"));
    }


}
