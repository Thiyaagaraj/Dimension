package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Dashboard_PageObjects;
import pageObjects.WorkCenterOrder_PageObjects;

public class NavigateToWCOrder extends CommonFunctions {

	static Logger logger = Logger.getLogger(NavigateToWCOrder.class);

	public void navigatingToWCOrder() {

		PageFactory.initElements(driver, Dashboard_PageObjects.class);
		Dashboard_PageObjects.dashboardExpand.click();
		Dashboard_PageObjects.workCenterOrder.click();
		driver.switchTo().frame("id_798853033_IFrame");
		driver.switchTo().frame("Tab1_IFrame");

		testCase.log(Status.INFO, "Navigated to Workcenter Order");
		logger.info("Navigated to Workcenter Order");
	}

	@Test(dataProvider = "loginData", dataProviderClass = CommonFunctions.class)
	public void login(String scenario, String userName, String passWord) throws InterruptedException {
		testCase = extentReport.createTest("Login to Dimension");
		testCase.info("Login"); 
		logger.info("Login");
		login(userName, passWord);
		testCase.info("Login successfull"); 
		logger.info("Login successfull");
	}

	@Test
	public void workCenterOrder() {
		testCase = extentReport.createTest("WorkCenter Order");
		testCase.log(Status.INFO, "Navigating to Workcenter Order");
		logger.info("Navigating to Workcenter Order");

		
		 // PageFactory.initElements(driver, WorkCenterOrder_PageObjects.class);
		  NavigateToWCOrder orderMain = new NavigateToWCOrder();
		 
		orderMain.navigatingToWCOrder(); 
	}
}
