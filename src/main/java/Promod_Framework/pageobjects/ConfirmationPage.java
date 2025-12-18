package Promod_Framework.pageobjects;

import Promod_Framework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    By messageBy= By.cssSelector(".box h1");
    @FindBy(css = ".box h1")
    WebElement confirmationMessage;

        public String getConfirmationMessage(){
            waitForElementToAppear(messageBy);
            return confirmationMessage.getText();

        }
}
