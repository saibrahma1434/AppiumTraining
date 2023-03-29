package mobileautomation.Appium;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {

	public static AppiumDriverLocalService service;
	public static AndroidDriver driver;

	@BeforeClass
	public void ConfigureAppium() {

		setUpServer();
		driver = new AndroidDriver(service.getUrl(), setCapabilities());
	}

	public static void setUpServer() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1").usingPort(4723)
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node")).withArgument(BASEPATH, "/wd/hub")
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE).withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	private static DesiredCapabilities setCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4");
		capabilities.setCapability(MobileCapabilityType.APP,
				"//Users//saibrahma.mutcherla//eclipse-workspace//Appium//src//test//java//resources//ApiDemos-debug.apk");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		return capabilities;
	}

	@AfterClass
	public static void tearDownServer() {

		driver.quit();
		service.stop();
	}

}
