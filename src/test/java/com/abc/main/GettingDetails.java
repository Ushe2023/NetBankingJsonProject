//1. Verify Retrieval of All Accounts:
//   - Send a GET request to retrieve all bank accounts.
//   - Validate that the response code is 200.
//   - Check that the response body contains the expected number of bank accounts.

package com.abc.main;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abc.EndPoints.EndPoints;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;

public class GettingDetails {
	
	ExtentReports extentReports;
	@BeforeTest
	public void createReport() {
		extentReports = new ExtentReports();
		ExtentSparkReporter  extentSparkReporter1 = new ExtentSparkReporter("./Reports/GetAllDetails/Pass.html");
		extentReports.attachReporter(extentSparkReporter1);
		ExtentSparkReporter  extentSparkReporter2 = new ExtentSparkReporter("./Reports/GetAllDetails/Fail.html");
		extentReports.attachReporter(extentSparkReporter2);
		
		extentSparkReporter1.filter().statusFilter().as(new Status[] {Status.PASS}).apply();
		extentSparkReporter2.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
	}
	
	@AfterTest
	public void push() {
		extentReports.flush();
	}
	@Test
	public void GetDetailsOfAllAccountHolders()
	{
		
	ExtentTest extentTest = extentReports.createTest("This is a Report of retrieving all the account details");
		
	EndPoints e = new EndPoints();
	
	
	int ExpectedCount = 6;//Need to check before running
	
	
	
	extentTest.log(Status.INFO,"Will be displaying all the account details");
	Response response1= e.GetDetailsOfAllAccountHolders();
	
	extentTest.log(Status.INFO,"Details of all the account holders in the bank are correctly displayed. They are: ");
	System.out.println("Details of all the account holders in the bank are correctly displayed. They are:" );
	extentTest.log(Status.INFO,""+response1.asPrettyString());
	System.out.println(response1.asPrettyString());
	extentTest.log(Status.INFO,"Status Code: "+response1.statusCode());
	System.out.println("Status Code: "+response1.statusCode());

	//Validating Status Code
	int ActualStatusCode = response1.statusCode();
	int ExpectedStatusCode=200;
		
	try {
		Assert.assertEquals(ActualStatusCode, ExpectedStatusCode);
		extentTest.log(Status.PASS,"Correct Status code is displayed: "+ActualStatusCode);
		System.out.println("Correct Status code is displayed: "+ActualStatusCode);
	}catch(AssertionError er)
	{
		extentTest.log(Status.FAIL,"Incorrect Status Code is displayed: "+ActualStatusCode);
		System.out.println("Incorrect Status Code is displayed: "+ActualStatusCode);
	}
	
	//Validating number of accounts
	
			int ActualCount = response1.jsonPath().getList("Holder").size();
			System.out.println("Number of Accounts: "+ActualCount);
			
			if(ActualCount == ExpectedCount) {
				System.out.println("Number of accounts displayed is correct: "+ActualCount);
				extentTest.log(Status.PASS,"Number of accounts displayed is correct: "+ActualCount);
				
			}else
			{
				System.out.println("Number of accounts displayed is incorrect: "+ActualCount);
				extentTest.log(Status.FAIL,"Number of accounts displayed is incorrect: "+ActualCount);
}


}
}
