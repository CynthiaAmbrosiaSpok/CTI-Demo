package winapp.cti.qa.util;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	private static ConstantVariables constantVariables = new ConstantVariables();;
	private static String path = constantVariables.extentFactoryReportPath;
	
	public static ExtentReports getInstance() {
		ExtentReports extent;
		
		extent = new ExtentReports(path, false);
		
		return extent;
	}
	
	public static void deleteExtentReport() {
		File file = new File(path);
		
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete file, located at: " + path);
		}
	}
	
}