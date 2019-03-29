package com.st.lms.dao;

import java.util.ArrayList;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.st.lms.daoInterfaces.PublisherDAO;
import com.st.lms.model.Publisher;

public class PublisherDaoImpl implements PublisherDAO {
	
	private ExcelFileAccess ea;
	
	private final int ID_ROW=0;
	private final int NAME_ROW=1;
	private final int ADDRESS_ROW=2;
	private final int PHONE_ROW=3;
	
	public PublisherDaoImpl() {
		this.ea = new ExcelFileAccess("Publisher");
	}
	
	@Override
	public Publisher save(Publisher publisher) {
		Sheet sheet = ea.open();
		int lastRow=sheet.getLastRowNum();
		int id = (int) sheet.getRow(sheet.getLastRowNum()).getCell(ID_ROW).getNumericCellValue() + 1;
		publisher.setId(id);
		Row row = sheet.createRow(++lastRow);
		row.createCell(ID_ROW).setCellValue(id);
		row.createCell(NAME_ROW).setCellValue(publisher.getName());
		row.createCell(ADDRESS_ROW).setCellValue(publisher.getAddress());
		row.createCell(PHONE_ROW).setCellValue(publisher.getPhone());
		ea.save();
		ea.close();
		return publisher;
	}
	
	@Override
	public void delete(Publisher publisher) {
		Sheet sheet = ea.open();
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++){
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			XSSFCell cell = row.getCell(0);
			if(cell.getNumericCellValue() == (double) publisher.getId()){
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
	public void update(Publisher publisher){
		Sheet sheet = ea.open();
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++){
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			XSSFCell cell = row.getCell(ID_ROW);
			if(cell.getNumericCellValue() == (double) publisher.getId()){
				row.getCell(NAME_ROW).setCellValue(publisher.getName());
				row.getCell(ADDRESS_ROW).setCellValue(publisher.getAddress());
				row.getCell(PHONE_ROW).setCellValue(publisher.getPhone());
				break;
			}
		}
		ea.save();
		ea.close();
	}
	
	public Publisher find(Publisher publisher) {
		
//		Sheet sheet = ea.open();
//		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
//			XSSFRow row = (XSSFRow) sheet.getRow(r);
//			XSSFCell cell = row.getCell(NAME_ROW);
//			
//			if(publisher.getName().compareTo(cell.getStringCellValue()) == 0){
//				publisher.setId((int) row.getCell(ID_ROW).getNumericCellValue());
//				publisher.setAddress(row.getCell(ADDRESS_ROW).getStringCellValue());
//				publisher.setPhone(row.getCell(PHONE_ROW).getStringCellValue());
//				ea.close();
//				return publisher;
//			}
//		}
//		ea.close();
		return null;
	}
	
	@Override
	public Publisher find(int id){
		Sheet sheet = ea.open();
		Publisher publisher = new Publisher();
		
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			XSSFCell cell = row.getCell(ID_ROW);
			if((int) cell.getNumericCellValue() == id){
				publisher.setId((int) row.getCell(ID_ROW).getNumericCellValue());
				publisher.setName(row.getCell(NAME_ROW).getStringCellValue());
				publisher.setAddress(row.getCell(ADDRESS_ROW).getStringCellValue());
				publisher.setPhone(row.getCell(PHONE_ROW).getStringCellValue());
				ea.close();
				return publisher;
			}
		}
		ea.close();
		return null;
	}
	
	public Publisher find(String name){
		Sheet sheet = ea.open();
		Publisher publisher = new Publisher();
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			XSSFCell cell = row.getCell(NAME_ROW);
			if(cell.getStringCellValue().equals(name)){
				publisher.setId((int) row.getCell(ID_ROW).getNumericCellValue());
				publisher.setName(row.getCell(NAME_ROW).getStringCellValue());
				publisher.setAddress(row.getCell(ADDRESS_ROW).getStringCellValue());
				publisher.setPhone(row.getCell(PHONE_ROW).getStringCellValue());
				ea.close();
				return publisher;
			}
		}
		ea.close();
		return null;
	}
	
	public List<Publisher> findAll(){
		Sheet sheet = ea.open();
		List<Publisher> list = new ArrayList();
		
		for(int r = sheet.getFirstRowNum()+1; r <= sheet.getLastRowNum(); r++){
			Publisher publisher = new Publisher();
			XSSFRow row = (XSSFRow) sheet.getRow(r);
			publisher.setName(row.getCell(NAME_ROW).getStringCellValue());
			publisher.setId((int) row.getCell(ID_ROW).getNumericCellValue());
			publisher.setAddress(row.getCell(ADDRESS_ROW).getStringCellValue());
			publisher.setPhone(row.getCell(PHONE_ROW).getStringCellValue());
			list.add(publisher);
		}
		ea.close();
		return list;
	}
}
