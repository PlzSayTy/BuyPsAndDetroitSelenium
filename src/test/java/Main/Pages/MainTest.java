package Main.Pages;
import Main.Pages.Pages.BasketPage;
import Main.Pages.Pages.MainPage;
import Main.Pages.Pages.ProductInfoPage;
import Main.Pages.Pages.ResultPage;
import Main.Pages.ProductObject.ProductObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    ProductObject productObject = new ProductObject("playstation", "Игровая приставка PlayStation 4 Slim Black 1 TB", "2");
    ProductObject detroit = new ProductObject("detroit", "detroit become human", "");

    @Before
    public void openBrowser() {
        Init.initDriver();
    }

    @Test
    public void pageObjectCU(){
        MainPage main = new MainPage();
        main.searchItem(productObject.getType());
        main.findWhichYouNeed(productObject.getType());
        ResultPage resultPage = new ResultPage();
        resultPage.findWhichYouNeed(productObject.getName());
        ProductInfoPage productInfoPage = new ProductInfoPage();
        productObject.setCommonPrice(productInfoPage.rememberPrice());
        productInfoPage.getGuarantee(productObject.getGuarantee());
        productObject.setPriceWithGuarantee(productInfoPage.rememberPrice());
        productInfoPage.buy();
        main.searchItem(detroit.getType());
        main.findWhichYouNeed(detroit.getType());
        resultPage.findWhichYouNeed(detroit.getName());
        detroit.setCommonPrice(productInfoPage.rememberPrice());
        productInfoPage.buy();
        productInfoPage.assertPrice(productObject.getPriceWithGuarantee()+detroit.getCommonPrice());
        productInfoPage.gotoBasket();
        productInfoPage.assertGuarantee(productObject.getGuarantee());
        BasketPage basketPage = new BasketPage();
        basketPage.deleteFromBasket();
        basketPage.assertDelete("");
        basketPage.assertPrice(productObject.getPriceWithGuarantee());
        basketPage.addMorePs();
        basketPage.addMorePs();
        basketPage.assertPrice(productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee());
        basketPage.restoreRemove();
        basketPage.assertPrice(productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee()+detroit.getCommonPrice());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public  void close(){
        Init.closeDriver();
    }
}
