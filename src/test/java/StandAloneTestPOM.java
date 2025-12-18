import Promod_Framework.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTestPOM {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        String testProduct= "ADIDAS ORIGINAL";
        //automate the login page for https://rahulshettyacademy.com/client
        //        pramod123@gmail.com
        //        pwd- Test@1234
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LandingPage landingPage=new LandingPage(driver);
        landingPage.goTo();
        ProductCatalogue productCatalogue=landingPage.loginApplication("pramod123@gmail.com","Test@1234");
//        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        List<WebElement>products= productCatalogue.getProductList();
        productCatalogue.addProductToCart(testProduct);
        CartPage cartPage=productCatalogue.goToCartPage();
//        CartPage cartPage=new CartPage(driver);
        Boolean match=cartPage.isProductInCart(testProduct);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage=cartPage.goToCheckOut();
        checkOutPage.setSelectCountry("ind");
        ConfirmationPage confirmationPage=checkOutPage.submitOrder(driver);
        String resultText= confirmationPage.getConfirmationMessage();
//        String thankYouText="THANKYOU FOR THE ORDER.";
//        Assert.assertEquals(thankYouText,resultText);
//          OR
        Assert.assertTrue(resultText.equals("THANKYOU FOR THE ORDER."));


        driver.close();


















    }
}
