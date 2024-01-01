package com.abc.data;

import org.testng.annotations.DataProvider;

public class Data {
	
	@DataProvider
	public Object[][] DataHolders(){
		
		Object[][] data = {
				{"Kiran","Female",2300},
				{"Ramzi","Male",20000}
				
		};
			return data;
			
		
	}

}
