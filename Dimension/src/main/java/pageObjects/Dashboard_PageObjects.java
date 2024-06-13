package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_PageObjects {
	
	@FindBy(xpath="//a[contains(text(),'Welcome')]")
	public static WebElement settingsLink;
	
	@FindBy(id="logout-textEl")
	public static WebElement signOut;
	
	@FindBy(id="tool-1012")
	public static WebElement dashboardExpand;
	
	@FindBy(partialLinkText = "Workcenter Order")
	public static WebElement workCenterOrder;

}
