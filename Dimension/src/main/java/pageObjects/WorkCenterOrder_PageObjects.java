package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkCenterOrder_PageObjects {
	
	@FindBy(id = "txtKeyF1-inputEl")
	public static WebElement orderInput;
	
	@FindBy(id = "txtKeyF2-inputEl")
	public static WebElement subOrderInput;
	
	@FindBy(id = "imgBtnDirectSearch")
	public static WebElement directSearchBtn;
	
	@FindBy(id = "imgbtnSearch-btnInnerEl")
	public static WebElement searchBtn;
	
}
