package winapp.cti.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.locators.PhoneControlLocators;
import winapp.cti.qa.util.GeneralMethods;
import winapp.cti.qa.util.ReportLoggerMethods;

public class PhoneControlPage extends PhoneControlLocators {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	GeneralMethods genMethods;
	ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PhoneControlPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	/*
	 * Used Location: 'LoginPageTest'
	 * 
	 * Purpose: Click the 'Login' button to login the user
	 * 
	 * How:	Clicks the 'Login' button
	 * 
	 * Pre-Conditions: User needs to be logged out
	 * 
	 * Parameters: N/A
	 * 
	 * Returns: N/A
	 */
	public void login(String userid) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Entering login credentials and logging in");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Entering login credentials and logging in");
		
		//Click the 'Login' button
		loginButton.click();
		
		//Enter the userid
		enterKeypad(userid);
		
		//Click 'Execute' to login
		executeButton.click();
	}
	
	/*
	 * Used Location: 'LoginPageTest'
	 * 
	 * Purpose: Click the 'Logout' button to logout the user
	 * 
	 * How:	Clicks the 'Logout' button
	 * 
	 * Pre-Conditions: User needs to be logged in
	 * 
	 * Parameters: N/A
	 * 
	 * Returns: N/A
	 */
	public void clickLogout() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Logout' button to logout");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Logout' button to logout");
		
		//Click the 'Logout' button
		logoutButton.click();
	}
	
	/*
	 * Used Location: 'ReadyTest'
	 * 
	 * Purpose: Click the 'Ready' button to put the user into the 'Ready' status
	 * 
	 * How:	Clicks the 'Ready' button
	 * 
	 * Pre-Conditions: User needs to be logged in
	 * 					User needs to not be in 'Ready' status
	 * 
	 * Parameters: N/A
	 * 
	 * Returns: N/A
	 */
	public void clickReadyButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Ready' button to change the agent's status");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Ready' button to change the agent's status");
		
		//Click the 'Ready' button
		readyButton.click();
	}
	
	/*
	 * Used Location: 'ReadyTest'
	 * 
	 * Purpose: Click the 'Not-Ready' button to put the user into the 'Not-Ready' status
	 * 
	 * How:	Clicks the 'Not-Ready' button
	 * 
	 * Pre-Conditions: User needs to be logged in
	 * 					User needs to not be in 'Not-Ready' status
	 * 
	 * Parameters: N/A
	 * 
	 * Returns: N/A
	 */
	public void clickNotReadyButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Not-Ready' button to change the agent's status");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Not-Ready' button to change the agent's status");
		
		//Click the 'Not-Ready' button
		notReadyButton.click();
		
		//Confirm 'Not-Ready' status
		executeButton.click();
	}
	
	public void clickWorkButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Work' button to change the agent's status");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Work' button to change the agent's status");
		
		//Click the 'Not-Ready' button
		workButton.click();
		
		//Confirm 'Work' status
		executeButton.click();
	}
	
	/*
	 * Used Location: 'ReleaseCallTest'
	 * 
	 * Purpose: Click the 'Release' button to end an active call
	 * 
	 * How:	Clicks the 'Release' button while a call is active
	 * 
	 * Pre-Conditions: A call must be active/on-going
	 * 
	 * Parameters: N/A
	 * 
	 * Returns: N/A
	 */
	public void clickReleaseButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Release' button to end the active call");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Release' button to end the active call");
		
		//Click the 'Release' button
		releaseButton.click();
	}
	
	public void clickHoldButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Hold' button to put the active call on hold");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Hold' button to put the active call on hold");
		
		//Click the 'Hold' button
		holdButton.click();
	}
	
	public void clickAnswerButton(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Answer' button to connect to an incoming call");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Answer' button to connect to an incoming call");
		
		//Click the relevant Extension Number button to answer the call
		eDriver.findElement(By.name(phoneNumber)).click();
	}
	
	public void performOutgoingCall(String extensionNumber, String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Performing an outgoing call to " + phoneNumber + " from the extension " + extensionNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Performing an outgoing call to " + phoneNumber + " from the extension " + extensionNumber);
		
		//Click the relevant Extension Number button to answer the call
		eDriver.findElement(By.name(extensionNumber)).click();
		
		//Enter the phone number
		enterKeypad(phoneNumber);
		
		//Click 'Execute' to perform call
		executeButton.click();
	}
	
	public void clickTransferButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Transfer' button to complete the transfer process");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Transfer' button to complete the transfer process");
		
		//Click the 'Transfer' button
		transferButton.click();
	}
	
	public void transferCall(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Transferring the active call to: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Transferring the active call to: " + phoneNumber);
		
		//Click the 'Transfer' button
		transferButton.click();
		
		//Enter the phone number to start the transfer call
		enterKeypad(phoneNumber);
		
		//Click 'Execute' to submit the transfer
		executeButton.click();
	}
	
	public void blindTransferCall(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Performing a 'Blind Transfer' on the active call to the phone number: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Performing a 'Blind Transfer' on the active call to the phone number: " + phoneNumber);
		
		//Click the 'Blind Transfer' button
		blindTransferButton.click();
		
		//Enter the phone number to start the transfer call
		enterKeypad(phoneNumber);
		
		//Click 'Execute' to submit the transfer
		executeButton.click();
	}
	
	public void startConferenceCall(String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Setting up a Conference call the active call and with the phone number: " + phoneNumber);
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Setting up a Conference call the active call and with the phone number: " + phoneNumber);
		
		//Click the 'Conference' button
		conferenceButton.click();
		
		//Enter the phone number to start the transfer call
		enterKeypad(phoneNumber);
		
		//Click 'Execute' to submit the transfer
		executeButton.click();
	}
	
	public void clickConferenceButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Conference' button to complete the conference process");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Conference' button to complete the conference process");
		
		//Click the 'Conference' button
		conferenceButton.click();
	}
	
	public boolean checkLogoutStatus() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the user is currently logged out");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the user is currently logged out");
		
		//Check if the 'Login' button is enabled
		if (logoutButton.isEnabled()) {
			//Return the 'logged out' status
			return false;
		} else {
			//Return the 'logged in' status
			return true;
		}
	}
	
	/*
	 * Used Location: 'LoginPageTest'
	 * 
	 * Purpose: Checks that the CTI Application is connected & has established connection to the CTI Server
	 * 
	 * How:	Checks for the 'Connected' status (in the bottm-right corner)
	 * 		Checks for the 'Established' status (in the bottm-right corner)
	 * 
	 * Pre-Conditions: N/A (Internet & VPN must be connected for a successful result)
	 * 
	 * Parameters: SoftAssert - to perform assert(s)
	 * 
	 * Returns: SoftAssert
	 */
	public SoftAssert verifyApplicationConnection(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the Spok CTI Client is connected to the server");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the Spok CTI Client is connected to the server");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The client is in 'connected' and 'established' status";
		String failureMessage = "Spok CTI Client:";
		
		//Check if the 'connected' message appears
		try {
			softAssert.assertEquals(connectedMessage.getText(), "connected");
		} catch (Exception e) {
//			softAssert.assertEquals(reconnectionMessage.getText(), "connected");
			failureMessage += " The client is not in 'connected' status.";
		}
		
		//Check if the 'established' message appears
		try {
			softAssert.assertEquals(establishedMessage.getText(), "established");
		} catch (Exception e) {
//			softAssert.assertEquals(failedConnectionMessage.getText(), "established");
			failureMessage += " The client is not in 'established' status.";
		}
		
		//Send the pass/fail status to the ExtentReport & System
		try {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} catch (Exception e) {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyApplicationConnection");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	/*
	 * Used Location: 'LoginPageTest'
	 * 
	 * Purpose: Checks that the 'Logout' button has active status
	 * 
	 * How:	Checks for the 'Active' status to be on the 'Logout' button
	 * 		Checks for the 'Login' button to be enabled
	 * 		Checks for the 'Logout' button to be disabled
	 * 
	 * Pre-Conditions: N/A
	 * 
	 * Parameters: SoftAssert - to perform assert(s)
	 * 
	 * Returns: SoftAssert
	 */
	public SoftAssert verifyLogoutStatus(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the user is currently logged out");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the user is currently logged out");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The agent is properly logged out";
		String failureMessage = "Spok CTI Client:";
		
		//Check if the user's status is on 'Logout'
		softAssert.assertEquals(logoutButton.getAttribute("Name"), "♦ Logout");
		
		//Check if the 'Login' button was disabled, as expected
		if (loginButton.isEnabled()) {
			softAssert.assertEquals("The 'Login' button is enabled", "The 'Login' button is enabled");
		} else {
			softAssert.assertEquals("The 'Login' button is disabled", "The 'Login' button is enabled");
			failureMessage += " The should be enabled, not disabled.";
		}
		
		//Check if the 'Logout' button was enabled, as expected
		if (logoutButton.isEnabled()) {
			softAssert.assertEquals("The 'Logout' button is enabled", "The 'Logout' button is disabled");
			failureMessage += " The should be disabled, not enabled.";
		} else {
			softAssert.assertEquals("The 'Logout' button is disabled", "The 'Logout' button is disabled");
		}
		
		//Send the pass/fail status to the ExtentReport & System
		if (loginButton.isEnabled() && !logoutButton.isEnabled()) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyLogoutStatus");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	/*
	 * Used Location: 'ReadyTest'
	 * 
	 * Purpose: Checks that the Agent Status is active on the relevant status/button
	 * 
	 * How:	Checks for the active status to be on the relevant Agent Status button (aka. 'Ready', 'Not-Ready', etc. button has the diamond/star shape)
	 * 
	 * Pre-Conditions: Agent is logged in
	 * 
	 * Parameters: SoftAssert - to perform assert(s)
	 * 
	 * Returns: SoftAssert
	 */
	public SoftAssert verifyAgentStatus(SoftAssert softAssert, String agentStatus) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the agent is currently in '" + agentStatus + "' status");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the agent is currently in '" + agentStatus + "' status");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The agent's status is properly set to '" + agentStatus + "'";
		String failureMessage = "Spok CTI Client: The agent's status is not set to '" + agentStatus + "'";
		
		//Check the Agent Status
		if (agentStatus.equalsIgnoreCase("ready")) {
			//Check if the user's status is on 'Ready'
			softAssert.assertEquals(readyButton.getAttribute("Name"), "♦ Ready");
			
			//Send the pass/fail status to the ExtentReport & System
			if (readyButton.getAttribute("Name").equals("♦ Ready")) {
				System.out.println("Success: " + successMessage);
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			} else {
				System.out.println("Failed: " + failureMessage);
				reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyAgentStatus");
			}
		} else if (agentStatus.equalsIgnoreCase("not-ready") || agentStatus.equalsIgnoreCase("not ready")) {
			//Check if the user's status is on 'Not-Ready'
			softAssert.assertEquals(notReadyButton.getAttribute("Name"), "♦ Not-Ready");
			
			//Send the pass/fail status to the ExtentReport & System
			if (notReadyButton.getAttribute("Name").equals("♦ Not-Ready")) {
				System.out.println("Success: " + successMessage);
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			} else {
				System.out.println("Failed: " + failureMessage);
				reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyAgentStatus");
			}
		} else if (agentStatus.equalsIgnoreCase("Work")) {
			//Check if the user's status is on 'Work'
			softAssert.assertEquals(workButton.getAttribute("Name"), "♦ Work");
			
			//Send the pass/fail status to the ExtentReport & System
			if (workButton.getAttribute("Name").equals("♦ Work")) {
				System.out.println("Success: " + successMessage);
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			} else {
				System.out.println("Failed: " + failureMessage);
				reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyAgentStatus");
			}
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	/*
	 * Used Location: 'LoginPageTest'
	 * 
	 * Purpose: Checks that the user successfully logged in
	 * 
	 * How:	Checks for the 'Active' status to be on the 'Not-Ready' button
	 * 		Checks for the 'Login' button to be disabled
	 * 		Checks for the 'Logout' button to be enabled
	 * 
	 * Pre-Conditions: N/A
	 * 
	 * Parameters: SoftAssert - to perform assert(s)
	 * 
	 * Returns: SoftAssert
	 */
	public SoftAssert verifySuccessfulLogin(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the login attempt was successful");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the login attempt was successful");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The agent is successfully logged in";
		String failureMessage = "Spok CTI Client: ";
		
		//Check if the user's status is on 'Not-Ready'
		softAssert.assertEquals(notReadyButton.getAttribute("Name"), "♦ Not-Ready");
		
		//Send an error to the ExtentReport if the agent's status it not set to 'LOGOUT'
		if (!notReadyButton.getAttribute("Name").equals("♦ Not-Ready")) {
			failureMessage += "The agent's status is not set to 'Not-Ready'" + System.lineSeparator();
		}
		
		//Check if the 'Login' button was disabled, as expected
		if (loginButton.isEnabled()) {
			softAssert.assertEquals("The 'Login' button is enabled", "The 'Login' button is disabled");
			//Send an error to the ExtentReport if the loginButton is enabled
			failureMessage += "The loginButton is enabled despite the agent expecting to be logged in" + System.lineSeparator();
		} else {
			softAssert.assertEquals("The 'Login' button is disabled", "The 'Login' button is disabled");
		}
		
		//Check if the 'Logout' button was enabled, as expected
		if (logoutButton.isEnabled()) {
			softAssert.assertEquals("The 'Logout' button is enabled", "The 'Logout' button is enabled");
		} else {
			softAssert.assertEquals("The 'Logout' button is disabled", "The 'Logout' button is enabled");
			//Send an error to the ExtentReport if the logoutButton is not enabled
			failureMessage += "The logoutButton is not enabled to allow logout attempts" + System.lineSeparator();
		}
		
		//Send the pass/fail status to the ExtentReport & System
		if (notReadyButton.getAttribute("Name").equals("♦ Not-Ready") && !loginButton.isEnabled() && logoutButton.isEnabled()) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifySuccessfulLogin");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyLoginStatus(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the agent is currently logged in");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the agent is currently logged in");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The is properly logged in";
		String failureMessage = "Spok CTI Client: ";
		
		//Check if the 'Login' button was disabled, as expected
		if (loginButton.isEnabled()) {
			softAssert.assertEquals("The 'Login' button is enabled", "The 'Login' button is disabled");
			failureMessage += "The 'Login' button should not be enabled." + System.lineSeparator();
		} else {
			softAssert.assertEquals("The 'Login' button is disabled", "The 'Login' button is disabled");
		}
		
		//Check if the 'Logout' button was enabled, as expected
		if (logoutButton.isEnabled()) {
			softAssert.assertEquals("The 'Logout' button is enabled", "The 'Logout' button is enabled");
		} else {
			softAssert.assertEquals("The 'Logout' button is disabled", "The 'Logout' button is enabled");
			failureMessage += "The 'Logout' button should not be disabled." + System.lineSeparator();
		}
		
		//Send the pass/fail status to the ExtentReport & System
		if (!loginButton.isEnabled() && logoutButton.isEnabled()) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyLoginStatus");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyIncomingCall(SoftAssert softAssert, String ozekiNumber, String phoneNumber) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if there is an incoming call. Call state expected -> ring");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if there is an incoming call. Call state expected -> ring");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The incoming call is correctly set to: '" + ozekiNumber + " to " + phoneNumber + "'";
		String failureMessage = "Spok CTI Client: The incoming call is incorrectly set to: '" + displayInfoField1.getText() + "', instead of '" + ozekiNumber + " to " + phoneNumber + "'";
		
		//Pause the script for a bit
		genMethods.waitFor(1);
		
		//Check if the displayed general info matches expectations
		softAssert.assertEquals(displayInfoField1.getText(), ozekiNumber + " to " + phoneNumber);
		
		//Send the pass/fail status to the ExtentReport & System
		if (displayInfoField1.getText().equalsIgnoreCase(ozekiNumber + " to " + phoneNumber)) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyIncomingCall");
		}
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "ring");
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyAnsweredCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the call was answered successful. Call state expected -> connected");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the call was answered successful. Call state expected -> connected");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The incoming call has been answered correctly";
		String failureMessage = "Spok CTI Client: The incoming call has not been answered. Expected status: 'connected'. Actual status: '" + callStateField.get(0).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "connected");
		
		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("connected")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyAnsweredCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyOutgoingCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if there is an outgoing call. Call state expected -> ringback");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if there is an outgoing call. Call state expected -> ringback");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The outgoing call has been performed correctly";
		String failureMessage = "Spok CTI Client: The incoming call has not been performed correctly. Expected status: 'ringback'. Actual status: '" + callStateField.get(0).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "ringback");
		
		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("ringback")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyOutgoingCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyHoldCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the active call was placed on 'Hold'. Call state expected -> hold");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the active call was placed on 'Hold'. Call state expected -> hold");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The ongoing call has been put on hold successfully";
		String failureMessage = "Spok CTI Client: The ongoing call has not been put on hold. Expected status: 'hold'. Actual status: '" + callStateField.get(0).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "hold");
		
		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("hold")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyHoldCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyTransferredCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the active call is being transferred. Call state expected -> soft_hold & ringback");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the active call is being transferred. Call state expected -> soft_hold & ringback");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The ongoing call has been transferred successfully";
		String failureMessage = "Spok CTI Client: The ongoing call has not been transferred successfully. Expected statuses: 'soft_hold' and 'ringback'. Actual statuses: '" + callStateField.get(0).getText() + "' and '" + callStateField.get(1).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "soft_hold");
		softAssert.assertEquals(callStateField.get(1).getText(), "ringback");
		
		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("soft_hold") && callStateField.get(1).getText().equalsIgnoreCase("ringback")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyTransferredCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyOutgoingConferenceCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the Conference Call invite has been sent. Call state expected -> soft_hold & ringback");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the Conference Call invite has been sent. Call state expected -> soft_hold & ringback");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The conference call invite has been sent successfully";
		String failureMessage = "Spok CTI Client: The conference call invite has not been sent successfully. Expected statuses: 'soft_hold' and 'ringback'. Actual statuses: '" + callStateField.get(0).getText() + "' and '" + callStateField.get(1).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "soft_hold");
		softAssert.assertEquals(callStateField.get(1).getText(), "ringback");
		
		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("soft_hold") && callStateField.get(1).getText().equalsIgnoreCase("ringback")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyOutgoingConferenceCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyAcceptedConferenceCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the Conference Call invite has been accepted. Call state expected -> soft_hold & connected");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the Conference Call invite has been accepted. Call state expected -> soft_hold & connected");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The conference call invite has been accepted successfully";
		String failureMessage = "Spok CTI Client: The conference call invite has not been accepted successfully. Expected statuses: 'soft_hold' and 'connected'. Actual statuses: '" + callStateField.get(0).getText() + "' and '" + callStateField.get(1).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "soft_hold");
		softAssert.assertEquals(callStateField.get(1).getText(), "connected");
		
		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("soft_hold") && callStateField.get(1).getText().equalsIgnoreCase("connected")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyAcceptedConferenceCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifySuccessfulConferenceCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the Conference Call is setup. Call state expected -> connected");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the Conference Call is setup. Call state expected -> connected");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The conference call has been setup successfully";
		String failureMessage = "Spok CTI Client: The conference call has not been setup successfully. Expected status: 'connected'. Actual status: '" + callStateField.get(0).getText() + "'";
		
		//Check if the Call State is correct
		softAssert.assertEquals(callStateField.get(0).getText(), "connected");

		//Send the pass/fail status to the ExtentReport & System
		if (callStateField.get(0).getText().equalsIgnoreCase("connected")) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifySuccessfulConferenceCall");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyReleasedCall(SoftAssert softAssert) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the active call was released successful. Call state & other info should not be present");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the active call was released successful. Call state & other info should not be present");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The ongoing call has been released successfully";
		String failureMessage = "Spok CTI Client: The ongoing call has not been released successfully.";
		
		//Check if the Call State/Status does not exist, as expected
		try {
			softAssert.assertEquals(callStateField.get(0).getText(), "N/A");
			
			//Send the pass/fail status to the ExtentReport & System
			failureMessage += " Expected no call status, but found the status: '" + callStateField.get(0).getText() + "'";
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "PhoneControlPage_verifyReleasedCall");
		} catch (Exception e) {
			softAssert.assertEquals("No Call Status", "No Call Status");
			
			//Send the pass/fail status to the ExtentReport & System
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}