package BasePageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonHome {

	private WebDriver driver;

	private By deliverToLink = By.id("nav-global-location-popover-link");

	private By countryDropDownList = By.xpath("//select[@id='GLUXCountryList']");

	private By countryLabel = By.id("glow-ingress-line2");

	private By countryDone = By.name("glowDoneButton");

	private By itemSearchBox = By.id("twotabsearchtextbox");

	private By submitSearchButton = By.id("nav-search-submit-button");

	private By displayImageLink = By.xpath("//img[@alt='Amazon.com eGift Card']");

	private By selectedImage = By.xpath("//img[@alt='Amazon.com gift card design']");

	private By previewImage =By.xpath("//img[@src='https://m.media-amazon.com/images/G/01/gc/designs/livepreview/a_generic_white_10_us_noto_email_v2016_us-main._CB627448186_.png']");

	private By selectedDesign = By.xpath("//*[@id=\"gc-mini-picker-design-swatch-image-2\"]");
	

	private By giftAmountChanged = By.xpath("//*[@id=\"gc-mini-picker-amount-3\"]");
	
	
	private By displayAmount = By.xpath("//span[@id='gc-live-preview-amount']");

	public AmazonHome(WebDriver driver) {
		this.driver = driver;
       
	}

	public void amazonSelectDeliverTo() {

		driver.findElement(deliverToLink).click();

	}

	public void selectCountry(String country) {

		WebElement dropDown = driver.findElement(countryDropDownList);
		Select select = new Select(dropDown);
		select.selectByVisibleText(country);

	}

	public void closeCountryList() {

		driver.findElement(countryDone).click();

	}

	public String getCountryText() {
		return driver.findElement(countryLabel).getText();

	}

	public void enterSearch(String item) {
		driver.findElement(itemSearchBox).sendKeys(item);

	}

	public void clickAmazonSearch() {

		driver.findElement(submitSearchButton).click();

	}

//	public By getAmazonGiftImage() {
//
//		return displayImageLink;
//	}
	
	public WebElement getAmazonGiftImage() {
		
				return driver.findElement(displayImageLink);
			}
	
	public WebElement getPreviwLinkOnGiftImage() {

		return driver.findElement(selectedImage);
	}


	public WebElement selectImageDesign() {

		return driver.findElement(selectedDesign);
	}

	public By getPreviwedGiftImage() {

		return previewImage;
	}
	
	
	

	public WebElement getAmazonGiftAmount() {

		return driver.findElement(giftAmountChanged);

	}
//	public WebElement getAmazonGiftAmount() {
//
//				return amountSelectElement;
//
//			}

	public String getAmazonGiftAmountValue() {

		return driver.findElement(giftAmountChanged).getText();


	}
	
	

	public String getDisplayAmountValue() {

		return driver.findElement(displayAmount).getText();

	}
}
