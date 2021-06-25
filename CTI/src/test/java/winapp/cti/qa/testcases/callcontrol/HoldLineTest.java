package winapp.cti.qa.testcases.callcontrol;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.ACDPage;
import winapp.cti.qa.pages.CallControlPage;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.pages.OzekiPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class HoldLineTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public HoldLineTest() {
		super();
	}
	
	public void performSetup(String reportTitle) {
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest(reportTitle);
		
		//Initialize PageFactories
		System.out.println(constantVariables.reportMessage);
		reportLogger.log(LogStatus.INFO, constantVariables.reportMessage);
		
		//Setup PageFactories for the Ozeki Application
		eDriver = initializeApplication("Ozeki", "1");
		ozekiPage = new OzekiPage(eDriver, reportLogger);
		
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
		excelMethods.setSheetName("Hold Call");
		column = 5;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void holdLineTest(String active, String ozekiNumber, String phoneNumber, String phoneNumberButton, String finalStatus, String dataRow) {
		System.out.println("@Test - holdLineTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		excelMethods.setDataTableCell("", iteration, column);
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup("TC53077-US52713 Hold Call from: " + phoneNumber);
			
			//Register the Ozeki Phone
			if (iteration == 1) {
				//Register the Ozeki Phone
				ozekiPage.registerOzeki();
				
				//Pause the script for a bit
				ozekiPage.waitForRegistration();
			}
			
			//Enter the phone number & perform the call
			ozekiPage.performCallField(phoneNumber);
			
			//Check if the call status is set to 'ring'
			checkpoint = phoneControlPage.verifyIncomingCall(checkpoint, ozekiNumber, phoneNumber);
			
			//Answer the call
			callControlPage.clickAnswerButton();
			
			//Check if the call status is set to 'connected'
			checkpoint = phoneControlPage.verifyAnsweredCall(checkpoint);
			
			//Hold the call
			callControlPage.clickHoldButton();
			
			//Check if the call status is set to 'hold'
			checkpoint = phoneControlPage.verifyHoldCall(checkpoint);
			
			//Answer the call
			callControlPage.clickUnholdButton();
			
			//Check if the call status is set to 'connected'
			checkpoint = phoneControlPage.verifyAnsweredCall(checkpoint);
			
			//Release the call
			callControlPage.clickReleaseButton();
			
			//Check if the call status is set to 'connected'
			checkpoint = phoneControlPage.verifyReleasedCall(checkpoint);
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
	
}