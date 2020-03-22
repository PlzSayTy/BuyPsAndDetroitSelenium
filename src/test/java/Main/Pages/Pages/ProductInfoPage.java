package Main.Pages.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductInfoPage extends BasePage{
    @FindBy(xpath = "//span[contains(@class, 'current-price-value')]")
    WebElement price;
    @FindBy(xpath = "//div[contains(@class, 'desktop-selector')]//select[contains(@class, 'form-control select')]")
    WebElement selectElem;
    @FindBy(xpath = "//button[contains(@class, 'btn btn-cart btn-lg')]")
    WebElement buyButton;
    public int rememberPrice(){
        String s = price.getText().replace(" ", "");
        return Integer.parseInt(s);
    }
    public void getGuarantee(String s){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'desktop-selector')]//select[contains(@class, 'form-control select')]")));
        Select select = new Select(selectElem);
        wait.until(ExpectedConditions.visibilityOf(selectElem));
        select.selectByValue(s);
    }
    public  void buy(){
        buyButton.click();
    }
}
