package Main.Pages.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage{
    @FindBy(xpath = "//i[contains(@class, 'count-buttons__icon-minus')]")
    WebElement deleteElement;
    @FindBy(xpath = "//i[contains(@class, 'count-buttons__icon-plus')]")
    WebElement addElement;
    public void deleteFromBasket(){
        deleteElement.click();
    }
    public void assertDelete(String s){
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Detroit'"))!=null);
    }
    public void addMorePs(){
        addElement.click();
    }
}
