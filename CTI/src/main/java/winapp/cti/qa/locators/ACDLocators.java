package winapp.cti.qa.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ACDLocators {
	
	@FindBy(name="ACD")
	protected WebElement acdTab;
	
	@FindBy(xpath="//*[@AutomationId='agentId']")
	protected WebElement agentIDField;
	
	@FindBy(xpath="//*[@AutomationId='agentStatus']")
	protected WebElement agentStatusField;
	
	@FindBy(xpath="//*[@AutomationId='totalCallsInQueue']")
	protected WebElement totalCallsInQueueField;
	
	@FindBy(xpath="//*[@AutomationId='buttonACDLogin']")
	protected WebElement loginButton;
		
		@FindBy(xpath="//*[@AutomationId='textBoxAgentId']")
		protected WebElement agentIDInputField;
		
		@FindBy(xpath="//*[@AutomationId='textBoxPassword']")
		protected WebElement passwordInputField;
		
		@FindBy(xpath="//*[@AutomationId='textBoxACDGroup']")
		protected WebElement acdGroupInputField;
		
		@FindBy(xpath="//*[@AutomationId='buttonOK']")
		protected WebElement enterLoginCredentialsButton;//asdfasdfadsfasdf
		
		@FindBy(xpath="//*[@AutomationId='buttonCancel']")
		protected WebElement cancelButton;
		
	@FindBy(xpath="//*[@AutomationId='buttonACDLogout']")
	protected WebElement logoutButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonACDReady']")
	protected WebElement readyButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonACDNotReady']")
	protected WebElement notReadyButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonACDWork']")
	protected WebElement workButton;
		
		@FindBy(xpath="//*[@AutomationId='ListViewSubItem-1']")
		protected WebElement agentStatusChangeReasonButton;
		
		@FindBy(xpath="//*[@AutomationId='buttonOK']")
		protected WebElement submitAgentStatusChangeButton;
		
	@FindBy(xpath="//*[@AutomationId='buttonACDAfterCall']")
	protected WebElement afterCallButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonACDBusy']")
	protected WebElement busyButton;
	
}