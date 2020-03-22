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
    Actions builder;
    public void deleteFromBasket(int position){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Удалить')]")));
        WebElement delete = deleteElement.stream()
                .skip(position - 1)
                .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
        delete.click();
    }
    public void assertDelete(String s){

    }
    public void addItem(int position) throws InterruptedException {
        String s = getBasketPrice();
        Thread.sleep(1500);
        builder = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class, 'count-buttons__icon-plus')]")));
        WebElement add = addElement.stream()
                .skip(position - 1)
                .findFirst().orElseThrow(() -> new RuntimeException("нет такой позиции"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'count-buttons__icon-plus')]")));
        executor = (JavascriptExecutor)driver;
        try{
            executor.executeScript("arguments[0].click();", add);
            wait.until(ExpectedConditions.elementToBeClickable(add));
            //builder.click(add).perform();
            //add.click();
        }catch (org.openqa.selenium.StaleElementReferenceException ex){
            wait.until(ExpectedConditions.elementToBeClickable(add));
            Thread.sleep(3000);
            //builder.click(add).perform();
            executor.executeScript("arguments[0].click();", add);
            //add.click();
        }
        Thread.sleep(2500);
    }
}
