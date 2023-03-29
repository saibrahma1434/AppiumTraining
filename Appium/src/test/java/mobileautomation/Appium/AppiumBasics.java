package mobileautomation.Appium;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AppiumBasics extends BaseTest {

	@Test
	public void clickOnPreferencesBtn() throws MalformedURLException {
		ConfigureAppium();
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		tearDownServer();
	}

}
