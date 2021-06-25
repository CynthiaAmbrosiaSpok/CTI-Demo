package winapp.cti.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelMethods {
	
	//Initialize Variable(s)
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	//Location of the Excel File
	private static String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "winapp" + File.separator + "cti" + File.separator + "qa" + File.separator + "testdata" + File.separator + "CTI.xlsx";
	public static String sheetName = "";
	
	public void setSheetName(String desiredSheetName) {
		sheetName = desiredSheetName;
	}
	
	//Method returns the current time when called
	public String getCurrentDateTime() {
		//Get the current time - will be used to indicate when the test finished for the Excel File output
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
		LocalDateTime currentDateTime = LocalDateTime.now();
		Format timeZone = new SimpleDateFormat("z");
		String currentDateTimeStr = dateTimeFormat.format(currentDateTime) + " (" + timeZone.format(new Date()) + ")";
		
		return currentDateTimeStr;
	}
	
	//Retrieve a single row of a DataSheet - each row of a DataSheet is iterated through by another method in this java class before sending off the DataSheet to the active script
	public static String[] retrieveRow(XSSFRow row, int numCols) {
		//Initialize Variable(s)
		String[] importRow = new String[numCols];
		XSSFCell cell;
		
		for (int i = 0; i < row.getLastCellNum(); i++) {
			//Set the values for Variable(s)
			cell = row.getCell(i);
			
			//If a cell has no value, create a cell & specify it as blank, then add that cell
			if(cell != null) {
				importRow[i] = cell.toString();
			} else {
				row.createCell(i);
				importRow[i] = row.getCell(i).toString();
			}
		}
		
		importRow[numCols-1] = "0";
		
		return importRow;
	}
	
	//Retrieve an Excel DataSheet for automation testing
	@DataProvider(name="inputs")
	public String[][] getDataTable() {
		
		//Initialise Variable(s)
		String[][] importDataTable = null;
		System.out.println(path + " - " + sheetName);
		try {
			//Open the Excel File
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			int numRows = ExcelWSheet.getPhysicalNumberOfRows() - 1;
			int numCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells() + 1;
			
			importDataTable = new String[numRows][numCols];
			
			//Setup an 2D ArrayList to use as Parameters
			for (int i = 1; i < ExcelWSheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = ExcelWSheet.getRow(i);
				importDataTable[i-1] = retrieveRow(row, numCols);
			}
			
			for (int i = 0; i < importDataTable.length; i++) {
				importDataTable[i][numCols-1] = String.valueOf(i+1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return importDataTable;
	}

	public String getDataTableCell(int rowNum, int colNum) {
		//Initialize Variable(s)
		String cellValue = "";
		
		try {
			//Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			
			//Access the Excel DataSheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			if (Cell == null) {
				cellValue = "";
			} else {
				cellValue = Cell.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cellValue;
	}

	//Send output to a specific DataSheet cell
	public void setDataTableCell(String result, int rowNum, int colNum) {
		//Initialize Variable(s)
		colNum = colNum - 1; //Reduce the colNum by 1, since the ExcelSheet starts with 0. (no need to do this with rowNum, since the rows have row #0 dedicated to column titles)
		
		try {
			//Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			
			//Access the Excel DataSheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}
			
			//Open the file to write the results
			FileOutputStream outputFile = new FileOutputStream(path);
			
			ExcelWBook.write(outputFile);
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Send output to a specific DataSheet cell
	public void setDataTableCell(Double result, int rowNum, int colNum) throws Exception {
		try {
			//Ouput to the Excel File
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}
			
			//Open the file to write the results
			FileOutputStream outputFile = new FileOutputStream(path);
			
			ExcelWBook.write(outputFile);
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}