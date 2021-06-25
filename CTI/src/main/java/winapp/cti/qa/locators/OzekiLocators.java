package winapp.cti.qa.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OzekiLocators {
	
	@FindBy(xpath="//*[@AutomationId='btnRegister']")
	protected WebElement registerButton;
	
	@FindBy(xpath="//*[@AutomationId='tbPhoneLineStatus']")
	protected WebElement registerStatusField;
	
	@FindBy(xpath="//*[@AutomationId='tbDial']")
	protected WebElement dialpadField;
	
	@FindBy(xpath="//*[@AutomationId='btnKey0']")
	protected WebElement dialpad0Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey1']")
	protected WebElement dialpad1Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey2']")
	protected WebElement dialpad2Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey3']")
	protected WebElement dialpad3Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey4']")
	protected WebElement dialpad4Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey5']")
	protected WebElement dialpad5Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey6']")
	protected WebElement dialpad6Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey7']")
	protected WebElement dialpad7Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey8']")
	protected WebElement dialpad8Button;
	
	@FindBy(xpath="//*[@AutomationId='btnKey9']")
	protected WebElement dialpad9Button;
	
//	@FindBy(xpath="//*[@AutomationId='btnDialAudio']")
	@FindBy(name="Dial (A)")
	protected WebElement callPhoneNumberAudioButton;
	
	//Add new phone account ('Add SIP Account') locators
	@FindBy(name="Add SIP account")
	protected WebElement addSIPAccountButton;
	
	@FindBy(xpath="//*[@AutomationId='tbDisplayName']")
	protected WebElement displayNameField;
	
	@FindBy(xpath="//*[@AutomationId='tbUserName']")
	protected WebElement userNameField;
	
	@FindBy(xpath="//*[@AutomationId='tbRegisterName']")
	protected WebElement registerNameField;
	
	@FindBy(xpath="//*[@AutomationId='tbPassword']")
	protected WebElement passwordField;
	
	@FindBy(xpath="//*[@AutomationId='tbDomain']")
	protected WebElement domainField;
	
	@FindBy(xpath="//*[@AutomationId='cbTransportType']")
	protected WebElement transportTypeDropDown;
	
	@FindBy(xpath="//*[@AutomationId='tbSTUNserver']")
	protected WebElement stunServerField;
	
	@FindBy(xpath="//*[@AutomationId='btnOK']")
	protected WebElement okButton;
	
	@FindBy(xpath="//*[@AutomationId='btnAnswer']")
	protected WebElement answerButton;
	
	@FindBy(xpath="//*[@AutomationId='btnHangup']")
	protected WebElement hangupButton;
	
	@FindBy(name="Dialing")
	protected WebElement callStateDialing;
	
	@FindBy(name="Ringing")
	protected WebElement callStateRinging;
	
	@FindBy(name="Connected")
	protected WebElement callStateConnected;
	
}