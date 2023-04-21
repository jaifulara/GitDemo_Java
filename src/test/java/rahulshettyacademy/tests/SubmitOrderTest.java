package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckOut;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest {
	String prodName = "IPHONE 13 PRO";
	
	@Test(dataProvider="getData", groups={"Purchase"})
//	public void submitOrder(String email, String password, String productName) throws InterruptedException, IOException 
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException 
	{		
		ProductCatalogue prodCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = prodCatalogue.getProductList();
		prodCatalogue.addProductToCart(input.get("product"));
		MyCart myCart = prodCatalogue.goToCart();
		
		Boolean match = myCart.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match);
		CheckOut checkOut = myCart.checkOut();
		checkOut.selectCountry("india");
		
		ConfirmationPage confirmationPage = checkOut.placeOrder();
		String confirmationMessage = confirmationPage.ConfirmartionMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	@Test (dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue prodCatalogue = landingPage.loginApplication("anshika@gmail.com","Iamking@000");
		OrderPage ordersPage = prodCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(prodName));
	}
	
	@Test (dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest2()
	{
		ProductCatalogue prodCatalogue = landingPage.loginApplication("anshika@gmail.com","Iamking@000");
		OrderPage ordersPage = prodCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(prodName));
	}
	
	@Test (dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest3()
	{
		ProductCatalogue prodCatalogue = landingPage.loginApplication("anshika@gmail.com","Iamking@000");
		OrderPage ordersPage = prodCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(prodName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}


//@DataProvider
//public Object[][] getData()
//{
//	return new Object[][] {{"anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO"},{"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//}


//@DataProvider
//public Object[][] getData() throws IOException
//{
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "IPHONE 13 PRO");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL"); }
