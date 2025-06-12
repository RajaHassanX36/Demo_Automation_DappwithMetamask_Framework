package com.dappwithmm.qa.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.dappwithmm.qa.base.TestBase;

import io.qameta.allure.Step;

public class Swap extends TestBase {

	// Signin button element
	@FindBy(id = "send2")
	WebElement Signinbtn;

	// connect button
	@FindBy(xpath = "//span[text()='Connect']")
	WebElement connectBtn;

	// other wallet button
	@FindBy(xpath = "//span[text()='Other wallets']")
	WebElement otherwalletBtn;

	// select metamask button
	@FindBy(xpath = "/html/body/div[3]/div/div/div/div/div[4]/div[1]/div/div[1]")
	WebElement metamaskbtn;

	public Swap() {
		PageFactory.initElements(driver, this);

	}

	@Step("click on connect button at dapp")
	public void ClickOnConnectbutton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(connectBtn));
		connectBtn.click();
	}
	
	@Step("select other wallet option at dapp")
	public void ClickOnOtherwalletbutton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(otherwalletBtn));
		otherwalletBtn.click();
	}

	@Step("select metamask wallet option at dapp")
	public void selectmetamask() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(metamaskbtn));
		metamaskbtn.click();
	}
}
