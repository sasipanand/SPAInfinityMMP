package org.iit.healthcare.mmp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Maven, Tomcat are from org.apache.org
// Selenium  has runtime exceptions(element not found exception). Java has compile time(IO exception,file exception...) and runtime exceptions
public class AppLibrary {

	
	public static String getFutureDate(int noofDays)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noofDays);
		Date d =calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("d/MMMM/YYYY");
		String date = sdf.format(d);
		String dateArr[] = date.split("/");
		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		return date;
	}
	

	public static String[][] readXLSX(String fileName,String sheetName) throws IOException 
	{
		//
		//In Java if you need to read a file that is xls/xlsx/... you need API's called File. 
		
		File f = new File(fileName);//U first need to point the File to Xlsx
		FileInputStream fis = new FileInputStream(f); // u are reading the file using the FileInputStream class and for writing /outputting a file you need fileoutputstream
		try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {// XSSFWorkbook packages are available in org.apache.eoi for xlsx. XSSWorkbook packages are available in org.apache.eoi for xls
			XSSFSheet sheet = wb.getSheet(sheetName);//get the sheeting
			int rows = sheet.getPhysicalNumberOfRows();// we are writing a logic to get the no of defined rows, no of defined columns that is row, col size in a sheet and store it in an array
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("number of rows" + rows);
			System.out.println("number of cols" + cols);
			String inputData[][] = new String[rows][cols];
			for(int i=0;i<rows;i++)
			{
				for(int j=0;j<cols;j++)
				{
					 XSSFCell cell = sheet.getRow(i).getCell(j);// u get/fix row index first(getRow(i)) and then fix cell index(getCell(j)) and then get the value . get row. getcol index is not available . First fix the row,row index then specific column index you are providing and getting the values 
					 inputData[i][j]= cell.getStringCellValue();// u get the value in the cell and start storing it in an array
					 System.out.print(inputData[i][j]);
				}
				 System.out.println();
			}
			return inputData;//u return an array
		}
		
	}
	
}