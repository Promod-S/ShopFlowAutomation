package Promod_Framework.pageobjects;

import Promod_Framework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

//    List<WebElement> products= driver.findElements
//    (By.xpath("//button[text()=' View']/parent::div/parent::div/parent::div"));
    @FindBy(xpath = "//button[text()=' View']/parent::div/parent::div/parent::div")
    List<WebElement> products;

    //By selector
     By productsBy= By.xpath("//button[text()=' View']/parent::div/parent::div/parent::div");
     By addToCartBy= By.cssSelector(".card-body button:last-of-type");
     By toastMessageBy= By.id("toast-container");
     By spinnerBy= By.className("ng-animating");


    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;

    }
    public WebElement getProductByName(String testProduct){
        WebElement prod= getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(testProduct)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String testProduct){
        WebElement prod= getProductByName(testProduct);
        prod.findElement(addToCartBy).click();
        waitForElementToAppear(toastMessageBy);
        waitForElementToDisappear(spinnerBy);

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
//        //ng-animating
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
    }

}
