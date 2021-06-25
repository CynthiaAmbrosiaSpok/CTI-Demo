package winapp.cti.qa.util;

public class GeneralMethods {
	
	public void waitFor(int seconds) {
		//Initialize Variable(s)
		seconds = seconds * 1000;
		
		//Wait for the specified amount of time
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * This method is used when the xpath of a WebElement cannot be pre-determined
	 *    (aka. Either the WebElement has a changing xpath, or a different WebElement may need to be referenced, depending on the test)
	 * This method can be called by using...
	 *    .createXPath("//a[contains(text(), \"{0}\")]", varName0);
	 *    .createXPath("//a[contains(text(), \"{0}\") and @name=\"{1}\"]", varName0, varName1);
	 *    .createXPath("//a[contains(text(), \"{0}\") and @name=\"{1}\"] and @type=\"{2}\"]", varName0, varName1, varName2);
	 *    etc..., where varName0 will replace {0}, varName1 will replace {1}, varName2 will replace {2}, etc.
	 *    Calling this method will work with any number of varName# entries, as long as there are {#} to coincide with them
	 * 
	 * This method is used in the 'selectProduct' method in the 'SchoolStorePage.java' class (underneath the 'src/main/java' folder & 'com.jostens.qa.pages' package)
	 */
	public String createXPath(String xpathExp, Object ...args) {
		for (int i = 0; i < args.length; i++) {
			xpathExp = xpathExp.replace("{" + i + "}", (CharSequence) args[i]);
		}
		
		return xpathExp;
	}
}