package com.abc.dataSpecific;
import com.abc.EndPoints.BankUrl;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetDetailsOfSpecificDetails {
	
	public Response GetSpecDetails(int IdNumber ) {
	Response response = RestAssured
			.given().baseUri(BankUrl.BaseUrl).pathParam("id", IdNumber)
			.when().get(BankUrl.GetSpecDelUrl)
			.then().statusCode(200).extract().response();
			
			return response;
}
}