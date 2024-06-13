package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.OrderDetails_PageObjects;

public class OrderDetails extends CommonFunctions {
	
static Logger logger = Logger.getLogger(OrderDetails.class);
	
	@Test
	public static void workCenterDetails() {
		
		testCase = extentReport.createTest("Order Details");
		testCase.log(Status.INFO, "Navigated to Order Details Screen");
		logger.info("Navigated to Order Details Screen");
		
		driver.switchTo().frame("id_798853033_IFrame");	
		WebElement iFrameElement = driver.findElement(By.id("component-1027"));
		driver.switchTo().frame(iFrameElement);
		driver.switchTo().frame("tabGeneral_IFrame");
		
	    PageFactory.initElements(driver, OrderDetails_PageObjects.class);
	    
	    testCase.log(Status.INFO, "Moved to Process Actions");
		logger.info("Moved to Process Actions");
	    OrderDetails_PageObjects.processActions.click();
	    
	    driver.switchTo().defaultContent();
	}
}
