package winapp.cti.qa.testcases.callcontrol;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.ACDPage;
import winapp.cti.qa.pages.CallControlPage;
import winapp.cti.qa.util.ExcelMethods;
import winapp.cti.qa.util.ExtentFactory;
import winapp.cti.qa.util.GeneralMethods;

public class AgentStatusTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	String selectAgentStatus;
	
	//Constructor
	public AgentStatusTest() {
		super();
	}
	
	public void performSetup(String reportTitle) {
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest(reportTitle);
		
		//Initialize PageFactories
		System.out.println(constantVariables.reportMessage);
		reportLogger.log(LogStatus.INFO, constantVariables.reportMessage);
		
		//Setup PageFactories for the Spok CTI Client Application
		eDriver = initializeApplication("CTI", "1");
		callControlPage = new CallControlPage(eDriver, reportLogger);
		acdPage = new ACDPage(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@Parameters({"agentStatus"})
	@BeforeClass
	public void beforeClass(@Optional("") String agentStatus) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Agent Status");
		column = 3;
		
		selectAgentStatus = agentStatus;
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void readyStateTest(String active, String agentStatus, String finalStatus, String dataRow) {
		System.out.println("@Test - readyStateTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		excelMethods.setDataTableCell("", iteration, column);
		
		//If the current row is not an active test row, skip it
		if ((active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) && (selectAgentStatus.equalsIgnoreCase(agentStatus) || selectAgentStatus.equalsIgnoreCase(""))) {
			//Setup the report & PageFactories
			if(agentStatus.equalsIgnoreCase("ready")) {
				performSetup("TC55303-US52495 Change Agent State to 'Ready'");
			} else if (agentStatus.equalsIgnoreCase("not-ready") || agentStatus.equalsIgnoreCase("not ready")) {
				performSetup("TC55308-USTC55308 Change Agent State to 'Not-Ready'");
			} else if (agentStatus.equalsIgnoreCase("work")) {
				performSetup("TC55476-USTC55476 Change Agent State to 'Work'");
			}
			
			//Navigate to the ACD Page
			acdPage.navigateToACDTab();
			
			//Change Agent Status
			if (agentStatus.equalsIgnoreCase("ready")) {
				//Click the 'Ready' button
				acdPage.clickReadyButton();
			} else if (agentStatus.equalsIgnoreCase("not-ready") || agentStatus.equalsIgnoreCase("not ready")) {
				//Click the 'Not-Ready' button
				acdPage.clickNotReadyButton();
			} else if (agentStatus.equalsIgnoreCase("Work")) {
				//Click the 'Work' button
				acdPage.clickWorkButton();
			}
			
			//Check if the agent's status is set correctly
			checkpoint = acdPage.verifyAgentStatus(checkpoint, agentStatus);
			
			//Navigate to the 'Call Control' Page
			callControlPage.navigateToCallControlTab();
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}