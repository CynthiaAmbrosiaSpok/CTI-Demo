package winapp.cti.qa.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.locators.ACDLocators;
import winapp.cti.qa.util.GeneralMethods;
import winapp.cti.qa.util.ReportLoggerMethods;

public class ACDPage extends ACDLocators {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	GeneralMethods genMethods;
	ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public ACDPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	public void navigateToACDTab() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Navigating to the 'ACD' tab");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Navigating to the 'ACD' tab");
		
		//Click the 'ACD' tab
		acdTab.click();
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
	 * Parameters: String userid - used to enter as login credentials
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
		agentIDInputField.sendKeys(userid);
		
		//Click 'Execute' to login
		enterLoginCredentialsButton.click();
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
		
		//Select the reason for changing the agent's status to 'Not-Ready'
		agentStatusChangeReasonButton.click();
		
		//Confirm 'Not-Ready' status
		submitAgentStatusChangeButton.click();
	}
	
	public void clickWorkButton() {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Clicking the 'Work' button to change the agent's status");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Clicking the 'Work' button to change the agent's status");
		
		//Click the 'Work' button
		workButton.click();
		
		//Select the reason for changing the agent's status to 'Work'
		agentStatusChangeReasonButton.click();
		
		//Confirm 'Work' status
		submitAgentStatusChangeButton.click();
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
	 * Purpose: Checks that the 'Logout' button has active status
	 * 
	 * How:	Checks for the agent status to be set to 'LOGOUT'
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
		
		//Initialize Variable(s)s
		String successMessage = "Spok CTI Client: The agent is properly logged out";
		String failureMessage = "Spok CTI Client: ";
		
		//Check if the user's status is on 'Logout'
		softAssert.assertEquals(agentStatusField.getAttribute("Name"), "LOGOUT");
		
		//Send an error to the ExtentReport if the agent's status it not set to 'LOGOUT'
		if (!agentStatusField.getAttribute("Name").equals("LOGOUT")) {
			failureMessage += "The agent's status is set to '" + agentStatusField.getAttribute("Name") + "' instead of 'LOGOUT'" + System.lineSeparator();
		}
		
		//Check if the 'Login' button was disabled, as expected
		if (loginButton.isEnabled()) {
			softAssert.assertEquals("The 'Login' button is enabled", "The 'Login' button is enabled");
		} else {
			softAssert.assertEquals("The 'Login' button is disabled", "The 'Login' button is enabled");
			//Send an error to the ExtentReport if the loginButton is not enabled
			failureMessage += "The loginButton is not enabled to allow login attempts" + System.lineSeparator();
		}
		
		//Check if the 'Logout' button was enabled, as expected
		if (logoutButton.isEnabled()) {
			softAssert.assertEquals("The 'Logout' button is enabled", "The 'Logout' button is disabled");
			//Send an error to the ExtentReport if the logoutButton is enabled
			failureMessage += "The logoutButton is enabled despite the agent expecting to be logged out" + System.lineSeparator();
		} else {
			softAssert.assertEquals("The 'Logout' button is disabled", "The 'Logout' button is disabled");
		}
		
		//Send the pass/fail status to the ExtentReport & System
		if (agentStatusField.getAttribute("Name").equals("LOGOUT") && loginButton.isEnabled() && !logoutButton.isEnabled()) {
			System.out.println("Success: " +  successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "ACDPage_verifyLogoutStatus()");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyAgentStatus(SoftAssert softAssert, String agentStatus) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the agent is currently in '" + agentStatus + "' status");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the agent is currently in '" + agentStatus + "' status");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The agent's status is properly set to '" + agentStatus + "'";
		String failureMessage = "Spok CTI Client: The agent's status is set to '" + agentStatusField.getAttribute("Name") + "' instead of '" + agentStatus + "'";
		
		//Check the Agent Status
		if (agentStatus.equalsIgnoreCase("ready")) {
			//Check if the user's status is on 'Ready'
			softAssert.assertEquals(agentStatusField.getAttribute("Name"), "READY");
			
			//Send the pass/fail status to the ExtentReport & System
			if (agentStatusField.getAttribute("Name").equals("READY")) {
				System.out.println("Success: " + successMessage);
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			} else {
				System.out.println("Failed: " + failureMessage);
				reportLoggerMethods.reportFailedCheckpoint(failureMessage, "ACDPage_verifyAgentStatus");
			}
		} else if (agentStatus.equalsIgnoreCase("not-ready") || agentStatus.equalsIgnoreCase("not ready")) {
			//Check if the user's status is on 'Not-Ready'
			softAssert.assertEquals(agentStatusField.getAttribute("Name"), "NOTREADY (Out-to-lunch - Out to lunch)");
			
			//Send the pass/fail status to the ExtentReport & System
			if (agentStatusField.getAttribute("Name").equals("NOTREADY (Out-to-lunch - Out to lunch)")) {
				System.out.println("Success: " + successMessage);
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			} else {
				System.out.println("Failed: " + failureMessage);
				reportLoggerMethods.reportFailedCheckpoint(failureMessage, "ACDPage_verifyAgentStatus");
			}
		} else if (agentStatus.equalsIgnoreCase("work")) {
			//Check if the user's status is on 'Work'
			softAssert.assertEquals(agentStatusField.getAttribute("Name"), "AUXWORK (Quick-meeting - Quick meeting)");
			
			//Send the pass/fail status to the ExtentReport & System
			if (agentStatusField.getAttribute("Name").equals("AUXWORK (Quick-meeting - Quick meeting)")) {
				System.out.println("Success: " + successMessage);
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			} else {
				System.out.println("Failed: " + failureMessage);
				reportLoggerMethods.reportFailedCheckpoint(failureMessage, "ACDPage_verifyAgentStatus");
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
	 * How:	Checks for the agent status to be set to 'NOTREADY'
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
		softAssert.assertEquals(agentStatusField.getAttribute("Name"), "NOTREADY");

		//Send an error to the ExtentReport if the agent's status it not set to 'LOGOUT'
		if (!agentStatusField.getAttribute("Name").equals("NOTREADY")) {
			failureMessage += "The agent's status is set to '" + agentStatusField.getAttribute("Name") + "' instead of 'NOTREADY'" + System.lineSeparator();
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
		if (agentStatusField.getAttribute("Name").equals("NOTREADY") && !loginButton.isEnabled() && logoutButton.isEnabled()) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "ACDPage_verifySuccessfulLogin");
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
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "ACDPage_verifyLoginStatus");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}