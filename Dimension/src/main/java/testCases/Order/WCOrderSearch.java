package testCases.Order;


import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import commonFunctions.CommonFunctions;
import pageObjects.OrderGeneralScreen_PageObjects;
import pageObjects.OrderSearch_PageObjects;
import pageObjects.WorkCenterOrder_PageObjects;

public class WCOrderSearch extends CommonFunctions {

	static Logger logger = Logger.getLogger(WCOrderSearch.class);

	@Test
	public void orderSearch() throws InterruptedException {
		testCase = extentReport.createTest("Order Search");
		testCase.log(Status.INFO, "Navigating to Order Search Dialog");
		logger.info("Navigating to Order Search Dialog");

		//driver.switchTo().defaultContent(); 
		driver.switchTo().frame("id_798853033_IFrame");		

		PageFactory.initElements(driver, WorkCenterOrder_PageObjects.class);
		WorkCenterOrder_PageObjects.summaryView.click();

		driver.switchTo().frame("Tab1_IFrame");

		WorkCenterOrder_PageObjects.searchBtn.click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("a1_IFrame");

		PageFactory.initElements(driver, OrderSearch_PageObjects.class);
		PageFactory.initElements(driver, OrderGeneralScreen_PageObjects.class);
		OrderSearch_PageObjects.searchOkBtn.click();
		testCase.log(Status.INFO, "Order Search Ok button clicked");
		logger.info("Order Search OK button clicked"); 
		driver.switchTo().defaultContent();
	
	}
}
