package winapp.cti.qa.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PhoneControlLocators {
	
	@FindBy(name="Unable to connect to CTI Server")
	protected WebElement ctiServerConnectionFailMessage;
		
		@FindBy(name="reconnecting")
		protected WebElement reconnectionMessage;
		
		@FindBy(name="failed")
		protected WebElement failedConnectionMessage;
		
		@FindBy(name="connected")
		protected WebElement connectedMessage;
		
		@FindBy(name="established")
		protected WebElement establishedMessage;
		
//	@FindBy(xpath="//*[@AutomationId='buttonExecute']")
	@FindBy(name="Execute")
	protected WebElement executeButton;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad1']")
		@FindBy(name="1")
		protected WebElement keyPad1Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad2']")
		@FindBy(name="2")
		protected WebElement keyPad2Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad3']")
		@FindBy(name="3")
		protected WebElement keyPad3Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad4']")
		@FindBy(name="4")
		protected WebElement keyPad4Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad5']")
		@FindBy(name="5")
		protected WebElement keyPad5Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad6']")
		@FindBy(name="6")
		protected WebElement keyPad6Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad7']")
		@FindBy(name="7")
		protected WebElement keyPad7Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad8']")
		@FindBy(name="8")
		protected WebElement keyPad8Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad9']")
		@FindBy(name="9")
		protected WebElement keyPad9Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad0']")
		@FindBy(name="0")
		protected WebElement keyPad0Button;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad10']")
		@FindBy(name="*")
		protected WebElement asteriskButton;
		
//		@FindBy(xpath="//*[@AutomationId='keyPad11']")
		@FindBy(name="#")
		protected WebElement hashtagButton;
		
	@FindBy(xpath="//*[@AutomationId='loginButton']")
	protected WebElement loginButton;
	
	@FindBy(xpath="//*[@AutomationId='logoutButton']")
	protected WebElement logoutButton;
	
	@FindBy(xpath="//*[@AutomationId='readyButton']")
	protected WebElement readyButton;
	
	@FindBy(xpath="//*[@AutomationId='notreadyButton']")
	protected WebElement notReadyButton;
	
	@FindBy(xpath="//*[@AutomationId='workButton']")
	protected WebElement workButton;
	
	@FindBy(xpath="//*[@AutomationId='holdButton']")
	protected WebElement holdButton;
	
	@FindBy(xpath="//*[@AutomationId='releaseButton']")
	protected WebElement releaseButton;
	
	@FindBy(xpath="//*[@AutomationId='transferButton']")
	protected WebElement transferButton;
	
	@FindBy(name="Blind Transfer")
	protected WebElement blindTransferButton;
	
	@FindBy(name="Conference")
	protected WebElement conferenceButton;
	
	@FindBy(xpath="//*[@AutomationId='display1']")
	protected WebElement displayInfoField1;
	
	@FindBy(xpath="//*[@AutomationId='ListViewSubItem-3']")
	protected List<WebElement> callStateField;
	
	protected void enterKeypad(String enterCode) {
		for (int i = 0; i < enterCode.length(); i++) {
			switch(enterCode.charAt(i)) {
				case '1':
					keyPad1Button.click();
					break;
				case '2':
					keyPad2Button.click();
					break;
				case '3':
					keyPad3Button.click();
					break;
				case '4':
					keyPad4Button.click();
					break;
				case '5':
					keyPad5Button.click();
					break;
				case '6':
					keyPad6Button.click();
					break;
				case '7':
					keyPad7Button.click();
					break;
				case '8':
					keyPad8Button.click();
					break;
				case '9':
					keyPad9Button.click();
					break;
				case '0':
					keyPad0Button.click();
					break;
				case '*':
					asteriskButton.click();
					break;
				case '#':
					hashtagButton.click();
					break;
			}
		}
	}
	
}