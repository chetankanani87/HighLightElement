package highLightElement;

import java.io.IOException;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import lib.BrowserDriverUtility;
import lib.EmailWithAttachmentUtility;
import lib.ExtentReportUtility;
import lib.HighlightUtility;
import lib.ScreenshotUtility;

public class HighLightElement {
	WebDriver dr = BrowserDriverUtility.InvokeBrowser("webdriver.chrome.driver",
			"C:\\Chetan\\SeleniumSuite\\WebDrivers\\chromedriver.exe",
			"http://www.facebook.com");
	ExtentReports report = ExtentReportUtility.InvokeExtentReport();;
	ExtentTest logger = report.createTest("Test To Highlight Element");
	String path1, path2, path3, path4;
	WebElement ele;

	@BeforeTest
	public void InvokeBrowser() {
		try {
			path1 = ScreenshotUtility.CaptureScreenshot(dr, "1_MainPage");
			logger.pass("Main Page - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void HighlightEle() {
		try {
			ele = dr.findElement(By.id("email"));
			HighlightUtility.highLightElement(dr, ele);			
			ele.sendKeys("Hello");
			path2 = ScreenshotUtility.CaptureScreenshot(dr, "2_EmailTyped");
			logger.pass("Email textbox highlighted - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path2).build());

			ele = dr.findElement(By.id("pass"));
			HighlightUtility.highLightElement(dr, ele);			
			ele.sendKeys("demo");
			path3 = ScreenshotUtility.CaptureScreenshot(dr, "3_PassTyped");
			logger.pass("Password textbox highlighted - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path3).build());

			ele = dr.findElement(By.xpath("//input[@value='Log In']"));
			HighlightUtility.highLightElement(dr, ele);			
			ele.click();
			path4 = ScreenshotUtility.CaptureScreenshot(dr, "4_LoginBtnClicked");
			logger.pass("Password textbox highlighted - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path4).build());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterTest
	public void tearDown() {
		try {
			EmailWithAttachmentUtility.SendEmail("Element Highlighted - Test Case Passed", "Congratulations dude...!!!", path1, path2, path3, path4, "Screenshots of pages where element is highlighted...!!!");
			report.flush();
			dr.close();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
