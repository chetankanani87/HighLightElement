package lib;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {
	public static String CaptureScreenshot(WebDriver driver, String snapshotName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir")+"/snapshots/" + snapshotName + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
			Thread.sleep(500);
			System.out.println("Screenshot Successfully taken.");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
		
		return path;
	}
}
