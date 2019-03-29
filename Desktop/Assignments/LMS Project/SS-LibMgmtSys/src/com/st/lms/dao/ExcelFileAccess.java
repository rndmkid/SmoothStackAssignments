package com.st.lms.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileAccess {

	private String path = "./resources/LibraryDB.xlsx"; 
	private XSSFWorkbook workBook;
	private String sheetName;
	
	public ExcelFileAccess(String sheet){
		this.sheetName = sheet;
	}
	
	public Sheet open(){
		File file = new File(path);
		try {
			this.workBook = new XSSFWorkbook(file);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.workBook.getSheet(this.sheetName);
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
	
	public void close(){
		try {
			this.workBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
