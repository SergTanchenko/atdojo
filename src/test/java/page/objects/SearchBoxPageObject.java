package page.objects;

import lib.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Serg on 29.10.2014.
 */
public class SearchBoxPageObject extends AbstractPage {

    @FindBy(name = "q")
    private WebElement searchInput;

    public SearchBoxPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchBy(String searchString) {
        searchInput.sendKeys(searchString);
        searchInput.submit();
    }
}
