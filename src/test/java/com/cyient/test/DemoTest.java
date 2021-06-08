package com.cyient.test;

import org.testng.annotations.DataProvider;

public class DemoTest {
	
	
	@DataProvider
	public Object[][] validData(){
		Object[][] main=new Object[3][2];
		main [0][1] ="admin";
		main [0][2] ="pass";
		
		
		return main;
	}
	

}
