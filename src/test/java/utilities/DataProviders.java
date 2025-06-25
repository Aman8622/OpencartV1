package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		String path = "/Users/amankkashyap/eclipse-workspace/OpencartV1/testData/LoginData.xlsx"; // Excel file from TestData folder.
		
		ExcelUtility xlutil = new ExcelUtility(path); // XLUtility Object
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols]; // 2D Array to store Excel Data.
		
		for(int i=1;i<=totalrows;i++) { //1  reads data from excel file and stores it into a 2D array.
			for(int j=0;j<totalcols;j++) { //0    i->rows, j->columns
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata; // returns a 2D array.
	}
	
}
