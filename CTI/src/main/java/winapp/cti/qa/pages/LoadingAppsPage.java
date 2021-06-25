package winapp.cti.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.locators.LoadingAppsLocators;
import winapp.cti.qa.util.GeneralMethods;
import winapp.cti.qa.util.ReportLoggerMethods;

public class LoadingAppsPage extends LoadingAppsLocators {
	
	//Initialize Variable(s)
	ExtentTest reportLogger;
	EventFiringWebDriver eDriver;
	GeneralMethods genMethods;
	ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public LoadingAppsPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize the generic methods for this class
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	public void openFileExplorer() {
		//Click the 'File Explorer' option on the TaskBar
		fileExplorerTaskbarButton.click();
	}
	
	public void clickLocalDiskButton() {
		//Click the 'Local Disk (C:)' button in File Explorer
		localDiskButton.click();
	}
	
	public void clickFileDropDownButton() {
		//Bring up the list of file paths
		fileDropDownButton.click();
	}
	
	public void clickFilePathField(String filePath) {
		//Delete the existing file path
		filePathFieldButton.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		
		//Click on the file directory field
		filePathFieldButton.sendKeys(filePath);
	}
	
	public void clickMaximizeFileExplorerButton() {
//		((JavascriptExecutor) eDriver).executeScript("window.scrollBy(0,500)");
		
		//Maximize the 'File Explorer' page
		maximizeFileExplorerButton.click();
	}
	
	public void clickspokCTIClientExecutableButton() {
		//Execute the 'Spok CTI Client' application from the File Explorer
		try {
			Actions actions = new Actions(eDriver);
			actions.doubleClick(spokCTIClientExecutableButton).build().perform();
			actions.doubleClick(spokCTIClientExecutableButton).build().perform();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void clickMinimizeFileExplorerButton() {
		//Minimize the Windows File Explorer
		minimizeFileExplorerButton.click();
	}
	
	public SoftAssert verifyOzekiApplication(SoftAssert softAssert, String ozekiTitle) {
		//Output a message to the report & system
		System.out.println("Ozeki: Checking if the Ozeki Application was brought up");
		reportLogger.log(LogStatus.INFO, "Ozeki: Checking if the Ozeki Application was brought up");
		
		//Initialize Variable(s)
		String successMessage = "Ozeki Softphone: The Ozeki Softphone is properly brought up & focused on";
		String failureMessage = "Ozeki Softphone: The Ozeki Softphone is not properly brought up & focused on (Currently focused on: '" + eDriver.getTitle() + "'";
		
		//Check if the Ozeki Application was properly brought up
		softAssert.assertEquals(eDriver.getTitle(), ozekiTitle);
		
		//Send the pass/fail status to the ExtentReport & System
		if (eDriver.getTitle().equals(ozekiTitle)) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "LoadingAppsPage_verifyOzekiApplication");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifySpokCTIClientApplication(SoftAssert softAssert, String spokCTIClientTitle) {
		//Output a message to the report & system
		System.out.println("Spok CTI Client: Checking if the Spok CTI Client Application was brought up");
		reportLogger.log(LogStatus.INFO, "Spok CTI Client: Checking if the Spok CTI Client Application was brought up");
		
		//Initialize Variable(s)
		String successMessage = "Spok CTI Client: The Spok CTI Client is properly brought up & focused on";
		String failureMessage = "Spok CTI Client: The Spok CTI Client is not properly brought up & focused on (Currently focused on: '" + eDriver.getTitle() + "'";
		
		//Check if the Ozeki Application was properly brought up
		softAssert.assertEquals(eDriver.getTitle(), spokCTIClientTitle);
		
		//Send the pass/fail status to the ExtentReport & System
		if (eDriver.getTitle().equals(spokCTIClientTitle)) {
			System.out.println("Success: " + successMessage);
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			System.out.print("Failed: " + failureMessage);
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "LoadingAppsPage_verifySpokCTIClientApplication");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}