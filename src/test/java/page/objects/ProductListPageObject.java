package page.objects;

import lib.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 29.10.2014.
 */
public class ProductListPageObject extends AbstractPage {
    @FindBy(id = "product_list")
    private WebElement productListTable;

    @FindBy(id = "search_info")
    private WebElement searchInfo;

    public ProductListPageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean productListTableIsDisplyed(){
        return productListTable.isDisplayed();
    }

    public List<String> lookupProductList(){
        List result = new ArrayList();
        if (!productListTable.isDisplayed()){
            return null;
        }

        for (WebElement rowElem : productListTable.findElements(By.xpath("//tr[contains(@id,'productId_')]"))) {
            result.add(rowElem.getText());
        }
        return result;
    }

    public boolean searchInfoContains(final String expectedText){
        return searchInfo.getText().equalsIgnoreCase(expectedText);
    }
}
