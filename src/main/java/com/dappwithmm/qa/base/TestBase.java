package com.dappwithmm.qa.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.dappwithmm.qa.utils.PathConstants;
import com.dappwithmm.qa.utils.TestUtils;
import com.dappwithmm.qa.utils.WebEventListener;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
    public static WebEventListener eventListener;

    public TestBase() {
        prop = new Properties();
        try (FileInputStream ip = new FileInputStream(PathConstants.CONFIG_PROPERTIES)) {
            prop.load(ip);
            System.out.println("✅ Loaded config from: " + PathConstants.CONFIG_PROPERTIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    public static void initialization() throws InterruptedException {
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(PathConstants.METAMASK_CRX));
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("ff")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }

        // WebDriver event listener
        eventListener = new WebEventListener();
        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(eventListener);
        driver = decorator.decorate(driver);

        tdriver.set(driver); // Thread safety

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtils.IMPLICIT_WAIT));
        driver.get(prop.getProperty("url"));

        // Open MetaMask tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(prop.getProperty("MMurl"));
    }

    @BeforeSuite
    public void cleanAllureResults() {
        try {
            FileUtils.cleanDirectory(new File(PathConstants.ALLURE_RESULTS));
            System.out.println("✅ Cleaned Allure results folder: " + PathConstants.ALLURE_RESULTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void openHtmlReport() {
        // Open Extent Report
        try {
            File htmlFile = new File(PathConstants.EXTENT_REPORT_HTML);
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.out.println("❌ Extent Report not found: " + htmlFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Open Allure Report

		try {
			ProcessBuilder builder = new ProcessBuilder(
					PathConstants.ALLURE, "serve",
					PathConstants.ALLURE_RESULTS);
			builder.inheritIO();
			Process process = builder.start();
			process.waitFor();
			// Allure serve will automatically open report in browser
			System.err.println("Allure report serve successfully");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("Failed to serve Allure report");

		}
	}
}

