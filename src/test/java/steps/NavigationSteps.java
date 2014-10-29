package steps;

import lib.AbstractSteps;
import org.openqa.selenium.WebDriver;

/**
 * Created by Serg on 29.10.2014.
 */
public class NavigationSteps extends AbstractSteps {

    public NavigationSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage(final String url) {
        webDriver.get(url);
    }
}
