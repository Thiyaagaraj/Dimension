package testCases.Login;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonFunctions.CommonFunctions;
import pageObjects.Login_PageObjects;

public class Login extends CommonFunctions{

	static Logger logger = Logger.getLogger(Login.class);
	
	@Test(dataProvider = "loginData", dataProviderClass = CommonFunctions.class)
	public void login(String scenario, String userName, String passWord) throws InterruptedException {

		testCase = extentReport.createTest("Login To Dimension");
		testCase.log(Status.INFO, "Login to Dimension");

		logger.info("Login to Dimension");

		login(userName, passWord);
		
		PageFactory.initElements(driver, Login_PageObjects.class);
		try {
			Thread.sleep(3000);
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button-1006-btnIconEl")));
			Login_PageObjects.alreadyLoggedInYes.click();
			logger.info("User logged in Already");
		}
		catch(NoSuchElementException e) {
			logger.info("User Not logged in Already");
		}
		
		logger.info("Login Successfull");
		testCase.log(Status.PASS, "Valid Credentials");		
	}

}
