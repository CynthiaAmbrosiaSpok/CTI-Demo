package winapp.cti.qa.util;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import winapp.cti.qa.base.TestBase;

public class Listeners extends TestBase implements IInvokedMethodListener, ITestListener, ISuiteListener {
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		//System.out.println("onStart");
	}
	
	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		//System.out.println("onFinish");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestFailure -> Test Name: " + result.getName() + " in class name: " + result.getClass());
		System.out.println("onTestFailure -> Test Status: " + result.getStatus() + "/" + result.toString());
		
//		System.out.println(result.getTestContext());
//		System.out.println(result.getThrowable());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("onTestSkipped: " + testResult.getTestClass().getName() +
		//		" => " + method.getTestMethod().getMethodName());
		
		System.out.println("onTestSkipped -> Test Name: " + result.getName() + " / " + result.getName() + "-" + result.getMethod());
		System.out.println("Test Skipped due to:");
		System.out.println(result.getSkipCausedBy());
		//reportLogger.log(LogStatus.INFO,  "Web application open");
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	}
	
}