package com.bcd.Netbank2;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetDetailsOfAllHolders {
	public Response GetDetailsOfAllHolders()
	{
	Response response = 			
	RestAssured
	.given().baseUri(BankUrl.BaseUrl)
	.when().get(BankUrl.GetAllPostUrl)
	.then().statusCode(200).extract().response();

	return response;
	
}
}
