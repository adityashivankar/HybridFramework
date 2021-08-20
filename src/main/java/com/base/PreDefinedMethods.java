package com.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.base.PreDefinedMethods;
import com.constant.constant;
import com.util.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PreDefinedMethods {
	{
		PropertyFileReader propReader = new PropertyFileReader();
		predefinedPageProperties = propReader.readPropertyFile(constant.PRECONFIGPROPERTIES, "PreConfiguration");
	}

	public static WebDriver driver;
	Properties predefinedPageProperties;
	WebDriverWait wait;
	PropertyFileReader propReader;
	private final static Logger logger = Logger.getLogger(PreDefinedMethods.class);

	// Method to launch the browser. @browserName to get browser name from
	// properties file.
	public void browserInitialization() {
		String browserName = predefinedPageProperties.getProperty("browserName");
		switch (browserName.toUpperCase()) {
		case "CHROME": {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Chrome browser launch successfully.");
			break;
		}
		case "FIREFOX": {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Firefox browser launch successfully.");
			break;
		}
		case "IE": {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			logger.info("IE browser launch successfully.");
			break;
		}
		}
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
	}

	// Method to close the browser session.
	public void browserClose() {
		driver.close();
		driver.quit();
		logger.info("Closing all instances of browsers.");
	}

	// Method to open the URL.
	public void navigateToURL() {
		driver.get(predefinedPageProperties.getProperty("URL"));
		logger.info("Redirected to the " + predefinedPageProperties.getProperty("URL") + " URL.");
	}

	// Overloaded method to open the URL.
	public void navigateToURL(String NavigateToURL) {
		driver.get(NavigateToURL);
		logger.info("Redirected to the " + NavigateToURL + " URL.");
	}

	// Method is taking two parameters. One for xpath and another to enter text
	// value
	public void enterText(String locator, String propertyFileName, String textValue) {
		getElement(locator, propertyFileName).clear();
		getElement(locator, propertyFileName).sendKeys(textValue);
		logger.info("Sending text: " + textValue + " into element" + locator);
	}

	// Method to click on the locator
	public void clickOnElement(String locator, String propertyFileName) {
		getElement(locator, propertyFileName).click();
		logger.info("Clicked on element: " + locator);
	}

	// Generic method to perform findElement
	public WebElement getElement(String locator, String propertyFileName) {
		// waitForElementToBeVisible(locator, propertyFileName);
		return driver.findElement(getCompleteElement(locator, propertyFileName));
	}

	// Method will return list of web elements
	public List<WebElement> getElements(String locator, String propertyFileName) {
		List<WebElement> listOfElements = driver.findElements(getCompleteElement(locator, propertyFileName));
		return listOfElements;
	}

	// Method is taking two parameters. One to hover on mainmenu and another to
	// click on the submenu.
	public void hoverOnElement(String hoverElement, String subHoverElement, String propertyFileName) {
		Actions act = new Actions(driver);
		WebElement element = getElement(hoverElement, propertyFileName);
		act.moveToElement(element).build().perform();
		logger.info("Hover moved to the element " + hoverElement);
		clickOnElement(subHoverElement, propertyFileName);
	}

	// Method is used to get the text from an element.
	public String getTextofElement(String locator, String propertyFileName) {
		String returnTextValue = getElement(locator, propertyFileName).getText();
		logger.info(returnTextValue + " is the text value of an element.");
		return returnTextValue;
	}

	// Method is used to wait for an element
	public void waitForElementToBeVisible(String element, String propertyFileName) {
		// By abc = getCompleteElement(element, propertyFileName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getCompleteElement(element, propertyFileName)));
	}

	// Method is used to take the screen shot of a page
	public String capatureScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String screenPath = ".//Screenshots//Screenshots" + getCurrentTimeAndDate() + ".jpeg";
		try {
			FileUtils.copyFile(sourceFile, new File(screenPath));
			logger.info("Screenshot captured on location: " + screenPath);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error while capturing screenshot.");
		}
		return screenPath;
	}

	// Method id used to get the current time and date
	public String getCurrentTimeAndDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
		String currentDate = simpleDateFormat.format(new Date());
		logger.info("Current date time is: " + currentDate);
		return currentDate;
	}

	// Method is used to get the element value from the properties file
	public By getCompleteElement(String locator, String propertyFileName) {
		By element = null;
		getDataFromPropertyFile(propertyFileName);
		String locatorType = locator.substring(locator.indexOf("_") + 1).toUpperCase();
		switch (locatorType) {
		case "CLASSNAME":
			element = By.className(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "CSSSELECTOR":
			element = By.cssSelector(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "ID":
			element = By.id(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "LINKTEXT":
			element = By.linkText(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "NAME":
			element = By.name(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "PARTIALLINKTEXT":
			element = By.partialLinkText(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "TAGNAME":
			element = By.tagName(predefinedPageProperties.getProperty(locator).toString());
			break;
		case "XPATH":
			element = By.xpath(predefinedPageProperties.getProperty(locator).toString());
			break;
		default:
			throw new IllegalArgumentException("By type " + locatorType + " is not found.");
		}
		logger.info(element + " :Element is visible on page.");
		return element;
	}

	public WebElement highlightElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='4px solid red'", element);
			Thread.sleep(1000);
			js.executeScript("arguments[0].style.border=''", element);
		} catch (Exception e) {
			logger.error(e);
		}
		return element;
	}

	// Method is used to read the property file
	public Properties getDataFromPropertyFile(String propertyFileName) {
		propReader = new PropertyFileReader();
		return predefinedPageProperties = propReader.readPropertyFile(propertyFileName);
	}

	// Method id used to verify the text of a locator using assert.
	public void verifyText(String actualTextlocator, String ExpectedTextLocator, String propertyFileName) {
		String actualText = getTextofElement(actualTextlocator, propertyFileName);
		Assert.assertEquals(actualText, ExpectedTextLocator);
	}

	// Method is used to check the check box
	public void checkCheckbox(String locator, String propertyFileName) {
		boolean isChecked = getElement(locator, propertyFileName).isSelected();
		if (!isChecked) {
			logger.info("Clicking on the checkbox to select: " + locator);
			clickOnElement(locator, propertyFileName);
		}
	}

	// Method is used to uncheck the check box
	public void uncheckCheckbox(String locator, String propertyFileName) {
		boolean isChecked = getElement(locator, propertyFileName).isSelected();
		if (isChecked) {
			logger.info("Clicking on the checkbox to uncheck: " + locator);
			clickOnElement(locator, propertyFileName);
		}
	}

	// Method is used to select the radio
	public void selectRadioButton(String locator, String propertyFileName) {
		boolean isSelected = getElement(locator, propertyFileName).isSelected();
		if (!isSelected) {
			logger.info("Clicking on the radio button to select:" + locator);
			clickOnElement(locator, propertyFileName);
		}
	}
}
