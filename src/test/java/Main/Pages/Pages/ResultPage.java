package Main.Pages.Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ResultPage extends BasePage{
    @FindBy(xpath = "//div[contains(@class, 'catalog-items-list view-simple')]//a[contains(text(), 'PlayStation')]")
    List<WebElement> products;
    public void findWhichYouNeed(String name){
        wait.until(ExpectedConditions.visibilityOf(products.get(0)));
        WebElement item = products.stream()
                .filter(element->element.getText().contains(name))
                .findFirst().orElseThrow(()->new RuntimeException("no element with title "+name));
        item.click();
    }
}
