package com.cyient.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {
	
	@DataProvider
	public Object[][] validCredentialExcelData() throws IOException{
Object[][] main=ExcelUtils.getSheetIntoObjectArray("src/test/resources/testdata/OpenEMRData.xlsx","validCredentialTest");
return main;

		
	}
	


	public Object[][] invalidCredentialData(){
		
		Object[][] in=new Object[2][4];
		in[0][0]="john"	;
		in[0][1]="john123"	;
		in[0][2]="Dutch"	;
		in[0][3]="Invalid username or password"	;
		
		in[1][0]="dutch"	;
		in[1][1]="dutch123"	;
		in[1][2]="Czech"	;
		in[1][3]="Invalid username or password" 	;
		
		
		return in;
	}

}
