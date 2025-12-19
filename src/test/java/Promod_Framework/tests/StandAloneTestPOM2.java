package Promod_Framework.tests;

import Promod_Framework.TestComponents.BaseTest;
import Promod_Framework.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StandAloneTestPOM2 extends BaseTest {
    String testProduct= "ADIDAS ORIGINAL";
    @Test
    public void submitOrderTest() throws IOException {


//        LandingPage landingPage=new LandingPage(driver);
//        landingPage.goTo();
//        LandingPage landingPage=launchApplication();
        ProductCatalogue productCatalogue=landingPage.loginApplication("pramod123@gmail.com","Test@1234");
        List<WebElement>products= productCatalogue.getProductList();
        productCatalogue.addProductToCart(testProduct);
        CartPage cartPage=productCatalogue.goToCartPage();
        Boolean match=cartPage.isProductInCart(testProduct);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage=cartPage.goToCheckOut();
        checkOutPage.setSelectCountry("ind");
        ConfirmationPage confirmationPage=checkOutPage.submitOrder(driver);
        String resultText= confirmationPage.getConfirmationMessage();
        Assert.assertTrue(resultText.equals("THANKYOU FOR THE ORDER."));
    }
    @Test(dependsOnMethods = {"submitOrderTest"})
    public  void orderHistoryTest(){
        ProductCatalogue productCatalogue=landingPage.loginApplication("pramod123@gmail.com","Test@1234");
        OrderPage orderPage= productCatalogue.goToOrderPage();

        Assert.assertTrue(orderPage.verifyOrderDisplay(testProduct));


    }
}
