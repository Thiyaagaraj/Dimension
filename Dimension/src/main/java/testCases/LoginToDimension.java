package testCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Login_PageObjects;

public class LoginToDimension extends CommonFunctions {

	static Logger logger = Logger.getLogger(LoginToDimension.class);

	@Test(dataProvider = "loginData", dataProviderClass = CommonFunctions.class)
	public void verifyLogin(String scenario, String userName, String passWord) throws IOException, InterruptedException {
		String Validations;
		if(scenario.equalsIgnoreCase("Valid")) {
			testCase = extentReport.createTest("Validation of Login");
			testCase.log(Status.INFO, "Valid Credentials");

			logger.info("Login Function With Valid Credentials");

			login(userName, passWord);
			Validations = driver.getTitle();

			testCase.log(Status.INFO, "Actual title: " + Validations);
			testCase.log(Status.INFO, "Expected title: Login to SpeedStep Dimension");
			testCase.log(Status.INFO, "Verification of Actual and Expected title");

			Assert.assertEquals(Validations, "Login to SpeedStep Dimension");

			testCase.log(Status.PASS, "Valid Credentials is Passed");

		} else if (scenario.equals("Invalid")) {
			logger.info("Login Function With Invalid Credentials");

			testCase = extentReport.createTest("Validation of Login With Invalid Credentials");
			testCase.log(Status.INFO, "Invalid Credentials");

			login(userName, passWord);
			// Thread.sleep(1000L);
			Validations = Login_PageObjects.invalid.getText();

			testCase.log(Status.INFO, "Actual message: " + Validations);
			testCase.log(Status.INFO, "Expected message: Invalid email or password");
			testCase.log(Status.INFO, "Verification of Actual and Expected message");

			takeScreenshot("./invalid.png");
			testCase.addScreenCaptureFromPath("./invalid.png");

			Login_PageObjects.inValidOkBtn.click();

			logger.info("Message Box - Ok Button Clicked");

			Login_PageObjects.userName.clear();
			Login_PageObjects.passWord.clear();

			Assert.assertEquals(Validations, "Invalid email or password");

			testCase.log(Status.PASS, "Invalid Credentials is Passed");
		}
	}
}
