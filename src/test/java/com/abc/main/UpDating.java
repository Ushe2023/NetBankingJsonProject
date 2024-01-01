//
//4. Verify Updating Account Information:
//   - Send a PUT request to update information for a specific bank account.
//   - Validate that the response code is 200.
//   - Retrieve the updated account details and ensure they match the new information.

//json file-bookAccount.json



package com.abc.main;

import java.util.Scanner;

import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.abc.EndPoints.EndPoints;
import com.abc.data.Payload;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

@Test
public class UpDating {
ExtentReports extentReports;
	
	@BeforeTest
	public void CreateReport() {
		extentReports = new ExtentReports();
		ExtentSparkReporter extentSparkReporter1 = new ExtentSparkReporter("./Reports/Update/Pass.html");
		extentReports.attachReporter(extentSparkReporter1);
		
		ExtentSparkReporter extentSparkReporter2 = new ExtentSparkReporter("./Reports/Update/Fail.html");
		extentReports.attachReporter(extentSparkReporter2);
		
		extentSparkReporter1.filter().statusFilter().as(new Status[] {Status.PASS}).apply();
		extentSparkReporter2.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		
	}

	@AfterTest
	public void push() {
		extentReports.flush();
	}
	public void UpdatingTheDetails() throws JsonProcessingException {
		ExtentTest extentTest = extentReports.createTest("This is to retrieve Specific AccountDetails");
		
		EndPoints e = new EndPoints();
		Payload p = new Payload();
		int bal=0;
		String name=null,gender=null;
		JSONObject jsonObject = new JSONObject();
		Scanner sc = new Scanner(System.in);
		
		Response response1= e.GetDetailsOfAllAccountHolders();
		JsonPath extractor= response1.jsonPath();
		String id = extractor.getString("id");
		System.out.println(id);
		
		System.out.println("Enter the ID number of the Account for which Details should be updated: ");
		int IdNumber =sc.nextInt();
		extentTest.log(Status.INFO,"The AccountId to be deleted is: "+IdNumber);
		System.out.println("Account details are" +response1.asPrettyString());
		System.out.println("Which data do you want to modify. Please select the options:");
		System.out.println("a:Account Name");
		System.out.println("b:gender");
		System.out.println("c:Account Balance");
		String option = sc.next();
		if(option.contentEquals("a")) {
				
		System.out.println("Enter the details you need to modify: Account Name");
		
		 name = sc.next();
		 p.setAccountName(name);
		 jsonObject.put("accountName", name);
		 extentTest.log(Status.INFO,"Account name will be updated as "+name);
		}
		else if(option.contentEquals("b")) {
			System.out.println("Enter the gender:");
			 gender = sc.next();	
			 p.setAccuntGender(gender);
			 jsonObject.put("accountGender", gender);
			 extentTest.log(Status.INFO,"Gender will be updated as "+gender);
		} 
		else if(option.contentEquals("c")) {
			System.out.println("Enter the Account Balance:");
			bal = sc.nextInt();	
			p.setAccountBalance(bal);
			jsonObject.put("accountBalance", bal);
			extentTest.log(Status.INFO,"Account balance will be updated to: "+bal);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String InputJson  = mapper.writeValueAsString(p);
		System.out.println(InputJson);
		
		System.out.println("Updated Details are displayed as follows: ");
		
 		Response response =
 				e.patchContent(jsonObject, IdNumber);
 		System.out.println(response.statusCode());
 		
 		System.out.println(response.asPrettyString());
 		
 		int ActualStatusCode = response.statusCode();
 		int ExpectStatusCode = 200;
 		try {
 			Assert.assertEquals(ActualStatusCode, ExpectStatusCode);
 			System.out.println("Correct Status code is displayed: " +ActualStatusCode);
 			extentTest.log(Status.INFO,"Correct Status code is displayed: "+ActualStatusCode);
 			extentTest.log(Status.INFO,"Account details is: "+response.asPrettyString());
 		}catch (AssertionError er) {
 			System.out.println("Incorrect status code is displayed: " +ActualStatusCode);
 			extentTest.log(Status.INFO,"Incorrect Status code is displayed: "+ActualStatusCode);
 			extentTest.log(Status.INFO,"Account details is: "+response.asPrettyString());
 			
			
 		}
	}
}
