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

public class MMdef extends TestBase {

	private WebDriverWait wait;

	public MMdef() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	//------------------------- Metamask Setup Elements -------------------------
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement termsCheckbox;

	@FindBy(xpath = "//button[contains(text(), 'Import an existing wallet')]")
	WebElement importExistingWalletBtn;

	@FindBy(xpath = "//button[contains(text(), 'No thanks')]")
	WebElement noThanksBtn;

	@FindBy(xpath = "//button[contains(text(), 'Confirm Secret Recovery Phrase')]")
	WebElement confirmSeedBtn;

	@FindBy(css = "input[data-testid='create-password-new']")
	WebElement createPasswordFld;

	@FindBy(css = "input[data-testid='create-password-confirm']")
	WebElement confirmPasswordFld;

	@FindBy(xpath = "//button[contains(text(), 'Import my wallet')]")
	WebElement importWalletBtn;

	@FindBy(css = "button[data-testid='onboarding-complete-done']")
	WebElement onboardingDoneBtn;

	@FindBy(css = "button[data-testid='pin-extension-next']")
	WebElement pinNextBtn;

	@FindBy(css = "button[data-testid='pin-extension-done']")
	WebElement pinDoneBtn;

	@FindBy(css = "button[data-testid='not-now-button']")
	WebElement notNowBtn;

	//------------------------- DApp / Connection -------------------------
	@FindBy(xpath = "//button[text()='Create']")
	WebElement createWalletBtn;

	@FindBy(xpath = "//button[contains(text(),'Unlock')]")
	WebElement unlockBtn;

	@FindBy(xpath = "//span[contains(text(),'Account1')]")
	WebElement connectedAccount;

	@FindBy(css = "button[data-testid='confirm-btn']")
	WebElement connectToDAppBtn;

	//------------------------- Network Switching -------------------------
	@FindBy(css = "button[data-testid='network-display']")
	WebElement networkDropdown;

	@FindBy(css = "input[data-testid='network-redesign-modal-search-input']")
	WebElement networkSearchFld;

	@FindBy(css = "button[data-testid='test-add-button']")
	WebElement addNetworkBtn;

	@FindBy(xpath = "//button[contains(text(),'Approve')]")
	WebElement approveBtn;

	@FindBy(xpath = "//p[contains(text(),'Polygon Mainnet')]")
	WebElement polygonNetwork;

	@FindBy(xpath = "//p[contains(text(),'Arbitrum One')]")
	WebElement arbitrumNetwork;

	@FindBy(xpath = "//p[contains(text(),'Binance Smart Chain')]")
	WebElement bscNetwork;
	
	@FindBy(xpath = "//p[contains(text(),'Ethereum Mainnet')]")
	WebElement ethNetwork;

	//------------------------- Helper Methods -------------------------
	private void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	private void type(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element)).clear();
		element.sendKeys(value);
	}

	//------------------------- Actions -------------------------

	@Step("Set up MetaMask wallet using seed and password")
	public void setupMetaMaskWallet(String seedPhrase, String password) {
		click(termsCheckbox);
		click(importExistingWalletBtn);
		click(noThanksBtn);
		enterSeedPhrase(seedPhrase);
		click(confirmSeedBtn);

		type(createPasswordFld, password);
		type(confirmPasswordFld, password);
		if (!termsCheckbox.isSelected()) click(termsCheckbox);
		click(importWalletBtn);

		click(onboardingDoneBtn);
		try {
			click(pinNextBtn);
			click(pinDoneBtn);
		} catch (Exception e) {
			System.out.println("ℹ️ Skipped pinning step.");
		}

		try {
			click(notNowBtn);
		} catch (Exception e) {
			System.out.println("ℹ️ 'Not Now' prompt not shown.");
		}
	}

	@Step("Enter seed phrase")
	public void enterSeedPhrase(String phrase) {
		String[] words = phrase.trim().split("\\s+");
		if (words.length != 12 && words.length != 24)
			throw new IllegalArgumentException("Seed phrase must be 12 or 24 words long");

		for (int i = 0; i < words.length; i++) {
			String xpath = String.format("//input[@data-testid='import-srp__srp-word-%d']", i);
			WebElement wordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			wordInput.clear();
			wordInput.sendKeys(words[i]);
		}
		System.out.println("✅ Seed phrase entered.");
	}

	@Step("Switch to MetaMask tab")
	public void switchToMetaMaskTab() {
		System.out.println("🔍 Looking for MetaMask tab...");
		boolean found = wait.until(driver -> {
			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().toLowerCase().contains("metamask")) return true;
			}
			return false;
		});
		if (!found) throw new IllegalStateException("❌ MetaMask tab not found.");
	}

	@Step("Switch to DApp tab")
	public void switchToDAppTab() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (!driver.getCurrentUrl().startsWith("chrome-extension://")) return;
		}
		throw new IllegalStateException("❌ DApp tab not found.");
	}

	@Step("Connect DApp with MetaMask")
	public void connectDAppWithMetaMask() {
		driver.navigate().refresh();
		System.out.println("🔄 Page refreshed.");
		click(connectToDAppBtn);
	}

	@Step("Open network dropdown")
	public void openNetworkDropdown() {
		driver.navigate().refresh();
		click(networkDropdown);
	}

	@Step("Add and approve network: {networkName}")
	public void addNetwork(String networkName) {
		type(networkSearchFld, networkName);
		click(addNetworkBtn);
		click(approveBtn);
	}

	@Step("Switch to Polygon network")
	public void switchToPolygon() {
		click(polygonNetwork);
	}

	@Step("Switch to Arbitrum network")
	public void switchToArbitrum() {
		click(arbitrumNetwork);
	}

	@Step("Switch to BSC network")
	public void switchToBSC() {
		click(bscNetwork);
	}
	
	@Step("Switch to ETH network")
	public void switchToETH() {
		click(ethNetwork);
	}
}




