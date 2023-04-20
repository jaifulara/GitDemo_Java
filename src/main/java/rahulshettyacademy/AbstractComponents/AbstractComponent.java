package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.OrderPage;
public class AbstractComponent {
	
	WebDriver driver;

	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='/dashboard/myorders']")
	WebElement orderHeader;

	public MyCart goToCart() 
	{
		cart.click();
		MyCart myCart = new MyCart(driver);
		return myCart;
	}
	
	public OrderPage goToOrdersPage() 
	{
		orderHeader.click();
		OrderPage orderPage =new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}

}
