package Promod_Framework.pageobjects;

import Promod_Framework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponents {
    WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
    List<WebElement> productNames ;
    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkOut;

        public Boolean verifyOrderDisplay(String testProduct){
            Boolean match= productNames.stream().anyMatch(item->
                    item.getText().equals(testProduct));
            return match;

        }
        public CheckOutPage goToCheckOut(){
            checkOut.click();
            CheckOutPage checkOutPage=new CheckOutPage(driver);
            return checkOutPage;
        }
}
