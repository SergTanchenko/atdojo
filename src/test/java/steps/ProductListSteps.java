package steps;

import lib.AbstractSteps;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 30.10.2014.
 */
public class ProductListSteps extends AbstractSteps {
    public ProductListSteps(WebDriver webDriver) {
        super(webDriver);
    }



    //Then all items contains [keyword]
    public boolean itemsContainsKeyWord(final List<String> productList, final String keyWord) {
        List<String> result = new ArrayList<String>();

        for(String product : productList) {
            if (!product.equalsIgnoreCase(keyWord.toLowerCase())) {
                result.add(product);
            }
        }
        return result.size() == 0;
    }


}
