package winapp.cti.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.locators.CallControlLocators;
import winapp.cti.qa.util.GeneralMethods;
import winapp.cti.qa.util.ReportLoggerMethods;

public class CallControlPage extends CallControlLocators {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	GeneralMethods genMethods;
	ReportLoggerMethods reportLoggerMethods;
	 
	//Constructor
	public CallControlPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	public void navigateToCallControlTab() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Navigating to the 'Call Control' tab");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Navigating to the 'Call Control' tab");
		
		//Click the 'Call Control' tab
		callControlTab.click();
	}
	
	public void clickAnswerButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Answer' button to connect to an incoming call");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Answer' button to connect to an incoming call");
		
		for (int i = 0; i < 2; i++) {
			try {
				//Click the 'Answer' button
				answerButton.click();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void clickHoldButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Hold' button to put the active call on hold");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Hold' button to put the active call on hold");
		
		//Click the 'Hold' button
		holdButton.click();
	}
	
	public void clickUnholdButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Unhold' button to retrieve the call from hold");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Unhold' button to retrieve the call from hold");
		
		//Click the 'Unhold' button
		unholdButton.click();
	}
	
	public void clickReleaseButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Release' button to end the active call");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Release' button to end the active call");
		
		for (int i = 0; i < 3; i++) {
			try {
				//Click the 'Release' button
				releaseButton.click();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void clickMakeCallButton(String extensionNumber, String phoneNumber) {
		//Output a message to the report & syste
		System.out.println("Spok CTI Client: Performing an outgoing call to " + phoneNumber + " from the extension " + extensionNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Performing an outgoing call to " + phoneNumber + " from the extension " + extensionNumber);
		
		//Click the 'Make Call' button
		makeCallButton.click();
		
		//Select the extension to be used to make the call
		for (int i = 0; i < 6; i++) {
			if (chooseExtensionDropDown.getText().equalsIgnoreCase(extensionNumber)) {
				break;
			}
			chooseExtensionDropDown.sendKeys(Keys.ARROW_DOWN);
		}
		
		//Enter the phone number to call
		enterDestinationField.sendKeys(phoneNumber);
		
		//Click to submit the call
		okButton.click();
	}
	
	public void transferCall(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Transferring the active call to: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Transferring the active call to: " + phoneNumber);
		
		//Click the 'Start Xfer' button
		startTransferButton.click();
		
		//Enter the phone number to start the transfer call
		phoneNumberField.sendKeys(phoneNumber);
		
		//Click 'OK' to submit the transfer
		okButton.click();
	}
	
	public void completeTransferCall() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Complete Xfer' button to complete the transfer");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Complete Xfer' button to complete the transfer");
		
		//Click the 'Complete Xfer' button
		completeTransferButton.click();
	}
	
	public void blindTransferCall(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Performing a 'Blind Transfer' on the active call to the phone number: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Performing a 'Blind Transfer' on the active call to the phone number: " + phoneNumber);
		
		//Click the 'Blind Xfer' button
		blindTrnasferButton.click();
		
		//Enter the phone number to start the transfer call
		phoneNumberField.sendKeys(phoneNumber);
		
		//Click 'OK' to submit the transfer
		okButton.click();
	}
	
	public void startConferenceCall(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Setting up a Conference call the active call and with the phone number: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Setting up a Conference call the active call and with the phone number: " + phoneNumber);
		
		//Click the 'Conference' button
		startConferenceButton.click();
		
		//Enter the phone number to start the transfer call
		phoneNumberField.sendKeys(phoneNumber);
		
		//Click 'Execute' to submit the transfer
		okButton.click();
	}
	
	public void completeConferenceCall() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Complete Conf.' button to complete the conference process");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Complete Conf.' button to complete the conference process");
		
		//Click the 'Conference' button
		completeConferenceButton.click();
	}
}