package Promod_Framework.tests;


import Promod_Framework.TestComponents.BaseTest;
import Promod_Framework.pageobjects.CartPage;
import Promod_Framework.pageobjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() throws IOException {
        ProductCatalogue productCatalogue=landingPage.loginApplication("pramod123@gmail.com","Tess@1234");

        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
    @Test
    public void productErrorValidation(){
        // 1234pramod@gmail.com   .... Test@1234
        String testProduct= "ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue=landingPage.loginApplication("1234pramod@gmail.com","Test@1234");
        List<WebElement>products= productCatalogue.getProductList();
        productCatalogue.addProductToCart(testProduct);
        CartPage cartPage=productCatalogue.goToCartPage();
        Boolean match=cartPage.isProductInCart("ADIDAS ORIGINAL XYZ");
        Assert.assertFalse(match);

    }
}
