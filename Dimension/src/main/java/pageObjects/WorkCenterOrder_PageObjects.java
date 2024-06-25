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
	
	@FindBy(id = "tab-1011-btnInnerEl")
	public static WebElement summaryView;
	
	@FindBy(id = "tab-1024-closeEl")
	public static WebElement orderCloseBtn;
	
	@FindBy(id = "tab-1028-closeEl")
	public static WebElement orderCloseBtnSearch;
	
	@FindBy(id = "tab-1011-btnInnerEl")
	public static WebElement optionSize;
}
