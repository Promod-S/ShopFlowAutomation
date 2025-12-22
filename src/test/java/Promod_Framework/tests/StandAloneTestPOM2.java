package Promod_Framework.tests;

import Promod_Framework.TestComponents.BaseTest;
import Promod_Framework.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StandAloneTestPOM2 extends BaseTest {
    String testProduct= "ADIDAS ORIGINAL";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitOrderTest(String email,String password, String testProduct) throws IOException {


//        LandingPage landingPage=new LandingPage(driver);
//        landingPage.goTo();
//        LandingPage landingPage=launchApplication();
//        ProductCatalogue productCatalogue=landingPage.loginApplication("pramod123@gmail.com","Test@1234");
        ProductCatalogue productCatalogue=landingPage.loginApplication(email,password);
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
    @DataProvider
    public Object[][] getData(){
        return new Object[][]{{"pramod123@gmail.com","Test@1234","ADIDAS ORIGINAL"},{"1234pramod@gmail.com","Test@1234","ZARA COAT 3"}};
    }

}
