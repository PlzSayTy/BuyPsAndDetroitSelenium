package Main.Pages.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class BasketPage extends BasePage{
    @FindBy(xpath = "//button[contains(text(),'Удалить')]")
    List <WebElement> deleteElement;
    @FindBy(xpath = "//i[contains(@class, 'count-buttons__icon-plus')]")
    List <WebElement> addElement;
    public void deleteFromBasket(int productNumber){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Удалить')]")));
        WebElement delete = deleteElement.stream()
                .skip(productNumber)
                .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
        delete.click();
    }
    public void addItem(int productNumber) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contain" + "s(@class, 'count-buttons__icon-plus')]")));
        WebElement add = addElement.stream()
                .skip(productNumber)
                .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
        executor = (JavascriptExecutor)driver;
        try{
            executor.executeScript("arguments[0].click();", add);
            wait.until(ExpectedConditions.elementToBeClickable(add));
        }catch (org.openqa.selenium.StaleElementReferenceException ex){
            wait.until(ExpectedConditions.elementToBeClickable(add));
            Thread.sleep(3000);
            executor.executeScript("arguments[0].click();", add);
        }
        Thread.sleep(2500);
    }
}
