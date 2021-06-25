package winapp.cti.qa.testcases.phonecontrol;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import winapp.cti.qa.base.TestBase;
import winapp.cti.qa.pages.PhoneControlPage;
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
		phoneControlPage = new PhoneControlPage(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@Parameters({"callType"})
	@BeforeClass
	public void beforeClass(@Optional("") String callType) {
		//Initialize Variable(s)
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setSheetName("Agent Status");
		column = 3;
		
		selectAgentStatus = callType;
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
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes") && (selectAgentStatus.equalsIgnoreCase(agentStatus) || selectAgentStatus.equalsIgnoreCase(""))) {
			
			//Change Agent Status
			if (agentStatus.equalsIgnoreCase("ready")) {
				performSetup("TC55312 - US52495 Change Agent State to 'Ready'");
				//Click the 'Ready' button
				phoneControlPage.clickReadyButton();
			} else if (agentStatus.equalsIgnoreCase("not-ready") || agentStatus.equalsIgnoreCase("not ready")) {
				performSetup("TC55318 - USTC55308 Change Agent State to 'Not-Ready'");
				//Click the 'Not-Ready' button
				phoneControlPage.clickNotReadyButton();
			} else if (agentStatus.equalsIgnoreCase("work")) {
				performSetup("TC55484 - USTC55476 Change Agent State to 'Work'");
				//Click the 'Work' button
				phoneControlPage.clickWorkButton();
			}
			
			//Check if the agent's status is active on the relevant status
			checkpoint = phoneControlPage.verifyAgentStatus(checkpoint, agentStatus);
			
			//Assert all checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
	
}