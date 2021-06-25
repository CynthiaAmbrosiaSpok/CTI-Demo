package winapp.cti.qa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportLoggerMethods {
	
	//Initialize the report logger variables
	private ConstantVariables constantVariables;
	private ExtentTest reportLogger;
	private EventFiringWebDriver eDriver;
	
	public ReportLoggerMethods(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		
		constantVariables = new ConstantVariables();
	}
	
	public void getScreenshot(String screenshotName) {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) eDriver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			String destination = constantVariables.screenshotLocation + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			//FileUtils.copyFile(source, finalDestination);
			FileHandler.copy(source, finalDestination);
			
			reportLogger.log(LogStatus.FAIL,  reportLogger.addScreenCapture(destination)); //adds screenshot to ExtentReport
		} catch (Exception e) {
			System.out.println("Unable to properly take a screenshot and save it to the report");
			System.out.println(e);
		}
	}
	
	public void reportSuccessfulCheckpoint(String reportMessage) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.PASS, reportMessage);
	}
	
	public void reportFailedCheckpoint(String reportMessage, String screenshotName) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.FAIL, reportMessage);
		
		//Take a screenshot of the application to record the error
		getScreenshot(screenshotName);
	}
	
	public void reportSkippedCheckpoint(String reportMessage, String screenshotName) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.FAIL, reportMessage);
		
		//Take a screenshot of the application to record the error
		getScreenshot(screenshotName);
	}
}