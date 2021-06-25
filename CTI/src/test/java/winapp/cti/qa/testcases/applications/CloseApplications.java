package winapp.cti.qa.testcases.applications;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class CloseApplications extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public CloseApplications() {
		super();
	}
	
	@BeforeClass
	public void beforeClass() {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Load Applications");
		column = 4;
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void closeApplications(String active, String cti, String ozeki, String finalStatus, String dataRow) {
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
			//Setup the Report
			report = ExtentFactory.getInstance();
			reportLogger = report.startTest("TC51652-US52554 Close the Spok CTI Client and Ozeki Softphone");
			
			//Initialize PageFactories
			System.out.println("Initializing setup for Ozeki Softphone and Spok CTI Client Applications");
			reportLogger.log(LogStatus.INFO, "Initializing setup for Ozeki Softphone and Spok CTI Client Applications");
			
			//Set the winappdriver to focus on the Ozeki application
			eDriver = initializeApplication("Ozeki", "1");
			
			//Print out the Ozeki application Title to ensure the focus has been set
			System.out.println(eDriver.getTitle());
			
			//Close the Ozeki application
			eDriver.close();
			
			//Verify that the Ozeki application has been closed
			try {
				checkpoint.assertEquals("Ozeki Application Closed", "Ozeki Application is Open");
			} catch (Exception e) {
				checkpoint.assertEquals("Ozeki Application Closed", "Ozeki Application Closed");
			}
			
			//Set the winappdriver to focus on the Ozeki application
			eDriver = initializeApplication("CTI", "1");
			
			//Print out the Ozeki application Title to ensure the focus has been set
			System.out.println(eDriver.getTitle());
			
			//Close the Ozeki application
			eDriver.close();
			
			//Verify that the Ozeki application has been closed
			try {
				checkpoint.assertEquals("Spok CTI Client Application Closed", "Spok CTI Client Application is Open");
			} catch (Exception e) {
				checkpoint.assertEquals("Spok CTI Client Application Closed", "Spok CTI Client Application Closed");
			}
			
			//Assert all checkpoints
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}