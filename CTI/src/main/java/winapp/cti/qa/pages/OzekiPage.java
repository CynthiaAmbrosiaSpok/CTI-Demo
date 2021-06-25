 package winapp.cti.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.locators.OzekiLocators;
import winapp.cti.qa.util.GeneralMethods;
import winapp.cti.qa.util.ReportLoggerMethods;

public class OzekiPage extends OzekiLocators {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	GeneralMethods genMethods;
	ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OzekiPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	public void enterNewOzekiAccount(String displayName, String userName, String registerName, String password, String domain, String transport, String stunServer) {
		//Output a message to the report & system
		System.out.println("Ozeki Softphone: Create a new SIP Account");
		reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Create a new SIP Account");
		
		//click the 'Add SIP Account' button to bring up the 'add new phone details' page
		addSIPAccountButton.click();
		
		//Enter the 'Display Name' value
		displayNameField.sendKeys(displayName);
		
		//Enter the 'User Name' value
		userNameField.sendKeys(userName);
		
		//Enter the 'Register Name' value
		registerNameField.sendKeys(registerName);
		
		//Enter the 'Password' value
		passwordField.sendKeys(password);
		
		//Enter the 'Domain' value
		domainField.sendKeys(domain);
	
		//Select the 'Transport' value from the drop-down
		transportTypeDropDown.click();
		genMethods.waitFor(2);
		Actions actions = new Actions(eDriver);
		actions.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
		
		//Enter the 'STUN Server' value
		stunServerField.sendKeys(stunServer);
		
		//Click 'OK' to confirm the new phone account
		okButton.click();
	}
	
	public void waitForRegistration() {
		for (int i = 0; i < 15; i++) {
			//Pause the script for a bit
			genMethods.waitFor(3);
			
//			if (registerStatusField.getText().equalsIgnoreCase("registrationsucceeded")) {
//				//Output a message to the report & system
//				System.out.println("Ozeki Softphone: Register of the Ozeki Phone was successful");
//				reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Register of the Ozeki Phone was successful");
//				return;
//			}
		}
	}
	
	public void registerOzeki() {
		if (!registerStatusField.getText().equalsIgnoreCase("registrationsucceeded")) {
			//Output a message to the report & system
			System.out.println("Ozeki Softphone: Registering the Ozeki Phone");
			reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Registering the Ozeki Phone");
			
			dialpadField.sendKeys("");
			dialpadField.clear();
			
			//Register the Ozeki account
			registerButton.click();
			
			//Wait for the registration to complete
			waitForRegistration();
		}
	}
	
	public void performCallField(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Ozeki Softphone: Entering and calling the phone number: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Entering and calling the phone number: " + phoneNumber);
		
		//Clear any input in the dial pad field
		dialpadField.clear();
		
		//Enter the phone number
		dialpadField.sendKeys(phoneNumber);
		
		//Click 'Dial (A)' to call the phone number
		callPhoneNumberAudioButton.click();
	}
	
	public void answerCall() {
		//Output a message to the report & system
		System.out.println("Ozeki Softphone: Answer the incoming call");
		reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Answer the incoming call");
		
		//Answer the incoming call
		answerButton.click();
	}
	
	public void hangoutCall() {
		//Output a message to the report & system
		System.out.println("Ozeki Softphone: Hangup the current call");
		reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Hangup the current call");
		
		//Hangup the current call
		hangupButton.click();
	}
	
	public SoftAssert verifyConnectedCallStatus(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Ozeki Softphone: Checking if the Call State is set to Connected");
		reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Checking if the Call State is set to Connected");
		
		//Initialize Variable(s)
		String successMessage = "Ozeki Softphone: The softphone is properly connected to the call";
		String failureMessage = "Ozeki Softphone: The softphone is not properly connected to the call";
		
		//Check if the Call State/Status is set to 'Connected'
		try {
			softAssert.assertEquals(callStateConnected.isDisplayed(), true);
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} catch (Exception e) {
			softAssert.assertEquals("Not Connected", "Connected");
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "OzekiPage_verifyConnectedCallStatus");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyRingingCallStatus(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Ozeki Softphone: Checking if the Call State is set to Ringing");
		reportLogger.log(LogStatus.INFO, "Ozeki Softphone: Checking if the Call State is set to Ringing");
		
		//Initialize Variable(s)
		String successMessage = "Ozeki Softphone: There is an incoming call with a ringing status";
		String failureMessage = "Ozeki Softphone: There is no incoming call with a ringing status";
		
		//Check if the Call State/Status is set to 'Ringing'
		try {
			softAssert.assertEquals(callStateRinging.isDisplayed(), true);
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} catch (Exception e) {
			softAssert.assertEquals("Not Ringing", "Ringing");
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "OzekiPage_verifyRingingCallStatus");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}