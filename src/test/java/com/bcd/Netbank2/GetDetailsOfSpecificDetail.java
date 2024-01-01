package com.bcd.Netbank2;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetDetailsOfSpecificDetail {
	
	public Response GetSpecDetails(int IdNumber ) {
	Response response = RestAssured
			.given().baseUri(BankUrl.BaseUrl).pathParam("id", IdNumber)
			.when().get(BankUrl.GetSpecDelUrl)
			.then().statusCode(200).extract().response();
			
			return response;
}
}