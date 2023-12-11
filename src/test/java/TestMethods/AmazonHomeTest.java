package TestMethods;

import BasePageClasses.AmazonHome;
import BasePageClasses.BaseClass;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;

public class AmazonHomeTest extends BaseClass {

	private AmazonHome amazonHome;
	
	
	@Test
	public void testSelectGiftCard() throws InterruptedException, IOException {

		amazonHome = new AmazonHome(driver);
		amazonHome.amazonSelectDeliverTo();
		amazonHome.selectCountry("Sweden");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		amazonHome.closeCountryList();
        driver.navigate().refresh();

		SoftAssert verifyCountryAssert = new SoftAssert();

		String expectedCountry = "Sweden";

		String actualCountry = amazonHome.getCountryText();
		
        //Verification of "DeliverToCountry"
		
		verifyCountryAssert.assertEquals(actualCountry, expectedCountry, "mismatch of selected country and visible country");
		verifyCountryAssert.assertAll();
		
		//Select Gift Card
		
		amazonHome.enterSearch("e gift card Amazon");
		amazonHome.clickAmazonSearch();
		WebElement giftImage = amazonHome.getAmazonGiftImage();
		giftImage.click();
		WebElement selectedDesign = amazonHome.selectImageDesign();
		selectedDesign.click();
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		//Get screen shots of comparing images
		
		File f = selectedDesign.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(f, new File(System.getProperty("user.dir") +"\\Images\\image2.png"));


		WebElement selectedPreviewDesign = amazonHome.getPreviwLinkOnGiftImage();
		File f2 = selectedPreviewDesign.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(f2, new File(System.getProperty("user.dir") +"\\Images\\image1.png"));
		BufferedImage expectedImage=readImage("image1.png");
		BufferedImage actualImage=readImage("image2.png");
		
		//Verification of "Updated Images"
		//compareImages(expectedImage,actualImage);
		
		
		//Verification of "Updated Amounts"
		WebElement selectedAmount = amazonHome.getAmazonGiftAmount();
		selectedAmount.click();

		String expectedAmount = amazonHome.getAmazonGiftAmountValue() + ".00";

		String actualAmount = amazonHome.getDisplayAmountValue();

		SoftAssert verifyAmount = new SoftAssert();
		verifyAmount.assertEquals(actualAmount, expectedAmount, "mismatch of selected amount and visible amount");
		verifyAmount.assertAll();
	}



	
	public static BufferedImage readImage(String imageName) throws IOException {
		BufferedImage rImage = ImageIO.read(new File(System.getProperty("user.dir") +"\\Images\\"+imageName));
		return rImage;

	}
	public static void compareImages(BufferedImage expectedImage, BufferedImage actualImage ) throws IOException {

		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		Assert.assertFalse(diff.hasDiff(),"Images are Same");
	}

}
