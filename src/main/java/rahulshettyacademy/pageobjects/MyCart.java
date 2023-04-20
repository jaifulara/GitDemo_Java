package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent{

	WebDriver driver;
	

	public MyCart(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	


	@FindBy(xpath = "//*[@class='cartSection']/h3")
	List<WebElement> cartItems;

	@FindBy(css= "li[class='totalRow'] button")
	WebElement checkoutBtn;
	
	public Boolean verifyProductInCart(String prodName) 
	{
		Boolean match = cartItems.stream().anyMatch(cartItem-> cartItem.getText().equalsIgnoreCase(prodName));
		return match;
	}
	
	public CheckOut checkOut() throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,1200)");
		Thread.sleep(2000);
		checkoutBtn.click();
		CheckOut checkOut = new CheckOut(driver);
		return checkOut;
		
	}
}