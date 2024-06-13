package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Dashboard_PageObjects;
import pageObjects.WorkCenterOrder_PageObjects;

public class WorkcenterOrder extends CommonFunctions {
	
	static Logger logger = Logger.getLogger(WorkcenterOrder.class);
	
	@Test(dataProvider = "OrderData", dataProviderClass = CommonFunctions.class)
	public void workCenterOrder(String OrderNo, String SubOrderNo) {
		
		testCase = extentReport.createTest("WorkCenter Order");
		testCase.log(Status.INFO, "Navigated to Workcenter Order");
		logger.info("Navigated to Workcenter Order");
        PageFactory.initElements(driver, Dashboard_PageObjects.class);
        Dashboard_PageObjects.dashboardExpand.click();
        Dashboard_PageObjects.workCenterOrder.click();
        
        driver.switchTo().frame("id_798853033_IFrame");
        driver.switchTo().frame("Tab1_IFrame");
        
        logger.info("Workcenter objects initialized");
        PageFactory.initElements(driver, WorkCenterOrder_PageObjects.class);
        WorkCenterOrder_PageObjects.orderInput.sendKeys(OrderNo);
        WorkCenterOrder_PageObjects.subOrderInput.sendKeys(SubOrderNo);
        WorkCenterOrder_PageObjects.directSearchBtn.click();
        logger.info("Order number entered and order details are loaded");
        testCase.log(Status.PASS, "Entered Order details are loaded successfully");
        
        driver.switchTo().defaultContent();
	}

}
