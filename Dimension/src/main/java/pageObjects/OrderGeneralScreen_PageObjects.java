package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderGeneralScreen_PageObjects {
	
	
	@FindBy(id = "btnProcessAction-btnInnerEl")
	public static WebElement processActions;
	
	@FindBy(id = "ext-gen1083")
	public static WebElement mainInfoText;
	
	@FindBy(id = "txt_orderno-inputEl")
	public static WebElement orderNo;
	
	@FindBy(id = "colourno-titleEl")
	public static WebElement screenValidataion;
	
}
