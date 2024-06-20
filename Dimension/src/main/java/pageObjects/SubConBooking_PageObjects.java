package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubConBooking_PageObjects {
		
	@FindBy(id = "ImgbtnSubConBooking-textEl")
	public static WebElement subConBooking;
	
	@FindBy(id = "txtRemark-inputEl")
	public static WebElement subConRemarks;
	
	@FindBy(id = "btnAddSubCon")
	public static WebElement addSubConBtn;
	
	@FindBy(id = "txtSubContractor-inputEl")
	public static WebElement txtAddSubCon;
	
	@FindBy(id = "chk_2_1")
	public static WebElement knitingOfPanelChkBox;
	
	@FindBy(id = "imgSaveClose-btnInnerEl")
	public static WebElement saveAndClose;
	
	@FindBy(id = "cmbBookingNo-inputEl")
	public static WebElement subConBookingNo;
	
	@FindBy(id = "imgExit-btnInnerEl")
	public static WebElement closeBtn;
	
}
