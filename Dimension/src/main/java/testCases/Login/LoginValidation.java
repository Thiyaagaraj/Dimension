package testCases.Login;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Dashboard_PageObjects;
import pageObjects.Login_PageObjects;

public class LoginValidation extends CommonFunctions {

	static Logger logger = Logger.getLogger(LoginValidation.class);

	@Test(dataProvider = "loginVerification", dataProviderClass = CommonFunctions.class)
	public void verifyLogin(String scenario, String userName, String passWord) throws IOException, InterruptedException {
		try {
			String verify;
			if(scenario.equalsIgnoreCase("Valid")) {
				testCase = extentReport.createTest("Validation of Login");
				testCase.log(Status.INFO, "Valid Credentials");

				logger.info("Login Function With Valid Credentials");

				login(userName, passWord);


				PageFactory.initElements(driver, Login_PageObjects.class);

				try { 
					Login_PageObjects.alreadyLoggedInYes.click();
					logger.info("User logged in Already"); 
				} 
				catch(NoSuchElementException e) {
					logger.info("User Not logged in Already");
				}


				logger.info("Login Successfull");
				//wait.wait(3000);

				PageFactory.initElements(driver, Dashboard_PageObjects.class);

				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ext-gen1118")));

				verify = driver.getTitle();
				testCase.log(Status.INFO, "Actual title: " + verify);
				testCase.log(Status.INFO, "Expected title: Dimension");
				testCase.log(Status.INFO, "Verification of Actual and Expected title");

				if(verify.equalsIgnoreCase("Dimension")) {
					testCase.log(Status.PASS, "Login is Successful");
				}else {
					testCase.log(Status.FAIL, "Login is NOT Successful");
				}

				logger.info("Login Validation is Successful"); 
				//				Thread.sleep(3000);
				testCase.log(Status.PASS, "Valid Credentials is Passed");
				logger.info("Valid Credentials is Passed");

				logOut();
				//Thread.sleep(3000);

			} else if (scenario.equals("Invalid")) {
				logger.info("Login Function With Invalid Credentials");

				testCase = extentReport.createTest("Validation of Login With Invalid Credentials");
				testCase.log(Status.INFO, "Invalid Credentials");

				login(userName, passWord);

				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Invalid email or password']")));
				Thread.sleep(1000);
				//wait.wait(1000);
				verify = Login_PageObjects.invalid.getText();
				testCase.log(Status.INFO, "Actual message: " + verify);
				testCase.log(Status.INFO, "Verification of Actual and Expected message");
				logger.info("Verification of Actual and Expected message");

				takeScreenshot("./invalid.png");
				testCase.addScreenCaptureFromPath("./invalid.png");
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Invalid email or password']")));
				Login_PageObjects.inValidOkBtn.click();
				logger.info("Message Box - Ok Button Clicked");

				Login_PageObjects.userName.clear();
				Login_PageObjects.passWord.clear();

				if(verify.equalsIgnoreCase("Invalid email or password")) {
					testCase.log(Status.PASS, "Invalid email or password");
				}else {
					testCase.log(Status.FAIL, "Email or password are Valid");
				}

				logger.info("Invalid Credentials is Passed");
				testCase.log(Status.PASS, "Invalid Credentials is Passed");
			}
		}
		catch (Exception e) {
			Login_PageObjects.sessionTimeout.click();
		}
	}
}
