package commonFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/*import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;*/
import pageObjects.Dashboard_PageObjects;
import pageObjects.Login_PageObjects;

public class CommonFunctions {

	public static Properties properties=null;
	public static WebDriver driver=null;
	public static ExtentReports extentReport;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest testCase;
    public static WebDriverWait wait;
	
	Logger logger = Logger.getLogger(CommonFunctions.class);
	String [][] data=null;
	
	@DataProvider(name="loginData")
	public String[][] loginDataProvider() throws IOException{
		
		String[][] data=getExcelData("C:\\Users\\lt\\eclipse-workspace\\Dimension\\TestData\\TestData.xlsx","Login");
		return data;
	}
	
	@DataProvider(name="OrderData")
	public String[][] orderDataProvider() throws IOException{
		
		String[][] data=getExcelData("C:\\Users\\lt\\eclipse-workspace\\Dimension\\TestData\\TestData.xlsx","WorkCenter_Order");
		return data;
	}
	
	@DataProvider(name="SubConData")
	public String[][] subConDataProvider() throws IOException{
		
		String[][] data=getExcelData("C:\\Users\\lt\\eclipse-workspace\\Dimension\\TestData\\TestData.xlsx","SubContractor");
		return data;
	}
	
	
	public String[][] getExcelData(String fileName, String sheetName) throws IOException {
		//String excelData = null;
        FileInputStream excel = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(excel);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        String[][] loginData = new String[noOfRows - 1][noOfColumns];

        for(int i = 0; i < noOfRows - 1; ++i) {
            for(int j = 0; j < noOfColumns; ++j) {
                DataFormatter dataformat = new DataFormatter();
                loginData[i][j] = dataformat.formatCellValue(sheet.getRow(i + 1).getCell(j));
            }
        }
        workbook.close();
        excel.close();
        return loginData;
	}

	public Properties loadPropertyFile() throws IOException {

		FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
		properties = new Properties();
		properties.load(fileInputStream);
		return properties;
	}
	
	public void takeScreenshot(String fileWithPath) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File sourceFile = (File)screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("imagePath");
        FileHandler.copy(sourceFile, destinationFile);
    }
	
	public void login(String userName, String passWord) {
        logger.info("Logging to the Dimension Application");
        testCase.log(Status.INFO, "Login Initiated");
        PageFactory.initElements(driver, Login_PageObjects.class);
        Login_PageObjects.userName.sendKeys(userName);
        Login_PageObjects.passWord.sendKeys(passWord);
        Login_PageObjects.signIn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
	
	public void logOut() {
		testCase.log(Status.INFO, "Logout Initiated");
        PageFactory.initElements(driver, Dashboard_PageObjects.class);
        Dashboard_PageObjects.settingsLink.click();
        logger.info("Navigated to Setting Link");
        Dashboard_PageObjects.signOut.click();
        logger.info("Logout button clicked");
        testCase.log(Status.INFO, "Logged Out successfully");
	}
	
	public static String dataBaseConnection(String number) throws ClassNotFoundException, SQLException {
		
		String dbBookingID = null;
		
		Class.forName("interbase.interclient.Driver");
		
		Connection connection= DriverManager.getConnection("jdbc:interbase://192.168.2.165:3051/C:/DB/DIM3.0/DIMENSION.IB","TEXDBA","texdba");

        Statement statement= connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * from epo_subcon_booking esb WHERE esb.sys_id ='1' AND esb.booking_id ="+number);
        
        //System.out.println(result);
				
		while(result.next()) { 
			dbBookingID =result.getString(3);
		}
		return dbBookingID;
	}

	@BeforeSuite
	public void launchBrowser() throws IOException {
		extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("./ExtentReport.html");
        extentReport.attachReporter(sparkReporter);
		logger.info("Application Test Execution Begins");
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		logger.info("Loading the Property File");
		loadPropertyFile();
		logger.info("Property File loaded successfully");
		String browser=properties.getProperty("browser");
		String url=properties.getProperty("url");
		//String driverLocation=properties.getProperty("DriverLocation");
		logger.info("Browser is :"+browser);
		logger.info("URL is :"+url);
		//logger.info("Driver Location is :"+driverLocation);
		if(browser.equalsIgnoreCase("chrome")) {
			logger.info("Launching chrome");
			//System.setProperty("Webdriver.chrome.driver", driverLocation);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firfox")) {
			logger.info("Launching Firfox");
			//System.setProperty("Webdriver.gecko.driver", driverLocation);
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		logger.info("Navigating to Dimension");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	@AfterSuite
	public void tearDown() {
		logger.info("Logging out from the Dimension");
		logOut();
		logger.info("Execution done and closing the browser");
		driver.quit();
		extentReport.flush();
	}

}
