package Main.Pages;
import Main.Pages.Pages.BasketPage;
import Main.Pages.Pages.MainPage;
import Main.Pages.Pages.ProductInfoPage;
import Main.Pages.Pages.ResultPage;
import Main.Pages.ProductObject.ProductObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    ProductObject productObject = new ProductObject("playstation", "Игровая приставка PlayStation 4 Slim Black 1 TB", "2");
    ProductObject detroit = new ProductObject("detroit", "detroit", "");

    @Before
    public void openBrowser() {
        Init.initDriver();
    }

    @Test
    public void pageObjectCU() throws InterruptedException {
        MainPage main = new MainPage();
        main.searchItem(productObject.getType());
        main.findWhichYouNeed(productObject.getType());
        ResultPage resultPage = new ResultPage();
        resultPage.findWhichYouNeed(productObject.getName());
        ProductInfoPage productInfoPage = new ProductInfoPage();
        productObject.setCommonPrice(productInfoPage.rememberPrice());
        productInfoPage.getGuarantee(productObject.getGuarantee());
        productObject.setPriceWithGuarantee(productInfoPage.rememberPrice());
        System.out.println(productObject.getPriceWithGuarantee());
        productInfoPage.buy();
        main.searchItemAndPressEnter(detroit.getType());
        detroit.setCommonPrice(productInfoPage.rememberPrice());
        productInfoPage.buy();
        productInfoPage.assertPrice(productObject.getPriceWithGuarantee()+detroit.getCommonPrice());
        productInfoPage.gotoBasket();
        productInfoPage.assertGuarantee(productObject.getGuarantee());
        BasketPage basketPage = new BasketPage();
        basketPage.deleteFromBasket(2);
        basketPage.assertPrice(productObject.getPriceWithGuarantee());
        basketPage.addItem(1);
        basketPage.addItem(1);
        System.out.println(productObject.getPriceWithGuarantee());
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
