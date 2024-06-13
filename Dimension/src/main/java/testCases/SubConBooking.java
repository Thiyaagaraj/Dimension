package testCases;

import java.sql.SQLException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.SubConBooking_PageObjects;

public class SubConBooking extends CommonFunctions {

	static Logger logger = Logger.getLogger(SubConBooking.class);
	static String remarkText;

	public static String subConBooking() throws InterruptedException {

		driver.switchTo().frame("id_798853033_IFrame");
		WebElement iFrameElement = driver.findElement(By.id("component-1027"));
		driver.switchTo().frame(iFrameElement);
		driver.switchTo().frame("tabGeneral_IFrame");

		PageFactory.initElements(driver, SubConBooking_PageObjects.class);
		SubConBooking_PageObjects.subConBooking.click();

		driver.switchTo().defaultContent();
		driver.switchTo().frame("a2_IFrame");
		logger.info("Entering Remarks");
		remarkText = "Test - Subcontractor Booking";
		SubConBooking_PageObjects.subConRemarks.sendKeys(remarkText);

		testCase.log(Status.INFO, "Remarks entered successfully");
		logger.info("Remarks entered successfully");

		SubConBooking_PageObjects.addSubConBtn.click();
		logger.info("Add Subcontrator Booking button Clicked");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("a9_IFrame");

		SubConBooking_PageObjects.txtAddSubCon.sendKeys("71212"+Keys.TAB);
		logger.info("Subcontrator number entered");

		Thread.sleep(5000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.switchTo().frame("pnlTierView_IFrame");
		SubConBooking_PageObjects.knitingOfPanelChkBox.click();
		logger.info("Knitting of panel Checkbox clicked");


		driver.switchTo().defaultContent(); 
		driver.switchTo().frame("a9_IFrame");

		SubConBooking_PageObjects.saveAndClose.click();
		logger.info("SubCon booking Saved successfully");
		testCase.log(Status.INFO,"SubCon booking Saved successfully");


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.switchTo().defaultContent(); 
		driver.switchTo().frame("a2_IFrame");


		Thread.sleep(5000); 
		String bookingNo =  SubConBooking_PageObjects.subConBookingNo.getAttribute("value");

		logger.info("Subcontractor Booking number " ); 
		testCase.log(Status.INFO,"Subcontractor Booking number ");
		SubConBooking_PageObjects.closeBtn.click();
		driver.switchTo().defaultContent();
		return bookingNo;

	}

	@Test
	public static void newSubConBooking()
			throws InterruptedException, ClassNotFoundException, SQLException {

		testCase = extentReport.createTest("SubContractor Booking");
		testCase.log(Status.INFO, "Add New SubContractor Booking");
		logger.info("Navigated to SubContractor Booking Screen");


		String bookingNumber =subConBooking(); 

		String dbBookingID = dataBaseConnection(bookingNumber);
		//Assert.assertEquals(dbBookingID, bookingNumber);

		if (dbBookingID.equals(bookingNumber)) {
			testCase.log(Status.INFO,"SubContractor Booking No: "+bookingNumber);
			logger.info("SubContractor Booking No: "+bookingNumber); 
			testCase.log(Status.PASS,"SubContractor Booked Successfully and verified in DB");
			logger.info("SubContractor Booked Successfully and verified in DB"); 
		} else {
			testCase.log(Status.FAIL, "SubContractor Booking Failed");
			logger.info("SubContractor Booking Failed"); 
		}

	}
}
