package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_PageObjects {
	
	@FindBy(id = "txt_email-inputEl")
	public static WebElement userName;
	
	@FindBy(id = "txt_pass-inputEl")
	public static WebElement passWord;
	
	@FindBy(id = "btn_ok")
	public static WebElement signIn;
	
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	    public static WebElement successfulLogin;
	
	@FindBy(xpath = "//div[text()='Invalid email or password']")
	public static WebElement invalid;
	
	@FindBy(xpath = "//a[@id='button-1005']")
	public static WebElement inValidOkBtn;
	
	@FindBy(id = "button-1006-btnIconEl")
	public static WebElement alreadyLoggedInYes;
	
	@FindBy(id = "imgCont")
	public static WebElement sessionTimeout;
}
