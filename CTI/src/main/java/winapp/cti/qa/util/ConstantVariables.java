package winapp.cti.qa.util;

import java.io.File;

public class ConstantVariables {
	//Project Location Path
	public String projectPath = System.getProperty("user.dir");
	
	//Winappdriver setup values
	public String winappdriverURL = "http://127.0.0.1:4723";
	public String winnappdriverPath = "C:" + File.separator + "Program Files (x86)" + File.separator + "Windows Application Driver" + File.separator + "WinAppDriver.exe";
	
	//Spok CTI Application Location
	public String spokCTIApplicationPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "winapp" + File.separator + "cti" + File.separator + "qa" + File.separator + "spokcticlient" + File.separator;
	public String spokCTIApplication = spokCTIApplicationPath + "SpokCTIClient.exe";
	
	//Ozeki Application Location
	public String ozekiApplicationPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "winapp" + File.separator + "cti" + File.separator + "qa" + File.separator + "ozeki" + File.separator;
	public String ozekiApplication = ozekiApplicationPath + "OzekiDemoSoftphoneWPF.exe";
	
	//Properties File Location
	public String propertiesApplicationPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "winapp" + File.separator + "cti" + File.separator + "qa" + File.separator + "config" + File.separator;
	public String propertiesApplication = propertiesApplicationPath + "config.properties";
	
	//Initializing the Ozeki & Spok CTI Client applications for the report
	public String reportMessage = "Initializing setup for Ozeki Softphone and Spok CTI Client Applications";
	
	//Titles for the Ozeki & Spok CTI Client Applications - used to locate & verify the existence of the applications
	public String ozekiApplicationTitle = "Ozeki VoIP SIP SDK - Demo Application 1.4.6.0";
	public String spokCTIClientTitle = "Spok CTI Client";
	
	public String screenshotLocation = projectPath + File.separator + "screenshots" + File.separator;
	
	public String extentFactoryReportPath = projectPath + File.separator + "test-output" + File.separator + "TestSuiteResults.html";
}