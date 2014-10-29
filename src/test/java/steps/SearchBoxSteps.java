package steps;

import lib.AbstractSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Serg on 29.10.2014.
 */
public class SearchBoxSteps extends AbstractSteps {

    public SearchBoxSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchBy(String text) {
        WebElement searchText = webDriver.findElement(By.name("q"));
        searchText.sendKeys(text);
        searchText.submit();
    }
}
