package com.abc.dataSpecific;

import com.abc.EndPoints.BankUrl;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetDetailsOfAllAccount {
	public Response GetDetailsOfAllAccount()
	{
	Response response = 			
	RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.when().get(BankUrl.GetAllPostUrl)
	.then().statusCode(200).extract().response();

	return response;
	
}
}
