package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;

public class PriceInquiryMain extends CommonFunctions{

	static Logger logger = Logger.getLogger(PriceInquiryMain.class);

	@Test(dataProvider = "PriceInquiryData", dataProviderClass = CommonFunctions.class)
	public static void priceInquiryDetails(String OrderNo, String SubOrderNo) {

		testCase = extentReport.createTest("Price Inquiry Details");
		testCase.log(Status.INFO, "Price Inquiry Details Screen");
		logger.info("Price Inquiry Details Screen");

		driver.switchTo().frame("id_798853031_IFrame");
		driver.switchTo().frame("Tab1_IFrame");


		String order = OrderNo +"/"+SubOrderNo; 
		WebElement orderNo = driver.findElement(By.xpath("//span[text()=" + "\"" + order + "\"" + "]")); 
		orderNo.click();
		testCase.log(Status.INFO, "Order Number Clicked");
		logger.info("Order Number Clicked");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("id_798853031_IFrame");
		String orderFrameID = "tab_"+OrderNo+"_"+SubOrderNo+"_IFrame";
		driver.switchTo().frame(orderFrameID);
		driver.switchTo().frame("tabGeneral_IFrame");
		
		String verify = driver.findElement(By.id("txt_order_no-inputEl")).getAttribute("value");
		logger.info("Price Inquiry Order Number Clicked");
		logger.info("verify :"+verify);
		logger.info("OrderNo :"+OrderNo);
		if(verify.equalsIgnoreCase(OrderNo)) {
			testCase.log(Status.PASS, "Price Inquiry Details screen is loaded succesfully");
		}else {
			testCase.log(Status.FAIL, "Price Inquiry Details screen is NOT loaded succesfully");
		}
		logger.info("Price Inquiry Details screen loaded verification done");
		driver.switchTo().defaultContent();
	}


}
