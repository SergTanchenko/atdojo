package steps;

import com.google.common.base.Function;
import lib.AbstractSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.concurrent.TimeoutException;

/**
 * Created by Serg on 29.10.2014.
 */
public class NavigationSteps extends AbstractSteps {

    public NavigationSteps(WebDriver webDriver) {
        super(webDriver);
    }

    public void openPage(final String url) {
        webDriver.get(url);
        waitForPageBeLoaded();
    }

    public void waitForPageBeLoaded() {
        new WebDriverWait(webDriver, 10).until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return  (Boolean) ((JavascriptExecutor) webDriver).executeScript("return document.readyState === 'complete'");
            }
        });
    }
}
