package winapp.cti.qa.testcases.phonecontrol;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.pages.OzekiPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class ReceiveLineTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	String selectCallType;
	
	//Constructor
	public ReceiveLineTest() {
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
	}
	
	//Setup variable(s) and other info for the class
	@Parameters({"callType"})
	@BeforeClass
	public void beforeClass(@Optional("") String callType) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Perform Call");
		column = 7;
		
		selectCallType = callType;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void receiveLineTest(String active, String ozekiNumber, String phoneNumber, String phoneNumberButton, String callType, String holdCall, String finalStatus, String dataRow) {
		System.out.println("@Test - receiveLineTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		excelMethods.setDataTableCell("", iteration, column);
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes") && (selectCallType.equalsIgnoreCase(callType) || selectCallType.equalsIgnoreCase(""))) {
			//Setup the report & PageFactories
			performSetup("TC51659 - US52496 Answer Call from: " + phoneNumber);
			
//			//Register the Ozeki Phone
//			if (iteration == 1) {
//				//Register the Ozeki Phone
//				ozekiPage.registerOzeki();
//				
//				//Wait for the Ozeki Application to register
//				ozekiPage.waitForRegistration();
//			}
			
			//Register the Ozeki Phone
			ozekiPage.registerOzeki();
			
			//Wait for the Ozeki Application to register
			ozekiPage.waitForRegistration();
			
			//Enter the phone number & perform the call
			ozekiPage.performCallField(phoneNumber);
			
			//Check if the call status is set to 'ring'
			checkpoint = phoneControlPage.verifyIncomingCall(checkpoint, ozekiNumber, phoneNumber);
			
			//Answer the call
			phoneControlPage.clickAnswerButton(phoneNumberButton);
			
			//Check if the call status is set to 'connected'
			checkpoint = phoneControlPage.verifyAnsweredCall(checkpoint);
			
			if (holdCall.equalsIgnoreCase("y") || holdCall.equalsIgnoreCase("yes")) {
				//Hold the call
				phoneControlPage.clickHoldButton();
				
				//Check if the call status is set to 'hold'
				checkpoint = phoneControlPage.verifyHoldCall(checkpoint);
				
				//Answer the call
				phoneControlPage.clickAnswerButton(phoneNumberButton);
				
				//Check if the call status is set to 'connected'
				checkpoint = phoneControlPage.verifyAnsweredCall(checkpoint);
			}
			
			//Release the call
			phoneControlPage.clickReleaseButton();
			
			//Check if the call status/details is removed
			checkpoint = phoneControlPage.verifyReleasedCall(checkpoint);
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
	
}