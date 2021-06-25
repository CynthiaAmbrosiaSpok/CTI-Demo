package winapp.cti.qa.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallControlLocators {
	
	@FindBy(name="Call Control")
	protected WebElement callControlTab;
	
	@FindBy(xpath="//*[@AutomationId='buttonAnswer']")
	protected WebElement answerButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonHold']")
	protected WebElement holdButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonUnhold']")
	protected WebElement unholdButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonRelease']")
	protected WebElement releaseButton;
	
	@FindBy(xpath="//*[@AutomationId='buttonMakeCall']")
	protected WebElement makeCallButton;
		
		@FindBy(xpath="//*[@AutomationId='comboBoxAddress']")
		protected WebElement chooseExtensionDropDown;
		
		@FindBy(xpath="//*[@AutomationId='textBoxDestination']")
		protected WebElement enterDestinationField;
		
		@FindBy(xpath="//*[@AutomationId='buttonOK']")
		protected WebElement okButton;
		
	@FindBy(xpath="//*[@LocalizedControlType='list item']")
	protected List<WebElement> tmp;
	
	@FindBy(xpath="//*[@AutomationId='buttonStartTransfer']")
	protected WebElement startTransferButton;
		
		@FindBy(xpath="//*[@AutomationId='textBoxDestination']")
		protected WebElement phoneNumberField;
		
	@FindBy(xpath="//*[@AutomationId='buttonCompleteTransfer']")
	protected WebElement completeTransferButton;
	
	@FindBy(name="Blind Xfer")
	protected WebElement blindTrnasferButton;
	
	@FindBy(name="Start Conf.")
	protected WebElement startConferenceButton;
	
	@FindBy(name="Complete Conf.")
	protected WebElement completeConferenceButton;
	
}