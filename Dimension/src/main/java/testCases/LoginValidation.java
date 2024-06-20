package testCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Login_PageObjects;

public class LoginValidation extends CommonFunctions {

	static Logger logger = Logger.getLogger(LoginValidation.class);

	@Test(dataProvider = "loginVerification", dataProviderClass = CommonFunctions.class)
	public void verifyLogin(String scenario, String userName, String passWord) throws IOException, InterruptedException {
		try {
			String Validations;
		if(scenario.equalsIgnoreCase("Valid")) {
			testCase = extentReport.createTest("Validation of Login");
			testCase.log(Status.INFO, "Valid Credentials");

			logger.info("Login Function With Valid Credentials");

			login(userName, passWord);

			logger.info("Login Successfull");
			Thread.sleep(3000);
			Validations = driver.getTitle();

			testCase.log(Status.INFO, "Actual title: " + Validations);
			testCase.log(Status.INFO, "Expected title: Dimension");
			testCase.log(Status.INFO, "Verification of Actual and Expected title");

			Assert.assertEquals(Validations, "Dimension");
			logger.info("Login Validation is Successful"); 
			Thread.sleep(3000);
			testCase.log(Status.PASS, "Valid Credentials is Passed");
			logger.info("Valid Credentials is Passed");

			logOut();
			Thread.sleep(3000);
			
		} else if (scenario.equals("Invalid")) {
			logger.info("Login Function With Invalid Credentials");

			testCase = extentReport.createTest("Validation of Login With Invalid Credentials");
			testCase.log(Status.INFO, "Invalid Credentials");

			login(userName, passWord);


			//Thread.sleep(3000);
			Validations = Login_PageObjects.invalid.getText();
			Login_PageObjects.inValidOkBtn.click();
			testCase.log(Status.INFO, "Actual message: " + Validations);
			testCase.log(Status.INFO, "Expected message: Invalid email or password");
			testCase.log(Status.INFO, "Verification of Actual and Expected message");
			logger.info("Verification of Actual and Expected message");

			takeScreenshot("./invalid.png");
			testCase.addScreenCaptureFromPath("./invalid.png");


			logger.info("Message Box - Ok Button Clicked");

			Login_PageObjects.userName.clear();
			Login_PageObjects.passWord.clear();

			Assert.assertEquals(Validations, "Invalid email or password");
			logger.info("Invalid Credentials is Passed");
			testCase.log(Status.PASS, "Invalid Credentials is Passed");
		}
		}
		catch (Exception e) {
			Login_PageObjects.sessionTimeout.click();
		}
	}
}
