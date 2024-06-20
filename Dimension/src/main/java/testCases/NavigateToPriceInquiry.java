package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Dashboard_PageObjects;

public class NavigateToPriceInquiry extends CommonFunctions {

	static Logger logger = Logger.getLogger(NavigateToPriceInquiry.class);

	public void navigatingToPriceInquiry() {

		PageFactory.initElements(driver, Dashboard_PageObjects.class);
		Dashboard_PageObjects.dashboardExpand.click();
		Dashboard_PageObjects.priceInquiry.click();

		testCase.log(Status.INFO, "Navigated to Price Inquiry");
		logger.info("Navigated to Price Inquiry");
	}


	@Test(dataProvider = "loginData", dataProviderClass = CommonFunctions.class)
	public void login(String scenario, String userName, String passWord) throws	InterruptedException { 
		testCase = extentReport.createTest("Login to Dimension"); 
		testCase.info("Login");
		logger.info("Login");
		login(userName, passWord);
		testCase.info("Login successfull"); 
		logger.info("Login successfull"); 
	}


	@Test
	public void priceInquiry() {
		testCase = extentReport.createTest("Price Inquiry");
		testCase.log(Status.INFO, "Navigating to Price Inquiry");
		logger.info("Navigating to Price Inquiry");

		navigatingToPriceInquiry();

		testCase.log(Status.INFO, "Price Inquiry screen displayed");
		logger.info("Price Inquiry screen displayed");

	}

}
