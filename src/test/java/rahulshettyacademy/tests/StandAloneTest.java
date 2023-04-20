package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String prodName = "IPHONE 13 PRO";
				
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jaideep Fulara\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink='/dashboard/cart']")));
		
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		
		Boolean match = cartItems.stream().anyMatch(cartItem-> cartItem.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(match);
		
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("li[class='totalRow'] button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		//Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,400)");
		
		Thread.sleep(1000);
		WebElement subBtn = driver.findElement(By.cssSelector(".action__submit"));
		subBtn.click();
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		//subBtn.click();		
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		System.out.println("Oh Nice, this also works");
		

	}

}
