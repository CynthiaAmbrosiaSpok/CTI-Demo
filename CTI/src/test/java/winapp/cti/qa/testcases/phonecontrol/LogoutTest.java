package winapp.cti.qa.testcases.phonecontrol;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class LogoutTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public LogoutTest() {
		super();
	}
	
	public void performSetup() {
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("Logout of the Spok CTI Client Application");
		
		//Initialize PageFactories
		System.out.println(constantVariables.reportMessage);
		reportLogger.log(LogStatus.INFO, constantVariables.reportMessage);
		
		//Setup PageFactories for the Spok CTI Client Application
		eDriver = initializeApplication("CTI", "1");
		phoneControlPage = new PhoneControlPage(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Logout");
		column = 4;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void TC55182_LoginTest(String active, String userid, String agentStatus, String finalResult, String dataRow) {
		System.out.println("@Test - logoutTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		excelMethods.setDataTableCell("", iteration, column);
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup();
			
			//Login with the desired userid
			if (phoneControlPage.checkLogoutStatus()) {
				phoneControlPage.login(userid);
			}
			
			//Check if the login was successful
			checkpoint = phoneControlPage.verifyLoginStatus(checkpoint);
			
			//Change Agent Status
			if (agentStatus.equalsIgnoreCase("ready")) {
				//Click the 'Ready' button
				phoneControlPage.clickReadyButton();
			} else if (agentStatus.equalsIgnoreCase("not-ready") || agentStatus.equalsIgnoreCase("not ready")) {
				//Click the 'Not-Ready' button
//				phoneControlPage.clickNotReadyButton();
			} else if (agentStatus.equalsIgnoreCase("work")) {
				//Click the 'Work' button
				phoneControlPage.clickWorkButton();
			}
			
			//Check if the agent's status is set correctly
			checkpoint = phoneControlPage.verifyAgentStatus(checkpoint, agentStatus);
			
			//Logout of the Spok CTI Client Application
			phoneControlPage.clickLogout();
			
			//Check if the user's status is on 'Logout'
			checkpoint = phoneControlPage.verifyLogoutStatus(checkpoint);
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}