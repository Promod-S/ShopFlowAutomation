package Promod_Framework.AbstractComponents;

import Promod_Framework.pageobjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver= driver;

    }
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;

    public void waitForElementToAppear(By findBy)    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElementToDisappear(By findBy){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    public CartPage goToCartPage(){
//        driver.findElement(By.xpath("//button[@routerlink=\'/dashboard/cart\']")).click();
        cartHeader.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }
}
