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
        //В первую очередь, я максимально параметризировал все методы
        //и изменил простые ожидалки на ожидалки по локаторам, там, где это явно требовалось
        MainPage main = new MainPage();
        //Заменил поиск по предложениям из выпадающего списка на поиск сразу по странице результатов
        //Однако в любом случае строка поиска иногда залагивает
        //Если это случится - метод searchItemAndPressEnter увидит ошибку и предупредит
        main.searchItemAndPressEnter(productObject.getType());
        ResultPage resultPage = new ResultPage();
        resultPage.findWhichYouNeed(productObject.getName());
        ProductInfoPage productInfoPage = new ProductInfoPage();
        productObject.setCommonPrice(productInfoPage.rememberPrice());
        productInfoPage.getGuarantee(productObject.getGuarantee());
        productObject.setPriceWithGuarantee(productInfoPage.rememberPrice());
        productInfoPage.buy();
        main.searchItemAndPressEnter(detroit.getType());
        detroit.setCommonPrice(productInfoPage.rememberPrice());
        productInfoPage.buy();
        productInfoPage.assertPrice(productObject.getPriceWithGuarantee()+detroit.getCommonPrice());
        productInfoPage.gotoBasket();
        productInfoPage.assertGuarantee(productObject.getGuarantee());
        BasketPage basketPage = new BasketPage();
        basketPage.deleteFromBasket(1);
        basketPage.assertPrice(productObject.getPriceWithGuarantee());
        // в методе addItem не придумал нормальной ожидалки
        // пока оставлю Thread.sleep, но работы над нормальными ожиданиями будут вестись
        basketPage.addItem(0);
        basketPage.addItem(0);
        basketPage.assertPrice(productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee());
        // Иногда на сайте не появляется кнопка "вернуть удаленный товар"
        // В таком случае метод restoreRemove поймает ошибку и предупредит о том, что тест упадёт
        basketPage.restoreRemove();
        basketPage.assertPrice(productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee()+productObject.getPriceWithGuarantee()+detroit.getCommonPrice());
    }
    @After
    public  void close(){
        Init.closeDriver();
    }
}
