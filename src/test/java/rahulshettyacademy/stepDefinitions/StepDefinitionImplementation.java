package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue prodCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I have landed on Ecommerce Page")
	public void I_have_landed_on_Ecommerce_Page() throws IOException 
	{
		landingPage = launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String name, String password)
	{
		prodCatalogue = landingPage.loginApplication(name, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products = prodCatalogue.getProductList();
		prodCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		MyCart myCart = prodCatalogue.goToCart();
		Boolean match = myCart.verifyProductInCart(productName);
		Assert.assertTrue(match);
		CheckOut checkOut = myCart.checkOut();
		checkOut.selectCountry("india");
		confirmationPage = checkOut.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmation_message(String string)
	{
		String confirmationMessage = confirmationPage.ConfirmartionMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed on login$")
	public void error_message_displayed(String strArg1) throws Throwable
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}

}
