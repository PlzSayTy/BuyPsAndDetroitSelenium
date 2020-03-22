package Main.Pages.Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ResultPage extends BasePage{
    @FindBy(xpath = "//a[contains(@data-role, 'clamped-link')]")
    List<WebElement> products;
    public void findWhichYouNeed(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'product-info__title')]")));
        WebElement item = products.stream()
                .filter(element->element.getText().contains(name))
                .findFirst().orElseThrow(()->new RuntimeException("no element with title "+name));
        item.click();
    }
}
