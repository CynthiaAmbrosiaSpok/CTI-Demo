package winapp.cti.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import winapp.cti.qa.pages.ACDPage;
import winapp.cti.qa.pages.CallControlPage;
import winapp.cti.qa.pages.LoadingAppsPage;
import winapp.cti.qa.pages.PhoneControlPage;
import winapp.cti.qa.pages.OzekiPage;
import winapp.cti.qa.util.ConstantVariables;
import winapp.cti.qa.util.EventHandler;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	//Define Variable(s)
	public static Properties prop;
	public static ConstantVariables constantVariables;
	public GeneralMethods genMethods;
	public ExcelMethods excelMethods;
//	private Process process;
	
	//Variable(s) used to export script results
	public int iteration;
	public int column;
	public static int ozekiApplicationTracker;
	protected boolean bringupOzeki;
	
	//Variable(s) used to initialize the WebDriver
	public static DesiredCapabilities caps = new DesiredCapabilities();
	public static WindowsDriver<WindowsElement> wDriver;
	public static EventHandler eHandler;  //used in conjunction with the EventFiringWebDriver
	public static EventFiringWebDriver eDriver; //used in conjunction with the WebDriver=
	
	//Initialize the report logger variables
	public ExtentReports report; //used to setup a report that will hold the testing info of the script(s)
	public ExtentTest reportLogger; //used to store testing details in the report
	
	//Define PageFactories
	public LoadingAppsPage loadingAppsPage;
	public PhoneControlPage phoneControlPage;
	public CallControlPage callControlPage;
	public ACDPage acdPage;
	public OzekiPage ozekiPage;
	
	public TestBase() {
		try {
			constantVariables = new ConstantVariables();
			prop = new Properties();
			FileInputStream ip = new FileInputStream(constantVariables.propertiesApplication);
			prop.load(ip);
			
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(constantVariables.extentFactoryReportPath);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static WindowsDriver<WindowsElement> GetDesktopSession() throws MalformedURLException {
		// Get Desktop Session
		DesiredCapabilities desktopCapabilities = new DesiredCapabilities();
		desktopCapabilities.setCapability("app", "Root");
		desktopCapabilities.setCapability("deviceName", "WindowsPC");
		WindowsDriver<WindowsElement> session =  new WindowsDriver<WindowsElement>(new URL(constantVariables.winappdriverURL), desktopCapabilities);
		return session;
	}

	public void setupDesktopPageFactory() {
		//Initialize PageFactories
		System.out.println("Preparing to open the Spok CTI Client Application");
		reportLogger.log(LogStatus.INFO, "Preparing to open the Spok CTI Client Application");
		
		//Setup PageFactory
		loadingAppsPage = new LoadingAppsPage(eDriver, reportLogger);
	}
	
	public void setupOzekiPageFactory() {
		//Initialize PageFactories
		System.out.println("Preparing to open the Ozeki Application");
		reportLogger.log(LogStatus.INFO, "Preparing to open the Ozeki Application");
		
		//Setup PageFactory
		loadingAppsPage = new LoadingAppsPage(eDriver, reportLogger);
	}
	
	protected static EventFiringWebDriver initializeApplication(String application, String ozekiApplicationNumber) {
		return initializeApplication(application, Integer.parseInt(ozekiApplicationNumber));
	}
	
	protected static EventFiringWebDriver initializeApplication(String application, int ozekiApplicationNumber) {
		try {
			//Setup a driver focusing on the full desktop
			WindowsDriver<WindowsElement> desktopSession = GetDesktopSession();
			
			//Initialize Variable(s)
			WebElement modal;
			String windowHandle;
			
			//Retrieve the Window Handle
			if (application.equalsIgnoreCase("ozeki")) {
				modal = desktopSession.findElementsByName(constantVariables.ozekiApplicationTitle).get(ozekiApplicationNumber-1);
				windowHandle = modal.getAttribute("NativeWindowHandle");
			} else if (application.equalsIgnoreCase("cti")) {
				modal = desktopSession.findElementByName(constantVariables.spokCTIClientTitle);
				windowHandle = modal.getAttribute("NativeWindowHandle");
			} else {
				return null;
			}
			
			//Convert the Window Handle to hex code
			int intWindowHandle = Integer.parseInt(windowHandle);
			windowHandle = Integer.toHexString(intWindowHandle);
			windowHandle = "0x" + windowHandle.toUpperCase();
			
			//Setup the DesiredCapabilities for the winappdriver initialization
			DesiredCapabilities appModalCapabilities = new DesiredCapabilities();
			appModalCapabilities.setCapability("appTopLevelWindow", windowHandle);
			
			//Setup the winappdriver
			wDriver = new WindowsDriver<WindowsElement>(new URL(constantVariables.winappdriverURL), appModalCapabilities);
			
			//Setup the EventFiringWebDriver
			eDriver = new EventFiringWebDriver(wDriver);
			eHandler = new EventHandler();
			eDriver.register(eHandler);
			
			eDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			//Return the EventFiringWebDriver
			return eDriver;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//Return null, if an error occurred
			return null;
		}
	}
	
	public void initializeDriver(String appLocation) {
		//Replace the forward/backward slashes with the relevant slash separator for the Operating System
		appLocation = appLocation.replace("\\", File.separator);
		appLocation = appLocation.replace("/", File.separator);
		
		//Set the capabilities
		caps.setCapability("app", appLocation);
		caps.setCapability("platformName", "Windows");
		caps.setCapability("deviceName", "WindowsPC");
		
		//Connect to the server & setup the driver
		try {
			URL url = new URL(constantVariables.winappdriverURL);
			wDriver = new WindowsDriver<WindowsElement>(url, caps);
			
			eDriver = new EventFiringWebDriver(wDriver);
			eHandler = new EventHandler();
			eDriver.register(eHandler);
		} catch (Exception e) {
			try {
				caps.setCapability("app", "Root");
				caps.setCapability("platformName", "Windows");
				caps.setCapability("deviceName", "WindowsPC");
				
				URL url = new URL(constantVariables.winappdriverURL);
				wDriver = new WindowsDriver<WindowsElement>(url, caps);
				
				eDriver = new EventFiringWebDriver(wDriver);
				eHandler = new EventHandler();
				eDriver.register(eHandler);
				
				eDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println(e);
		}
	}
	
	public void closeReport() {
		//Close & output the report
		report.endTest(reportLogger);
		report.flush();
		report.close();
	}
	
	@BeforeSuite
	@Parameters({"application", "phoneApp"})
	public void beforeSuite(String appLocation, String phoneApp) {
		//Initialize Variable(s)
		System.out.println("Performing the script's setups (@BeforeSuite)");
		
		//Delete previous Extent Report
		ExtentFactory.deleteExtentReport();
		
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		
//		try {
//			/*
//			 * Must bring up winappdriver before test suite
//			 * System.getProperty("user.dir")
//			 */
//			
//			String command = constantVariables.winnappdriverPath;
//			ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
//			process = builder.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@AfterSuite
	public void afterSuite() {
//		process.destroy();
//		eDriver.quit();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		try {
			if(result.getStatus() == ITestResult.FAILURE) {
				//Output the result to the system and report
				System.out.println("Test Case '" + result.getName() + "' failed");
				reportLogger.log(LogStatus.FAIL,  "Test Case '" + result.getName() + "' failed"); //adds name to ExtentReport
				reportLogger.log(LogStatus.FAIL,  "Test Case '" + result.getThrowable() + "' failed"); //adds error/exception to ExtentReport
				
				//Output the result to the Excel File
				excelMethods.setDataTableCell("Failed - " + excelMethods.getCurrentDateTime(), iteration, column);
			} else if (result.getStatus() == ITestResult.SKIP) {
				//Output the result to the system and report
				System.out.println("Test Case '" + result.getName() + "' was skipped");
				reportLogger.log(LogStatus.SKIP, "Test Case '" + result.getName() + "' was skipped");
				
				//Output the result to the Excel File
				excelMethods.setDataTableCell("Skipped - " + excelMethods.getCurrentDateTime(), iteration, column);
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				//Output the result to the system and report
				System.out.println("Test Case: '" + result.getName() + "' Passed");
				reportLogger.log(LogStatus.PASS,  "Test Case: '" + result.getName() + "' Passed");
				
				//Output the result to the Excel File
				excelMethods.setDataTableCell("Success - " + excelMethods.getCurrentDateTime(), iteration, column);
			} else {
				//Output the result to the system and report
				System.out.println("Test Case: '" + result.getName() + "' Passed");
				reportLogger.log(LogStatus.UNKNOWN,  "Unknown error upon completion of a @Test - the @Test neither passed, failed, or was skipped");
				
				//Output the result to the Excel File
				excelMethods.setDataTableCell("Unknown Issue - " + excelMethods.getCurrentDateTime(), iteration, column);
			}
			
			//Close & output the report
			report.endTest(reportLogger);
			report.flush();
			report.close();
		} catch (Exception e) {
			System.out.print("");
		}
	}
	
}