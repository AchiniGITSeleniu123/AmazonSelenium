package BasePageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage extends BaseClass {

	private By google = By.xpath("//textarea[@id='APjFqb']");

	private By amazonLink = By.xpath("//div[@class='yuRUbf']//a/descendant::h3[@class='LC20lb MBeuO DKV0Md']");

	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterSearch(String search) {
		driver.findElement(google).sendKeys(search);

	}

	public void clickSearchButton() {
		driver.findElement(google).sendKeys(Keys.ENTER);
	}

	public void clickSearchResult() {

		driver.findElement(amazonLink).click();
	}
}
