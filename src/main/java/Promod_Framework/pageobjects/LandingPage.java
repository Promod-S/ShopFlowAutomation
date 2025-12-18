package Promod_Framework.pageobjects;

import Promod_Framework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        //initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
//      WebElement userEmail=driver.findElement(By.xpath("//input[@type= 'email']")).sendKeys("pramod123@gmail.com");
//        driver.findElement(By.xpath("//input[@placeholder= 'enter your passsword']")).sendKeys("Test@1234");
//        driver.findElement(By.xpath("//input[@value='Login']")).click();

    @FindBy(xpath="//input[@type= 'email']")
    WebElement userEmail;
    @FindBy(xpath = "//input[@placeholder= 'enter your passsword']")
    WebElement userPassword;
    @FindBy(xpath="//input[@value='Login']")
    WebElement submit;

    //error validation
    // ng-tns-c4-4 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        //need wait
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }


}
