import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        String testProduct= "ADIDAS ORIGINAL";

        //automate the login page for https://rahulshettyacademy.com/client
        //        pramod123@gmail.com
        //        pwd- Test@1234

        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//input[@type= 'email']")).sendKeys("pramod123@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder= 'enter your passsword']")).sendKeys("Test@1234");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' View']/parent::div/parent::div/parent::div")));

        List<WebElement> products= driver.findElements(By.xpath("//button[text()=' View']/parent::div/parent::div/parent::div"));

//        String testProduct = driver.findElement(By.xpath("(//button[text()=' View']/parent::div/parent::div/parent::div)[2]")).getText();
//        System.out.println(testProduct);
        driver.findElement(By.xpath("(//button[text()=' Add To Cart'])[2]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));

        driver.findElement(By.xpath("//button[@routerlink=\'/dashboard/cart\']")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cartWrap")));

        List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartWrap h3"));

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
        Boolean isProductInCart = false;
        for(WebElement item : cartProducts) {
            if(item.getText().equals(testProduct)) {
                isProductInCart = true;
                break;
            }
//            String text = item.getText().trim();
//            System.out.println("Cart Product: '" + text + "'");
        }

        Assert.assertTrue(isProductInCart);
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("IND");
        driver.findElement(By.xpath("//span[text()=' India']")).click();
//        driver.findElement(By.cssSelector(".action__submit")).click();

//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit"))).click();
        Actions act= new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();

//        String thankYouText=" Thankyou for the order. ";
        String thankYouText="THANKYOU FOR THE ORDER.";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".box h1")));
        String resultText=driver.findElement(By.cssSelector(".box h1")).getText();
        System.out.println(resultText);


        Assert.assertEquals(thankYouText,resultText);

        driver.close();


















    }
}
