package Promod_Framework.pageobjects;

import Promod_Framework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponents {
    WebDriver driver;

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;
    @FindBy(xpath = "//span[text()=' India']")
            WebElement country;
    @FindBy(css = ".action__submit")
            WebElement placeOrder;

//       driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("IND");
//        driver.findElement(By.xpath("//span[text()=' India']")).click();
//    Actions act= new Actions(driver);
//        act.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();

    public void setSelectCountry(String countryKey){
        selectCountry.sendKeys(countryKey);
        country.click();

    }
    public ConfirmationPage submitOrder(WebDriver driver){
        Actions act=new Actions(driver);
        act.moveToElement(placeOrder).click().build().perform();
        ConfirmationPage confirmationPage=new  ConfirmationPage(driver);
        return confirmationPage;

    }


}
