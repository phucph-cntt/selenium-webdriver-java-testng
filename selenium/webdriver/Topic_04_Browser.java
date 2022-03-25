package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_VerifyUrl() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		//Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Step 05
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_VerifyTitle() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		//Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Step 05
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_NavigateFunction() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Step 04
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		//Step 05
		driver.navigate().back();
		//Step 06
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		//Step 07
		driver.navigate().forward();
		//Step 08
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_04_PageSource() {
		//Step 01
		driver.get("http://live.techpanda.org/");
		//Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Step 03
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Login or Create an Account"));
		//Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Step 05
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}