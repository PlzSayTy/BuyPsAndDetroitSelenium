package Main.Pages.Pages;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage{
    @FindBy (xpath = "//input[contains(@placeholder, 'Поиск среди более 100 000 товаров')]")
    WebElement searchFieldInput;
    @FindBy (xpath = "/html/body/header/nav/div/form/div")
    WebElement elementToClick;
    @FindBy(xpath = "//a[contains(@class, 'ui-link presearch__suggest')]")
    List<WebElement> products;
    JavascriptExecutor executor;
    public void findWhichYouNeed(String itemTitle){
        wait.until(ExpectedConditions.visibilityOf(products.get(0
        )));
        WebElement item = products.stream()
                .filter(element->element.getText().contains(itemTitle))
                .findFirst().orElseThrow(()->new RuntimeException("no element with title "+itemTitle));
        item.click();
    }
    public void searchItem(String name){
        elementToClick.click();
        searchFieldInput.clear();
        searchFieldInput.sendKeys(name);
        Assert.assertTrue(searchFieldInput.getAttribute("value").equals(name));
    }
}
