package winapp.cti.qa.testcases.callcontrol;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.ACDPage;
import winapp.cti.qa.pages.CallControlPage;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class LoginTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public LoginTest() {
		super();
	}
	
	public void performSetup(String reportTitle) {
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest(reportTitle);
		
		//Initialize PageFactories
		System.out.println(constantVariables.reportMessage);
		reportLogger.log(LogStatus.INFO, constantVariables.reportMessage);
		
		//Setup PageFactories for the Spok CTI Client Application
		eDriver = initializeApplication("CTI", "1");
		phoneControlPage = new PhoneControlPage(eDriver, reportLogger);
		callControlPage = new CallControlPage(eDriver, reportLogger);
		acdPage = new ACDPage(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Login");
		column = 4;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void TC55182_LoginTest(String active, String userid, String logout, String finalResult, String dataRow) {
		System.out.println("@Test - loginTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		excelMethods.setDataTableCell("", iteration, column);
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup("TC55182 - US52555 Login to 'Automated Call Distribution (ACD)' with userid = " + userid);
			
			//Check if the application connected, as expected
			checkpoint = phoneControlPage.verifyApplicationConnection(checkpoint);
			
			//Navigate to the 'ACD' Page
			acdPage.navigateToACDTab();
			
			//Check if the user's status is on 'Logout'
			checkpoint = acdPage.verifyLogoutStatus(checkpoint);
			
			//Login with the desired userid
			acdPage.login(userid);
			
			//Pause the script for a bit
			genMethods.waitFor(2);
			
			//Check if the login was successful
			checkpoint = acdPage.verifySuccessfulLogin(checkpoint);
			
			//Logout, if desired
			if (logout.equalsIgnoreCase("y")) {
				acdPage.clickLogout();
			}
			
			//Navigate to the 'Call Control' Page
			callControlPage.navigateToCallControlTab();
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}