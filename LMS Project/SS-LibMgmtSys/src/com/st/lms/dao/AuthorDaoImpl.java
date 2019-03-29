package com.st.lms.dao;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.st.lms.daoInterfaces.AuthorDAO;
import com.st.lms.model.Author;

public class AuthorDaoImpl implements AuthorDAO{
	
	private ExcelFileAccess ea;
	
	private final int ID_ROW = 0;
	private final int NAME_ROW = 1;
	
	public AuthorDaoImpl() {
		this.ea = new ExcelFileAccess("Author");
	}
	
	@Override
	public Author save(Author author){
		XSSFSheet sheet = (XSSFSheet) ea.open();
		int lastRow=sheet.getLastRowNum();
		int id = (int) sheet.getRow(sheet.getLastRowNum()).getCell(ID_ROW).getNumericCellValue() + 1;
		author.setId(id);
		Row row = sheet.createRow(++lastRow);
		row.createCell(ID_ROW).setCellValue(id);
		row.createCell(NAME_ROW).setCellValue(author.getName());
		ea.save();
		ea.close();
		return author;
	}
	
	@Override
	public void delete(Author author) {
		Sheet sheet = ea.open();
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++){
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			XSSFCell cell = row.getCell(0);
			if(cell.getNumericCellValue() == (double) author.getId()){
				if(i+1 > sheet.getLastRowNum())
					sheet.removeRow(row);
				else
					sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
				//sheet.removeRow(row);
				break;
			}
		}
		ea.save();
		ea.close();
	}
	
	@Override
	public void update(Author author){
		Sheet sheet = ea.open();
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++){
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			XSSFCell cell = row.getCell(ID_ROW);
			if(cell.getNumericCellValue() == (double) author.getId()){
				row.getCell(NAME_ROW).setCellValue(author.getName());
				break;
			}
		}
		ea.save();
		ea.close();
	}
	
	//TODO EDIT FIND TO SEARCH ALL PARAMETERS
	public Author find(Author author) {
//		
//		Sheet sheet = ea.open();
//		
//		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
//			XSSFRow row = (XSSFRow) sheet.getRow(r);
//			XSSFCell cell = row.getCell(NAME_ROW);
//			if(cell.getStringCellValue().equals(author.getName())){
//				author.setId((int) row.getCell(ID_ROW).getNumericCellValue());
//				ea.close();
//				return author;
//			}
//		}
//		ea.close();
		return null;
	}

	@Override
	public Author find(int id){
		Sheet sheet = ea.open();
		Author author = new Author();
		
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			XSSFCell cell = row.getCell(ID_ROW);
			if((int) cell.getNumericCellValue() == id){
				author.setId((int) row.getCell(ID_ROW).getNumericCellValue());
				author.setName(row.getCell(NAME_ROW).getStringCellValue());
				ea.close();
				return author;
			}
		}
		ea.close();
		return null;
	}
	
	public Author find(String name){
		Sheet sheet = ea.open();
		Author author = new Author();
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			XSSFCell cell = row.getCell(NAME_ROW);
			if(cell.getStringCellValue().equals(name)){
				author.setId((int) row.getCell(ID_ROW).getNumericCellValue());
				author.setName(row.getCell(NAME_ROW).getStringCellValue());
				ea.close();
				return author;
			}
		}
		ea.close();
		return null;
	}
	
	
	public List<Author> findAll(){
		Sheet sheet = ea.open();
		List<Author> list = new ArrayList();
		
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			Author author = new Author();
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			author.setName(row.getCell(NAME_ROW).getStringCellValue());
			author.setId((int) row.getCell(ID_ROW).getNumericCellValue());
			list.add(author);
		}
		ea.close();
		return list;
	}
	
	
		
}
