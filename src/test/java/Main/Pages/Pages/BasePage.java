package Main.Pages.Pages;

import Main.Pages.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    WebDriverWait wait;

    JavascriptExecutor executor;

    @FindBy(xpath = "//span[contains(@class, 'cart-link__badge')]")
    WebElement cartButton;
    @FindBy(xpath = "//span[contains(@class, 'cart-link__price')]")
    WebElement currentBucketPrice;

    public BasePage(){
        this.driver = Init.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }
    public WebElement waitForClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void gotoBasket(){
        cartButton.click();
    }

    public void assertPrice(int i){
        String s;
        try {
             s = currentBucketPrice.getText().replace(" ", "");
        }catch (org.openqa.selenium.StaleElementReferenceException ex){
            s = currentBucketPrice.getText().replace(" ", "");
        }
        System.out.println(s);
        Assert.assertEquals(i, Integer.parseInt(s));
    }
    public void assertGuarantee(String s){
        int x = Integer.parseInt(s);
        x = x * 12;
        Assert.assertTrue(driver.findElements(By.xpath("//span[contains(text(), 'Продленная гарантия на " + x + "')]"))!=null);
    }
    public void restoreRemove(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='restore-last-removed']")));
        try {
            driver.findElement(By.xpath("//*[@class='restore-last-removed']")).click();
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class='restore-last-removed']"))));
        }catch (org.openqa.selenium.TimeoutException te){
            System.out.println("Кнопка вернуть товар не появилась, тест упадёт");
        }
    }
    public String getBasketPrice(){
        String s = currentBucketPrice.getText();
        System.out.println(s);
        return s;

    }
}
