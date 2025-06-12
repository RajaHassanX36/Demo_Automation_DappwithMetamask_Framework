package com.dappwithmm.qa.utils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import com.dappwithmm.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverListener{
	
	 @Override
	    public void beforeGet(WebDriver driver, String url) {
	        System.out.println("Before navigating to: '" + url + "'");
	    }

	    @Override
	    public void afterGet(WebDriver driver, String url) {
	        System.out.println("Navigated to: '" + url + "'");
	    }

	    @Override
	    public void beforeClick(WebElement element) {
	        System.out.println("Trying to click on: " + element);
	    }

	    @Override
	    public void afterClick(WebElement element) {
	        System.out.println("Clicked on: " + element);
	    }
	    
	    @Override
	    public void beforeGetCurrentUrl(WebDriver driver) {
	        System.out.println("Before getting current URL");
	    }

	   

	    @Override
	    public void beforeGetTitle(WebDriver driver) {
	        System.out.println("Before getting page title");
	    }

	    @Override
	    public void afterGetTitle(WebDriver driver, String result) {
	        System.out.println("Page title is: " + result);
	    }

	    @Override
	    public void afterClose(WebDriver driver) {
	        System.out.println("Browser closed");
	    }


	    @Override
	    public void afterQuit(WebDriver driver) {
	        System.out.println("Test completed");
	    }

	    @Override
	    public void beforeGetWindowHandles(WebDriver driver) {
	        System.out.println("Before getting all window handles");
	    }

	   
	    @Override
	    public void beforeGetWindowHandle(WebDriver driver) {
	        System.out.println("Before getting current window handle");
	    }
	    
	    @Override
	    public void afterGetWindowHandle(WebDriver driver, String result) {
	        System.out.println("✅ Current window handle: " + result);
	    }
	    

	    @Override
	    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
	        System.out.println("Typing into element: " + describeElement1(element) + 
	                           " | Value entered: " + CharSequenceToString(keysToSend));
	    }
	    
	    @Override
	    public void afterWindow(WebDriver.TargetLocator targetLocator, String nameOrHandle, WebDriver driver) {
	        System.out.println("✅ Switched to window: " + nameOrHandle);
	    }


	    private String CharSequenceToString(CharSequence... chars) {
	        StringBuilder sb = new StringBuilder();
	        for (CharSequence cs : chars) {
	            sb.append(cs);
	        }
	        return sb.toString();
	    }

	    private String describeElement1(WebElement element) {
	        try {
	            return "[Tag: " + element.getTagName() + ", ID: " + element.getAttribute("id") + "]";
	        } catch (Exception e) {
	            return "[Element description unavailable]";
	        }
	    }

		@Override
	    public void beforeFindElement(WebDriver driver, By locator) {
	        System.out.println("Trying to find Element By: " + locator);
	    }
		
		
		 @Override
		    public void afterAccept(Alert alert) {
		        System.out.println("✅ Alert was accepted.");
		    }

	    @Override
	    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
	        System.out.println("Found Element By: " + locator);
	    }
	    
	    @Override
	    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
	        System.out.println("⚠️ Exception occurred while calling method: " + method.getName());
	        System.out.println("Target object: " + target);
	        System.out.println("Arguments: " + (args != null ? java.util.Arrays.toString(args) : "[]"));
	        System.out.println("Exception: " + e.getTargetException().getMessage());

	        // Optional: log stack trace or take screenshot
	        e.printStackTrace();
	    }



//
//	    @Override
//	    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
//	        System.out.println("Before getting screenshot as: " + target);
//	    }
//
//	    @Override
//	    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
//	        System.out.println("Screenshot taken");
//	    }
	    
//	    @Override
//	    public void afterGetScreenshotAs(OutputType<X> target, X result) {
//	        System.out.println("✅ Screenshot captured: " + result);
//	    }

	    @Override
	    public void beforeGetText(WebElement element) {
	        System.out.println("Before getting text from: " + element);
	    }

	    @Override
	    public void afterGetText(WebElement element, String text) {
	        System.out.println("Got text: " + text);
	    }

	   
	    
	    
	    
	    // STATE CHECKS
	    @Override
	    public void beforeIsSelected(WebElement element) {
	        System.out.println("Checking if element is selected: " + describeElement1(element));
	    }

	    @Override
	    public void afterIsSelected(WebElement element, boolean result) {
	        System.out.println("Element selected state: " + result + " | " + describeElement1(element));
	    }

	    @Override
	    public void beforeIsEnabled(WebElement element) {
	        System.out.println("Checking if element is enabled: " + describeElement1(element));
	    }

	    @Override
	    public void afterIsEnabled(WebElement element, boolean result) {
	        System.out.println("Element enabled state: " + result + " | " + describeElement1(element));
	    }

	    @Override
	    public void beforeIsDisplayed(WebElement element) {
	        System.out.println("Checking if element is displayed: " + describeElement1(element));
	    }

	    @Override
	    public void afterIsDisplayed(WebElement element, boolean result) {
	        System.out.println("Element displayed state: " + result + " | " + describeElement1(element));
	    }

	   
	    
	    //ATTRIBUTES
	    
	    @Override
	    public void beforeGetAttribute(WebElement element, String name) {
	        System.out.println("Before getting attribute '" + name + "' from element: " + describeElement1(element));
	    }
	    
	    @Override
	    public void afterMaximize(WebDriver.Window window) {
	        System.out.println("✅ Browser window was maximized.");
	    }

	    @Override
	    public void afterGetAttribute(WebElement element, String name, String result) {
	        System.out.println("Attribute '" + name + "' value: '" + result + "' from element: " + describeElement1(element));
	    }

	    @Override
	    public void afterFindElement(WebElement element, By locator, WebElement result) {
	        System.out.println("Child element found using locator: " + locator.toString() +
	                           " in parent element: " + describeElement1(element));
	    }

	    // Optional helper method for cleaner logging
	    private String describeElement(WebElement element) {
	        try {
	            String tag = element.getTagName();
	            String id = element.getAttribute("id");
	            String text = element.getText();
	            return "[Tag: " + tag + ", ID: " + id + ", Text: " + text + "]";
	        } catch (Exception e) {
	            return "[Element description unavailable]";
	        }
	  
	  

	    
	    
	    }}