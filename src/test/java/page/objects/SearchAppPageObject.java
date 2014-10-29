package page.objects;

import lib.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Serg on 29.10.2014.
 */
public class SearchAppPageObject extends AbstractPage {
    @FindBy(id = "search_button")
    private WebElement searchBtn;

    @FindBy(id = "search_text")
    private WebElement searchInput;


    public SearchAppPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void userTypesQuery(final String queryText) {
        searchInput.sendKeys(queryText);
    }

    public void userClickSearchButton() {
        searchBtn.click();
    }

}
