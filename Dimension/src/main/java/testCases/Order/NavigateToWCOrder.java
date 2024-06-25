package testCases.Order;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Dashboard_PageObjects;

public class NavigateToWCOrder extends CommonFunctions {

	static Logger logger = Logger.getLogger(NavigateToWCOrder.class);

	@Test
	public void navigatingToWCOrder() {
		testCase = extentReport.createTest("WorkCenter Order");
		testCase.log(Status.INFO, "Navigating to Workcenter Order");
		logger.info("Navigating to Workcenter Order");
		
		PageFactory.initElements(driver, Dashboard_PageObjects.class);
		Dashboard_PageObjects.dashboardExpand.click();
		Dashboard_PageObjects.workCenterOrder.click();
		
		testCase.log(Status.INFO, "Navigated to Workcenter Order");
		logger.info("Navigated to Workcenter Order");	
	}
}
