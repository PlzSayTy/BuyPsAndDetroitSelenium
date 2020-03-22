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

import java.sql.SQLOutput;
import java.util.List;

public class MainPage extends BasePage{
    @FindBy (xpath = "//input[contains(@placeholder, 'Поиск среди более 100 000 товаров')]")
    WebElement searchFieldInput;
    @FindBy (xpath = "/html/body/header/nav/div/form/div")
    WebElement elementToClick;
    public void findWhichYouNeed(String itemTitle){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'ui-link presearch__suggest')]")));
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[contains(@class, 'ui-link presearch__suggest')]"));
        System.out.println(listOfProducts.get(0).getText());
        WebElement item = listOfProducts.stream()
                .filter(element->element.getText().contains(itemTitle))
                .findFirst().orElseThrow(()->new RuntimeException("no element with title "+itemTitle));
        item.click();
        listOfProducts.clear();
    }
    public void searchItem(String name){
        waitForClickable(elementToClick);
        elementToClick.click();
        waitForElement(searchFieldInput);
        searchFieldInput.clear();
        searchFieldInput.sendKeys(name);
        Assert.assertTrue(searchFieldInput.getAttribute("value").equals(name));
    }
    public void searchItemAndPressEnter(String name){
        waitForClickable(elementToClick);
        elementToClick.click();
        waitForElement(searchFieldInput);
        searchFieldInput.clear();
        searchFieldInput.sendKeys(name+"\n");
    }
}
