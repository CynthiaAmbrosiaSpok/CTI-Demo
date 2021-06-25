package winapp.cti.qa.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoadingAppsLocators {
	
	@FindBy(xpath="//*[@AutomationId='Microsoft.Windows.Explorer']")
	protected WebElement fileExplorerTaskbarButton;
	
	@FindBy(name="Local Disk (C:)")
	protected WebElement localDiskButton;
	
	@FindBy(name="Previous Locations")
	protected WebElement fileDropDownButton;
	
	@FindBy(xpath="//*[@AutomationId='41477']")
	protected WebElement filePathFieldButton;
	
	@FindBy(xpath="//*[@AutomationId='Maximize-Restore']")
	protected WebElement maximizeFileExplorerButton;
	
	@FindBy(name="SpokCTIClient")
	protected WebElement spokCTIClientExecutableButton;
	
	@FindBy(xpath="//*[@ClassName='CabinetWClass']//*[@AutomationId='Minimize-Restore']")
	protected WebElement minimizeFileExplorerButton;
	
}