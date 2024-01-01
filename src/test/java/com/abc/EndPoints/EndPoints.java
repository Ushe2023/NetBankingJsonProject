package com.abc.EndPoints;

import org.apache.commons.lang3.Validate;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.trial.bank.trialmy.Url;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EndPoints {

public Response GetDetailsOfAllAccountHolders()
	{
	Response response = 			
	RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.when().get(BankUrl.GetAllPostUrl)
	.then().statusCode(200).extract().response();

	return response;
	
}

public Response GetDetailsOfSpecificAccountHolders(int IdNumber) {
	
	Response response = RestAssured
	.given().baseUri(BankUrl.BaseUrl).pathParam("id", IdNumber)
	.when().get(BankUrl.GetSpecDelUrl)
	.then().statusCode(200).extract().response();
	
	return response;
		}

public Response postData(String info) {
	
	 Response response = RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.contentType("application/json")
	.body(info.toString())
	.when().post(BankUrl.GetAllPostUrl)
	.then().extract().response();
	return response;
}

public Response putData(String info, int IdNumber) {
	Response response = RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.contentType("application/json")
	.body(info.toString())
	
	.when().put(BankUrl.GetSpecDelUrl, IdNumber)
	.then().extract().response();
	
	return response;

}

public Response DeleteDetails(int Number) {
	
	Response  response= RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.when().delete(BankUrl.GetSpecDelUrl, Number)
	.then().extract().response();

	return response;
}
public Response patchContent(JSONObject jsonObject, int IdNumber) throws JsonProcessingException {


	Response response = RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.contentType("application/json")
	.body(jsonObject.toString())	
	.when().patch(BankUrl.GetSpecDelUrl,IdNumber)
	.then().statusCode(200).extract().response();
	return response;

		
}

public void patchContenat(JSONObject jsonObject, int IdNumber) throws JsonProcessingException {

			Response response = RestAssured
			.given().baseUri(BankUrl.BaseUrl)
			.contentType("application/json")
			.body(jsonObject.toString())	
			.when().patch(BankUrl.GetSpecDelUrl,IdNumber)
			.then().statusCode(200).extract().response();
		
}


}
