package Promod_Framework.tests;

import Promod_Framework.TestComponents.BaseTest;
import Promod_Framework.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTestPOM2 extends BaseTest {
    String testProduct= "ADIDAS ORIGINAL";
    @Test(dataProvider = "getData",groups = {"Purchase"})
            //for 2D array
//    public void submitOrderTest(String email,String password, String testProduct) throws IOException {
            //for HashMap
            public void submitOrderTest(HashMap<String,String> input) {

//        LandingPage landingPage=new LandingPage(driver);
//        landingPage.goTo();
//        LandingPage landingPage=launchApplication();
//        ProductCatalogue productCatalogue=landingPage.loginApplication("pramod123@gmail.com","Test@1234");
//        ProductCatalogue productCatalogue=landingPage.loginApplication(email,password);
        ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement>products= productCatalogue.getProductList();
//        productCatalogue.addProductToCart(testProduct);
        productCatalogue.addProductToCart(input.get("testProduct"));
        CartPage cartPage=productCatalogue.goToCartPage();
//        Boolean match=cartPage.isProductInCart(testProduct);
        Boolean match=cartPage.isProductInCart(input.get("testProduct"));
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
    public void getScreenshot(String testCaseName) throws IOException {
        File source= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source,file);
    }
//    Using 2D array to send parameters
//    @DataProvider
//    public Object[][] getData(){
//        return new Object[][]{{"pramod123@gmail.com","Test@1234","ADIDAS ORIGINAL"},{"1234pramod@gmail.com","Test@1234","ZARA COAT 3"}};
//    }

    //Using HashMap to send data
    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String,String> map1= new HashMap<>();
//        map1.put("email","pramod123@gmail.com");
//        map1.put("password","Test@1234");
//        map1.put("testProduct","ADIDAS ORIGINAL");
//
//        HashMap<String,String> map2= new HashMap<>();
//        map2.put("email","1234pramod@gmail.com");
//        map2.put("password","Test@1234");
//        map2.put("testProduct","ZARA COAT 3");

//        return new Object[][]{{map1},{map2}};

        List<HashMap<String ,String >> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Promod_Framework\\data\\PurchaseOrder.json");

        return new Object[][]{{data.get(0)},{data.get(1)}};
    }



}
