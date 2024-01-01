//Fetching the specific data and validating if the correct data is fetched.
//Json file is bookAccountSpecific.json

package com.bcd.Netbank2;
import static org.testng.Assert.assertEquals;

import java.util.Scanner;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.abc.NetBank.GetDetailsOfAllAccount;
//import com.abc.NetBank.GetDetailsOfSpecificDetails;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;


public class Specific {
	
	ExtentReports extentReports;
	
	@BeforeTest
	public void CreateReport() {
		extentReports = new ExtentReports();
		ExtentSparkReporter extentSparkReporter1 = new ExtentSparkReporter("./Reports/SpecificGetSpecificAccDetails/Pass.html");
		extentReports.attachReporter(extentSparkReporter1);
		
		ExtentSparkReporter extentSparkReporter2 = new ExtentSparkReporter("./Reports/SpecificGetSpecificAccDetails/Fail.html");
		extentReports.attachReporter(extentSparkReporter2);
		
		extentSparkReporter1.filter().statusFilter().as(new Status[] {Status.PASS}).apply();
		extentSparkReporter2.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		
	}

	@AfterTest
	public void push() {
		extentReports.flush();
	}
	
	@Test
	public void GetSpecificAccDetails(){
		
		ExtentTest extentTest = extentReports.createTest("This is to retrieve Specific AccountDetails");
		GetDetailsOfSpecificDetail e = new GetDetailsOfSpecificDetail();
		GetDetailsOfAllHolders e2= new GetDetailsOfAllHolders();
		Scanner sc=new Scanner(System.in);
		extentTest.log(Status.INFO, "Entering the Accound id to be displayed");
		Response response2 = e2.GetDetailsOfAllHolders();
		
		
		JsonPath extractor= response2.jsonPath();
		String id = extractor.getString("id");
		System.out.println(id);
		System.out.println("Enter the A/c id whose details to be displayed:   ");
		int n = sc.nextInt();
		extentTest.log(Status.INFO, "Entered the A/c id whose details to be displayed:   " +n);
		
		Response response = e.GetSpecDetails(n);

        JsonPath jsonResponse = response.jsonPath();
        String actualAccountName = jsonResponse.getString("accountName");
        String actualAccountGender = jsonResponse.getString("accountGender");
        String actualAccountBalance = jsonResponse.getString("accountBalance");

        // Define expected data
        
        String expectedAccountName;
        String expectedAccountGender;
        String expectedAccountBalance;	
        if (n==101) {
        expectedAccountName = "Maanvi";
        expectedAccountGender = "Female";
        expectedAccountBalance = "2000";
        }else {
        	expectedAccountName = "Spoorthi";
            expectedAccountGender = "Female";
            expectedAccountBalance = "11600";	
        }
        // Compare actual data with expected data using assertions
        assertEquals(actualAccountName, expectedAccountName, "Account Name mismatch");
        assertEquals(actualAccountGender, expectedAccountGender, "Account Gender mismatch");
        assertEquals(actualAccountBalance, expectedAccountBalance, "Account Balance mismatch");
 
		
		
		extentTest.log(Status.INFO, "The details are: ");
		System.out.println("The Account details of the ID number "+n+ " are:");
		
		extentTest.log(Status.PASS, " "+response.asPrettyString());
		System.out.println(response.asPrettyString());
    	System.out.println("Status Code is: "+response.statusCode());
		
		int ActualStatusCode = response.statusCode();
		int ExpectedStatusCode= 200;
		
		try {
			Assert.assertEquals(ActualStatusCode, ExpectedStatusCode);
			extentTest.log(Status.PASS, "Correct Status code is displayed: "+ActualStatusCode);
			System.out.println("Correct Status code is displayed: "+ActualStatusCode);
		}catch(AssertionError er)
		{
			extentTest.log(Status.FAIL, "InCorrect Status code is displayed: "+ActualStatusCode);
			System.out.println("Incorrect Status Code is displayed: "+ActualStatusCode);
		}
	
	
		
	}
}
