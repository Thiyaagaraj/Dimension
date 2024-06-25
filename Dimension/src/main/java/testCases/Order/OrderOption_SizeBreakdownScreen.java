package testCases.Order;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.OrderGeneralScreen_PageObjects;
import pageObjects.WorkCenterOrder_PageObjects;

public class OrderOption_SizeBreakdownScreen extends CommonFunctions {

	static Logger logger = Logger.getLogger(OrderOption_SizeBreakdownScreen.class);


	@Test(dataProvider = "OrderData", dataProviderClass = CommonFunctions.class)
	public void optionSizeBreakdownScreen(String OrderNo, String SubOrderNo) {

		testCase = extentReport.createTest("Order Option Size Breakdown Screen");
		testCase.log(Status.INFO, "Order Option Size Breakdown Screen");
		logger.info("Order Option Size Breakdown Screen");
		PageFactory.initElements(driver, WorkCenterOrder_PageObjects.class);

		String orderFrameID = "tab_"+OrderNo+"_"+SubOrderNo+"_IFrame";

		driver.switchTo().frame("id_798853033_IFrame");
		driver.switchTo().frame(orderFrameID);
		
		WorkCenterOrder_PageObjects.optionSize.click();
		
		driver.switchTo().frame("tabPositions_IFrame");
		PageFactory.initElements(driver, OrderGeneralScreen_PageObjects.class);
		String verify = OrderGeneralScreen_PageObjects.screenValidataion.getText();
		
		if(verify.equalsIgnoreCase("Option no.")) {
			testCase.log(Status.PASS, "Order Option/Size Breakdown screen is loaded succesfully");
		}else {
			testCase.log(Status.FAIL, "Order Option/Size Breakdown screen is NOT loaded succesfully");
		}
		driver.switchTo().defaultContent();
		
	}
}
