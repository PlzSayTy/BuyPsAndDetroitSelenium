package Main.Pages.Pages;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.omg.CORBA.SystemException;
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
        try {
            if (driver.findElement(By.xpath("//div[contains(@class, 'homepage-actual-offers-main__title'")).isDisplayed()) {
                System.out.println("меню залагало, тест упадет");
            }
        }catch (org.openqa.selenium.InvalidSelectorException ise){
            System.out.println("меню не залагало, продолжаем работу");
        }

    }
}
