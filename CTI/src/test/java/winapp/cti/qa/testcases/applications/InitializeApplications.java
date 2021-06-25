package winapp.cti.qa.testcases.applications;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.OzekiPage;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class InitializeApplications extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public InitializeApplications() {
		super();
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Load Applications");
		column = 4;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("TC51651-US52553 Launch the Spok CTI Client and Ozeki Softphone");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void initializeApplications(String active, String cti, String ozeki, String finalStatus, String dataRow) {
		System.out.println("@Test - initializeApplications()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Initialize PageFactories
			System.out.println(constantVariables.reportMessage);
			reportLogger.log(LogStatus.INFO, constantVariables.reportMessage);
			
			//Initialize Ozeki Application
			initializeDriver(constantVariables.ozekiApplication);
			genMethods.waitFor(3);
			eDriver = initializeApplication("Ozeki", "1");
			genMethods.waitFor(3);
			ozekiPage = new OzekiPage(eDriver, reportLogger);
			ozekiPage.registerOzeki();
			genMethods.waitFor(30);
			
			//Initialize the root directory (treating the entire Desktop as an application)
			initializeDriver("Root");
			
			//Connect to the PageFactory
			setupDesktopPageFactory();
			
			//Bring up the Windows File Explorer
			loadingAppsPage.openFileExplorer();
			
			//Pause the script for a bit
			genMethods.waitFor(1);
			
			//Click the 'Local Disk (C:)' button in File Explorer
			loadingAppsPage.clickLocalDiskButton();
			
			//Pause the script for a bit
			genMethods.waitFor(1);
			
			//Bring up the list of file paths
			loadingAppsPage.clickFileDropDownButton();
			
			//Pause the script for a bit
			genMethods.waitFor(1);
			
			//Click on the file directory field
			loadingAppsPage.clickFilePathField(constantVariables.spokCTIApplicationPath + Keys.ENTER);
			
			//Pause the script for a bit
			genMethods.waitFor(1);
			
			//Maximize the 'File Explorer' page
			loadingAppsPage.clickMaximizeFileExplorerButton();
			
			//Execute the 'Spok CTI Client' application from the File Explorer
			loadingAppsPage.clickspokCTIClientExecutableButton();
			
			//Pause the script for a bit
			genMethods.waitFor(6);
			
			//Minimize the Windows File Explorer
			loadingAppsPage.clickMinimizeFileExplorerButton();
			
			//Check if the Ozeki Application was properly brought up
			eDriver = initializeApplication("Ozeki", "1");
			setupOzekiPageFactory();
			checkpoint = loadingAppsPage.verifyOzekiApplication(checkpoint, constantVariables.ozekiApplicationTitle);
			
			//Check if the Spok CTI Client Application was properly brought up
			eDriver = initializeApplication("CTI", "1");
			setupDesktopPageFactory();
			checkpoint = loadingAppsPage.verifySpokCTIClientApplication(checkpoint, constantVariables.spokCTIClientTitle);
			
			//Assert all checkpoints
			checkpoint.assertAll();
			
			//Logout of the CTI Application
			phoneControlPage = new PhoneControlPage(eDriver, reportLogger);
			phoneControlPage.clickLogout();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}