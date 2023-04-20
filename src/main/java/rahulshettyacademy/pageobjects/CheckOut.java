package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent{

	WebDriver driver;

	public CheckOut(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = "input[placeholder='Select Country']")
	List<WebElement> countries;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryBox;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement desiredCountry;
	
	@FindBy(css=".action__submit")
	WebElement subBtn;
	
	
	By countryList = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) 
	{
		Actions a = new Actions(driver);
		a.sendKeys(countryBox, countryName).build().perform();
		waitForElementToAppear(countryList);
		desiredCountry.click();
	}
	
	public ConfirmationPage placeOrder() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(1000);
		subBtn.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
}