
//json file-bookAccount.json
//Creating an account holder data. Data provider is Data.java



package com.abc.main;

import org.testng.annotations.Test;

import com.abc.EndPoints.EndPoints;
import com.abc.data.Data;
import com.abc.data.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class CreatingData{
	
	
@Test(dataProviderClass=Data.class , dataProvider = "DataHolders")
public void TestCaseOne(String Aname, String AGender, int ABalance) throws JsonProcessingException {
	
	Payload p = new Payload();
	p.setAccountName(Aname);
	p.setAccuntGender(AGender);
	p.setAccountBalance(ABalance);
	
	ObjectMapper mapper = new ObjectMapper();
	String JsonInput = mapper.writeValueAsString(p);
	
	EndPoints e = new EndPoints();
	e.postData(JsonInput);
}
}