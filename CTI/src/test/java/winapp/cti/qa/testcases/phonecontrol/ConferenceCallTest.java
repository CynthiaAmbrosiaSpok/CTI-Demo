package winapp.cti.qa.testcases.phonecontrol;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.CallControlPage;
import winapp.cti.qa.pages.OzekiPage;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class ConferenceCallTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public ConferenceCallTest() {
		super();
	}
	
	public void initializeReport() {
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("TC64730-US52732 Conference Call");
		
		//Initialize PageFactories
		System.out.println(constantVariables.reportMessage);
		reportLogger.log(LogStatus.INFO, constantVariables.reportMessage);
	}

	public void initializeApplications() {
		System.out.println("Initializing 2nd Ozeki Application");
		
		//Initialize the Report
		initializeReport();
		
		//Setup reference to the '2nd Ozeki Application' data sheet
		excelMethods.setSheetName("Load Second Ozeki App");
		column = 9;
		
		//Retrieve '2nd Ozeki Application' data sheet
		String displayName = excelMethods.getDataTableCell(1, 1);
		String userName = excelMethods.getDataTableCell(1, 2);
		String registerName = excelMethods.getDataTableCell(1, 3);
		String password = excelMethods.getDataTableCell(1, 4);
		String domain = excelMethods.getDataTableCell(1, 5);
		String transport = excelMethods.getDataTableCell(1, 6);
		String stunServer = excelMethods.getDataTableCell(1, 7);
		
		//Setup PageFactories for the Ozeki Application
		initializeDriver(constantVariables.ozekiApplication);
		
		try {
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Click 'OK' to bring up the (2nd) Ozeki Application
			eDriver.findElement(By.name("OK")).click();
			
			//Pause the script for a bit
			genMethods.waitFor(3);
		} catch (Exception e) {
			System.out.print("");
		}
		
		//Setup PageFactories for the Spok CTI Client Application
		eDriver = initializeApplication("CTI", "1");
		phoneControlPage = new PhoneControlPage(eDriver, reportLogger);
		
		//Setup PageFactories for the Ozeki Application
		eDriver = initializeApplication("Ozeki", "1");
		ozekiPage = new OzekiPage(eDriver, reportLogger);
		
		//Enter a new Ozeki phone account
		ozekiPage.enterNewOzekiAccount(displayName, userName, registerName, password, domain, transport, stunServer);
		
		//Register the new Ozeki phone account
		ozekiPage.registerOzeki();
		
		//Check if the Ozeki Application was properly brought up
		eDriver = initializeApplication("Ozeki", "2");
		setupOzekiPageFactory();
		checkpoint = loadingAppsPage.verifyOzekiApplication(checkpoint, constantVariables.ozekiApplicationTitle);
		
		//Set the Excel Sheet back to the relevant sheet
		excelMethods.setSheetName("Transfer Call");
		column = 8;
		
		//Change the variable to 'false' to prevent another Ozeki Application bringing brought up
		bringupOzeki = false;
		
		//Assert all checkpoints
		checkpoint.assertAll();
	}
	
	public void performSetup() {
		//Initialize the Report
		initializeReport();
		
		//Setup PageFactories for the Spok CTI Client Application
		eDriver = initializeApplication("CTI", "1");
		phoneControlPage = new PhoneControlPage(eDriver, reportLogger);
		callControlPage = new CallControlPage(eDriver, reportLogger);
		
		//Setup PageFactories for the Ozeki Application
		eDriver = initializeApplication("Ozeki", "2");
		ozekiPage = new OzekiPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Transfer Call");
		column = 8;
		
		ozekiApplicationTracker = 1;
		bringupOzeki = true;
	}
	
	@AfterClass
	public void afterClass() {
		//Close the second Ozeki application
		eDriver = initializeApplication("Ozeki", "1");
		eDriver.close();
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void transferCall(String active, String ozekiNumber, String ozekiNumber2, String phoneNumber, String phoneNumberButton, String callType, String ozekiStatus, String finalStatus, String dataRow) {
		System.out.println("@Test - initializeApplications()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		excelMethods.setDataTableCell("", iteration, column);
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup 2nd Ozeki Application
			if (bringupOzeki) {
				initializeApplications();
			} else {
				performSetup();
			}
			
			//Switch to the first Ozeki Application
			eDriver = initializeApplication("Ozeki", "1");
			ozekiPage = new OzekiPage(eDriver, reportLogger);
			
			//Enter the phone number & perform the call
			ozekiPage.performCallField(phoneNumber);
			
			//Switch to the second Ozeki Application
			eDriver = initializeApplication("Ozeki", "2");
			ozekiPage = new OzekiPage(eDriver, reportLogger);
			
			//Check if the call status is set to 'ring'
			checkpoint = phoneControlPage.verifyIncomingCall(checkpoint, ozekiNumber, phoneNumber);
			
			//Answer the incoming call (from Phone Control)
			phoneControlPage.clickAnswerButton(phoneNumberButton);
			
			//Check if the call status is set to 'connected'
			checkpoint = phoneControlPage.verifyAnsweredCall(checkpoint);
			
			//Setup Conference Call (from Phone Control)
			phoneControlPage.startConferenceCall(ozekiNumber2);
			
			//Check if the call status is set to 'soft_hold', and 'ringback'
			checkpoint = phoneControlPage.verifyOutgoingConferenceCall(checkpoint);
			
			//Answer the transferred call
			ozekiPage.answerCall();

			//Check if the call status is set to 'soft_hold', and 'ringback'
			checkpoint = phoneControlPage.verifyAcceptedConferenceCall(checkpoint);
			
			//Confirm Conference Call (from Phone Control)
			phoneControlPage.clickConferenceButton();
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Check if the Ozeki call status is set to 'Connected'
			checkpoint = ozekiPage.verifyConnectedCallStatus(checkpoint);
			
			//Check if the Conference Call is successfully/connected on the Spok CTI Client
			checkpoint = phoneControlPage.verifySuccessfulConferenceCall(checkpoint);
			
			//Release the call
			phoneControlPage.clickReleaseButton();
			
			//Check if the call status/details is removed
			checkpoint = phoneControlPage.verifyReleasedCall(checkpoint);
			
			//Hangup the transferred call
			ozekiPage.hangoutCall();
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}