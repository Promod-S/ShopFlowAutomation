import Promod_Framework.pageobjects.LandingPage;
import Promod_Framework.pageobjects.ProductCatalogue;
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

//        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LandingPage landingPage=new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginApplication("pramod123@gmail.com","Test@1234");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        List<WebElement>products= productCatalogue.getProductList();
        productCatalogue.addProductToCart(testProduct);
        productCatalogue.goToCartPage();

        //        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cartWrap")));

        List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartWrap h3"));
        Boolean isProductInCart = false;
        for(WebElement item : cartProducts) {
            if(item.getText().equals(testProduct)) {
                isProductInCart = true;
                break;
            }
        }

//        Boolean isProductInCart= cartProducts.stream().anyMatch(item->item.getText().equals(testProduct));

//        WebElement item=null;
//        for(WebElement prod: cartProducts){
//            if(prod.findElement(By.xpath("//h3[text()='ADIDAS ORIGINAL']")).getText()
//            System.out.println(prod);
////            if(prod.findElement(By.className("cartWrap")).getText().equals(testProduct)){
////                item= prod;
////                break;
////            }
//        }


        Assert.assertTrue(isProductInCart);
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("IND");
        driver.findElement(By.xpath("//span[text()=' India']")).click();
        Actions act= new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();


        String thankYouText="THANKYOU FOR THE ORDER.";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".box h1")));
        String resultText=driver.findElement(By.cssSelector(".box h1")).getText();
        System.out.println(resultText);


        Assert.assertEquals(thankYouText,resultText);

        driver.close();


















    }
}
