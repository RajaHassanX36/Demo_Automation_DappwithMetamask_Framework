package com.dappwithmm.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import com.dappwithmm.qa.base.TestBase;
import com.dappwithmm.qa.pages.MMdef;
import com.dappwithmm.qa.pages.Swap;
import com.mongodb.MapReduceCommand.OutputType;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Listeners({ com.qa.Listner.TestAllureListener.class })
public class SwapTest extends TestBase{

	
	MMdef mmdef;
	Swap swap;
	
	
	public SwapTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();

		mmdef = new MMdef();
		swap = new Swap();
		Thread.sleep(5000);
		mmdef.setupMetaMaskWallet(prop.getProperty("seed"), prop.getProperty("password"));
		Thread.sleep(5000);
		mmdef.switchToDAppTab();
	

	}
	
	@Test(priority = 1, groups = "Regression", description = ("Test Case Description: connect uniswap with metamask"))
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify user connected dapp with wallet successfully")
	public void TestCase2() throws InterruptedException {
		
	swap.ClickOnConnectbutton();
	swap.ClickOnOtherwalletbutton();
	swap.selectmetamask();
	Thread.sleep(5000);
	mmdef.switchToMetaMaskTab();
	Thread.sleep(3000);
	mmdef.connectDAppWithMetaMask();
	mmdef.switchToDAppTab();
	mmdef.switchToMetaMaskTab();
	mmdef.openNetworkDropdown();
	mmdef.addNetwork("polygon");
	Thread.sleep(3000);
	mmdef.openNetworkDropdown();
	mmdef.switchToETH();
	}
	
	
	@AfterMethod
	public void tearDown() {
	driver.quit();
	}
	
}
