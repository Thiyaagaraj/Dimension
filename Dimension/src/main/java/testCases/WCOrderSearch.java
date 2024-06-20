package testCases;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.OrderGeneralScreen_PageObjects;
import pageObjects.OrderSearch_PageObjects;
import pageObjects.WorkCenterOrder_PageObjects;

public class WCOrderSearch extends CommonFunctions {

	static Logger logger = Logger.getLogger(WCOrderSearch.class);

	@Test(dataProvider = "OrderData", dataProviderClass = CommonFunctions.class)
	public void orderSearch(String OrderNo, String SubOrderNo) throws InterruptedException {
		testCase = extentReport.createTest("Order Search");
		testCase.log(Status.INFO, "Navigating to Order Search Dialog");
		logger.info("Navigating to Order Search Dialog");

		PageFactory.initElements(driver, WorkCenterOrder_PageObjects.class);
		WorkCenterOrder_PageObjects.searchBtn.click();

		driver.switchTo().defaultContent(); 
		driver.switchTo().frame("a1_IFrame");

		PageFactory.initElements(driver, OrderSearch_PageObjects.class);
		PageFactory.initElements(driver, OrderGeneralScreen_PageObjects.class);
		OrderSearch_PageObjects.searchOkBtn.click();
		testCase.log(Status.INFO, "Order Search Ok button clicked");
		logger.info("Order Search OK button clicked");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("id_798853033_IFrame");
		driver.switchTo().frame("Tab1_IFrame");
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		String order = OrderNo +"/"+SubOrderNo; 
		WebElement orderNo =
				driver.findElement(By.xpath("//span[text()=" + "\"" + order + "\"" + "]")); 
		orderNo.click();

		testCase.log(Status.INFO, "Order No Link clicked");
		logger.info("Order No Link clicked");
		
		String orderFrameID = "tab_"+OrderNo+"_"+SubOrderNo+"_IFrame";
			
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("id_798853033_IFrame");
		driver.switchTo().frame(orderFrameID);
		driver.switchTo().frame("tabGeneral_IFrame");
		
		logger.info("Switched to General Frame");
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
