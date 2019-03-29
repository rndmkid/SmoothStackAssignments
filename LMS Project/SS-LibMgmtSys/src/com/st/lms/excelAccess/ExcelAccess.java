package com.st.lms.excelAccess;

import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelAccess {
	
	
	// TODO Add Default path//
	private String path = "./resources/LibraryDB.xlsx"; 
	private XSSFWorkbook workBook;
	private String sheetName;
	
	public ExcelAccess(String sheet) throws FileNotFoundException, IOException, InvalidFormatException{
		File file = new File(path);
		this.workBook = new XSSFWorkbook(file);
		this.sheetName = sheet;
	}
	
	public Sheet sheet(){
		return this.workBook.getSheet(this.sheetName);
	}
	
	
	public Sheet sheet(String name){
		return this.workBook.getSheet(name);
		
	}
	
	public void save(){
		try {
			String outpath = this.path.substring(0, this.path.lastIndexOf(".xlsx")) + "1.xlsx";
			File newBook = new File(outpath);
			FileOutputStream out = new FileOutputStream(newBook);
	        this.workBook.write(out);
	        
	        File oldBook = new File(path);
	        oldBook.delete();
	        newBook.renameTo(oldBook);
	        
	        
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException{
		this.workBook.close();
	}

}
