package com.st.lms.dao;

import java.util.ArrayList;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.st.lms.daoInterfaces.*;
import com.st.lms.model.Book;


public class BookDaoImpl implements BookDAO {
	private ExcelFileAccess ea;
	
	private final int ID_ROW=0;
	private final int TITLE_ROW=1;
	private final int AUTHOR_ROW=2;
	private final int PUBLISHER_ROW=3;
	
	public BookDaoImpl(){
		ea = new ExcelFileAccess("Book");
	}
	
	@Override
	public void save(Book book) {
		Sheet sheet = ea.open();
		int lastRow=sheet.getLastRowNum();
		int id = 0;
		try{
			id = (int) sheet.getRow(sheet.getLastRowNum()).getCell(ID_ROW).getNumericCellValue() + 1;
		}catch(IllegalStateException e){
			if(e.getMessage().compareToIgnoreCase("Cannot get a NUMERIC value from a STRING cell") == 0)
				id = 1;
			else{
				e.printStackTrace();
			}
			
		}
		Row row = sheet.createRow(++lastRow);
		row.createCell(ID_ROW).setCellValue(id);
		row.createCell(TITLE_ROW).setCellValue(book.getTitle());
		row.createCell(AUTHOR_ROW).setCellValue(book.getAuthor_id());
		row.createCell(PUBLISHER_ROW).setCellValue(book.getPublisher_id());
		
		ea.save();
		ea.close();
	}
	
	@Override
	public void delete(Book book) {
		Sheet sheet = ea.open();
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++){
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			XSSFCell cell = row.getCell(0);
			if(cell.getNumericCellValue() == (double) book.getId()){
				if(i+1 > sheet.getLastRowNum())
					sheet.removeRow(row);
				else
					sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
				break;
			}
		}
		ea.save();
		ea.close();
	}
	
	@Override
	public void update(Book book){
		Sheet sheet = ea.open();
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++){
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			XSSFCell cell = row.getCell(ID_ROW);
			if(cell.getNumericCellValue() == (double) book.getId()){
				row.getCell(TITLE_ROW).setCellValue(book.getTitle());
				row.getCell(AUTHOR_ROW).setCellValue(book.getAuthor_id());
				row.getCell(PUBLISHER_ROW).setCellValue(book.getPublisher_id());
				break;
			}
		}
		ea.save();
		ea.close();
	}
	
	
	public Book find(Book book) {
//		
//		Sheet sheet = ea.open();
//		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
//			XSSFRow row = (XSSFRow) sheet.getRow(r);
//			XSSFCell cell = row.getCell(TITLE_ROW);
//			
//			if(book.getTitle().compareTo(cell.getStringCellValue()) == 0){
//				book.setId((int) row.getCell(ID_ROW).getNumericCellValue());
//				book.setPublisher_id((int) row.getCell(PUBLISHER_ROW).getNumericCellValue());
//				book.setAuthor_id((int) row.getCell(AUTHOR_ROW).getNumericCellValue());
//				ea.close();
//				return book;
//			}
//		}
//		ea.close();
		return null;
	}

	@Override
	public Book find(int id){
		Sheet sheet = ea.open();
		Book book = new Book();
		
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			XSSFCell cell = row.getCell(ID_ROW);
			if((int) cell.getNumericCellValue() == id){
				book.setId((int) row.getCell(ID_ROW).getNumericCellValue());
				book.setTitle(row.getCell(TITLE_ROW).getStringCellValue());
				book.setPublisher_id((int) row.getCell(PUBLISHER_ROW).getNumericCellValue());
				book.setAuthor_id((int) row.getCell(AUTHOR_ROW).getNumericCellValue());
				ea.close();
				return book;
			}
		}
		ea.close();
		return null;
	}
	
	public Book find(String name){
		Sheet sheet = ea.open();
		Book book = new Book();
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			XSSFCell cell = row.getCell(TITLE_ROW);
			if(cell.getStringCellValue().equals(name)){
				book.setId((int) row.getCell(ID_ROW).getNumericCellValue());
				book.setTitle(row.getCell(TITLE_ROW).getStringCellValue());
				book.setPublisher_id((int) row.getCell(PUBLISHER_ROW).getNumericCellValue());
				book.setAuthor_id((int) row.getCell(AUTHOR_ROW).getNumericCellValue());
				ea.close();
				return book;
			}
		}
		ea.close();
		return null;
	}
	
	public List<Book> findAll(){
		Sheet sheet = ea.open();
		List<Book> list = new ArrayList();
		
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			Book book = new Book();
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			book.setTitle(row.getCell(TITLE_ROW).getStringCellValue());
			book.setId((int) row.getCell(ID_ROW).getNumericCellValue());
			book.setAuthor_id((int) row.getCell(AUTHOR_ROW).getNumericCellValue());
			book.setPublisher_id((int) row.getCell(PUBLISHER_ROW).getNumericCellValue());
			list.add(book);
		}
		ea.close();
		return list;
	}

}
