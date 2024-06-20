package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.OrderGeneralScreen_PageObjects;
import pageObjects.WorkCenterOrder_PageObjects;

public class OrderNoInput extends CommonFunctions {
	
	static Logger logger = Logger.getLogger(NavigateToWCOrder.class);
	
		@Test(dataProvider = "OrderData", dataProviderClass = CommonFunctions.class)
		public void orderNoDirectInput(String OrderNo, String SubOrderNo) {
			testCase = extentReport.createTest("Order No Input");
			testCase.log(Status.INFO, "Navigating to Order General Screen");
			logger.info("Workcenter objects initialized");
			PageFactory.initElements(driver, WorkCenterOrder_PageObjects.class);
			PageFactory.initElements(driver, OrderGeneralScreen_PageObjects.class);
			WorkCenterOrder_PageObjects.orderInput.sendKeys(OrderNo);
			WorkCenterOrder_PageObjects.subOrderInput.sendKeys(SubOrderNo);
			logger.info("Order No and Sub Order Number are entered");
			WorkCenterOrder_PageObjects.directSearchBtn.click();
			
			driver.switchTo().defaultContent();
			
			String orderFrameID = "tab_"+OrderNo+"_"+SubOrderNo+"_IFrame";
			driver.switchTo().frame("id_798853033_IFrame");
			driver.switchTo().frame(orderFrameID);
			driver.switchTo().frame("tabGeneral_IFrame");
						
			
			//Order General Screen displayed Validation
			String mainInfo = OrderGeneralScreen_PageObjects.mainInfoText.getText();
			logger.info("Actual Text "+mainInfo);
			logger.info("Expected Text : Main information");
			Assert.assertEquals(mainInfo,"Main information");
			logger.info("Order General screen loaded successfully");
			testCase.log(Status.PASS, "Order General screen loaded successfully");	
			
			driver.switchTo().defaultContent();
			logOut();
		}
}
