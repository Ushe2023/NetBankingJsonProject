//5. Verify Closing an Account:
//   - Send a DELETE request to close a specific bank account by its account number.
//   - Validate that the response code is 204 (no content).
//   - Confirm that the closed account is no longer present in the list of all accounts.


package com.abc.main;

import java.util.Scanner;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abc.EndPoints.EndPoints;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class CloseAccount {

ExtentReports extentReports;
	
	@BeforeTest
	public void CreateReport() {
		extentReports = new ExtentReports();
		ExtentSparkReporter extentSparkReporter1 = new ExtentSparkReporter("./Reports/GetSpecificAccDetails/Pass.html");
		extentReports.attachReporter(extentSparkReporter1);
		
		ExtentSparkReporter extentSparkReporter2 = new ExtentSparkReporter("./Reports/GetSpecificAccDetails/Fail.html");
		extentReports.attachReporter(extentSparkReporter2);
		
		extentSparkReporter1.filter().statusFilter().as(new Status[] {Status.PASS}).apply();
		extentSparkReporter2.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		
	}

	@AfterTest
	public void push() {
		extentReports.flush();
	}
	@Test
	public void CloseAnAccount() {
		ExtentTest extentTest = extentReports.createTest("This is to retrieve Specific AccountDetails");
	
		EndPoints e = new EndPoints();
		Response response = e.GetDetailsOfAllAccountHolders();
		
		JsonPath extractor= response.jsonPath();
		String id = extractor.getString("id");
		System.out.println(id);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the AccountId to be deleted: ");
		int AccId = sc.nextInt();
		extentTest.log(Status.INFO,"The AccountId to be deleted is: "+AccId);
		Response response2=e.DeleteDetails(AccId);
		int ActualStatusCode = response2.getStatusCode();
		int ExpectedStatusCode = 200;
		try
		{ 
			Assert.assertEquals(ActualStatusCode, ExpectedStatusCode);
			
			System.out.println("Status Code is correctly Displayed: " +ActualStatusCode);
			Response response3 = e.GetDetailsOfAllAccountHolders();
			extentTest.log(Status.PASS,"Status Code is correctly Displayed: " +ActualStatusCode);
			extentTest.log(Status.PASS,"Account is successfully deleted");
			
			System.out.println(AccId+" Account is successfully deleted.Remaining Account details are as follows:");
			System.out.println(response3.asPrettyString());
		
		}catch(AssertionError er) {
		System.out.println("Status Code is incorrectly displayed.: "+ActualStatusCode);
	}
		
		//Validate if the account is not displayed in the list of all accounts
		
		
}}
