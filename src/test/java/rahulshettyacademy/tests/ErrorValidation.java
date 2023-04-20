package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidation extends BaseTest 
{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException 
	{		
		landingPage.loginApplication("anshika@gmail.com","Iamkin000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());	
		
		System.out.println("Test is passed");
		System.out.println("Oh Nice");
		System.out.println("Oh Nice");
		
	}
		
//	@Test
//	public void ProductErrorValidation() throws InterruptedException, IOException 
//	{	
//		String prodName = "IPHONE 13 PRO";
//				
//		ProductCatalogue prodCatalogue = landingPage.loginApplication("anshika@gmail.com","Iamking@000");		
//		List<WebElement> products = prodCatalogue.getProductList();
//		prodCatalogue.addProductToCart(prodName);
//		MyCart myCart = prodCatalogue.goToCart();
//		
//		Boolean match = myCart.verifyProductInCart(prodName);
//		Assert.assertTrue(match);
//	}
}
