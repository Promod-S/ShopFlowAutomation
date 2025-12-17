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

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;

    }

}
