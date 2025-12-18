package Promod_Framework.pageobjects;

import Promod_Framework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".cartWrap h3")
    List<WebElement> cartProducts;
    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkOut;



//    public List<WebElement> getCartProducts(){
//
//                return cartProducts;
//    }

//    List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartWrap h3"));
//    Boolean isProductInCart = false;
//        for(WebElement item : cartProducts) {
//        if(item.getText().equals(testProduct)) {
//            isProductInCart = true;
//            break;
//        }
//    }
//     driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        public Boolean isProductInCart(String testProduct){
            Boolean match= cartProducts.stream().anyMatch(item->
                    item.getText().equals(testProduct));
            return match;

        }
        public CheckOutPage goToCheckOut(){
            checkOut.click();
            CheckOutPage checkOutPage=new CheckOutPage(driver);
            return checkOutPage;
        }
}
