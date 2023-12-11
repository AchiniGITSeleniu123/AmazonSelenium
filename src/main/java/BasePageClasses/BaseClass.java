package BasePageClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseClass {

	protected WebDriver driver;

	protected Properties config;

	@BeforeClass
	public void setup() throws IOException {
		config = new Properties();
		FileInputStream configFile = new FileInputStream("config.properties");
		config.load(configFile);

		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chrome_new\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		GoogleSearchPage google = new GoogleSearchPage(driver);
		google.enterSearch("Amazon");
		google.clickSearchButton();
		google.clickSearchResult();
		driver.navigate().refresh();

	}

	 @AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
