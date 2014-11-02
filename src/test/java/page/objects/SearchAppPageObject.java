package page.objects;

import com.google.common.base.Function;
import lib.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

/**
 * Created by Serg on 29.10.2014.
 */
public class SearchAppPageObject extends AbstractPage {
    @FindBy(id = "search_button")
    private WebElement searchBtn;

    @FindBy(id = "price")
    private WebElement priceInput;

    @FindBy(id = "price_search_option")
    private WebElement priceSearchOption;

    @FindBy(id="price_sorting_order_option")
    private WebElement priceSortingElem;

    @FindBy(id="validation_info")
    private WebElement validationInfo;


    public SearchAppPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void userTypesQuery(final String queryText) {
        getInputField().sendKeys(queryText);
    }


    public void userTypesPrice(final String queryText) {
        priceInput.sendKeys(queryText);
    }

    public WebElement getInputField(){
        return new WebDriverWait(webDriver, 5).until(new Function<WebDriver, WebElement>() {
            @Nullable
            @Override
            public WebElement apply(@Nullable WebDriver driver) {
                WebElement input = webDriver.findElement(By.xpath("//*[@id='search_text']"));
                if (input.isDisplayed()) {
                    return input;
                } else {
                    return null;
                }
            }
        });
    }

    public void userClickSearchButton() {
        searchBtn.click();
    }

    public boolean searchBtnIsDispalyed() {
        return searchBtn.isDisplayed();
    }


    public void userSelectAscSorting(){
        Select select = new Select(priceSortingElem);
        select.selectByValue("ascending");
    }

    public void userSelectDescSorting(){
        Select select = new Select(priceSortingElem);
        select.selectByValue("descending");
    }


    public void userSelectMoreThan(){
        Select select = new Select(priceSearchOption);
        select.selectByValue("more than");
    }

    public void userSelectLessThan() {
        Select select = new Select(priceSearchOption);
        select.selectByValue("less than");
    }

    public String getSelectedPriceOption() {
        Select select = new Select(priceSortingElem);
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedMoreLessOption() {
        Select select = new Select(priceSearchOption);
        return select.getFirstSelectedOption().getText();
    }

    public boolean validationInfoIsDisplyed() {
        return validationInfo.isDisplayed();
    }

    public String getMaxLength(){
        return getInputField().getAttribute("maxlength");
    }

}
