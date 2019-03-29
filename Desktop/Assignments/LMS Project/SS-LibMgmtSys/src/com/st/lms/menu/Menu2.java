package com.st.lms.menu;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.st.lms.model.*;
import com.st.lms.service.LmsService;

public class Menu2 {
	
	LmsService service = new LmsService();
	
	private void searchBook(){
		System.out.print("Please enter title: ");
		Scanner sc = new Scanner(System.in);
		Book book = service.findBook(sc.nextLine());
		if(book == null){
			System.out.println("Book Not Found!\n Try Again.");
			searchBook();
		}
		else{
			String format = "Title: %1$s \n"
					+ "Author: %2$s \n"
					+ "Publisher: %3$s \n";
			System.out.printf(format, book.getTitle(),
						service.findAuthor(book.getAuthor_id()).getName(),
						service.findPublisher(book.getPublisher_id()).getName());
			System.out.println("Enter 1 to quit.");
			sc.next();
			start();
		}
	}
	private void addBook(){
		System.out.print("Please enter title: ");
		Scanner sc = new Scanner(System.in);
		Book book = new Book();
		book.setTitle(sc.nextLine());
		
		System.out.print("\nPlease enter author: ");
		Author author;
		String name = sc.nextLine();
		if((author = service.findAuthor(name)) != null){
			book.setAuthor_id(author.getId());
		}else{
			author = new Author();
			author.setName(name);
			author = service.createAuthor(author);
			book.setAuthor_id(author.getId());
		}
		 
		System.out.print("\nPlease enter publisher: ");
		Publisher publisher = new Publisher();
		name = sc.nextLine();
		publisher.setName(name);
		if((publisher = service.findPublisher(publisher.getName())) != null){
			book.setPublisher_id(publisher.getId());
		}else{
			publisher = new Publisher();
			publisher.setName(name);
			book.setPublisher_id(service.createPublisher(publisher).getId());
		}
		
		service.createBook(book, author, publisher);
		
		System.out.println("\nSuccess!");
		System.out.println("Enter 1 to quit.");
		sc.next();
		start();
		
	}
	private void updateBook(){
		System.out.print("Please enter title: ");
		Scanner sc = new Scanner(System.in);
		Book book = service.findBook(sc.nextLine());
		if(book == null){
			System.out.println("Book Not Found!\n Try Again.");
			updateBook();
		} else{
			System.out.print("Enter new title: ");
			book.setTitle(sc.nextLine());
			service.updateBook(book);
			System.out.println("Book Updated!");
			System.out.println("Enter 1 to exit.");
			sc.next();
			start();
		}
	}
	private void deleteBook(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter title: ");
		Book book = service.findBook(sc.nextLine());
		if(book == null){
			System.out.println("Book Not Found!\n Try Again.");
			deleteBook();
		}else{
			service.destroyBook(book);
		}
		System.out.println("Book deleted.");
		System.out.println("Enter 1 to exit.");
		sc.next();
		start();
	}
	
	private void searchAuthor(){
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		Author author = service.findAuthor(sc.nextLine());
		if(author == null){
			System.out.println("Author Not Found!\n Try Again.");
			searchAuthor();
		}
		else{
			String format = "Name: %1$s \n";
			System.out.printf(format, author.getName());
			System.out.println("Enter 1 to quit.");
			sc.next();
			start();
		}
	}
	private void addAuthor(){
		System.out.println("Sorry, adding an author is not an option.");
		start();
	}
	private void updateAuthor(){
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		Author author = service.findAuthor(sc.nextLine());
		if(author == null){
			System.out.println("Author Not Found!\n Try Again.");
			searchAuthor();
		}
		else{
			System.out.print("Enter new name: ");
			author.setName(sc.nextLine());
			service.updateAuthor(author);
			System.out.println("Author Updated!");
			System.out.println("Enter 1 to exit.");
			sc.next();
			start();
		}
	}
	private void deleteAuthor(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name: ");
		Author author = service.findAuthor(sc.nextLine());
		if(author == null){
			System.out.println("Author Not Found!\n Try Again.");
			deleteBook();
		}else{
			service.destroyAuthor(author);
		}
		System.out.println("Author Deleted!");
		System.out.println("Enter 1 to exit.");
		sc.next();
		start();
	}
	
	private void searchPublisher(){
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		Publisher publisher = new Publisher();
		publisher.setName(sc.nextLine());
		publisher = service.findPublisher(publisher.getName());
		if(publisher == null){
			System.out.println("Publisher Not Found!\n Try Again.");
			searchPublisher();
		}
		else{
			String format = "Name: %1$s \n";
			System.out.printf(format, publisher.getName());
			System.out.println("Enter 1 to quit.");
			sc.next();
			start();
		}
	}
	private void addPublisher(){
		System.out.println("Sorry, adding a publisher is not an option.");
		start();
	}
	private void updatePublisher(){
		System.out.println("Would you like to update the...");
		System.out.println("1) Name?");
		System.out.println("2) Address?");
		System.out.println("3) Phone?");
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		while(!Pattern.matches("[1-3]", in)){
			System.out.println("Improper Input. Please enter 1-3.");
			in = sc.nextLine();
		}
		
		switch(in){
		case "1":
			updatePubName();
		case "2":
			updatePubAddress();
		case "3":
			updatePubPhone();
		}
		
	}
	
	//TODO fp
	private void updatePubName(){
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		Publisher publisher = new Publisher();
		publisher.setName(sc.nextLine());
		publisher = service.findPublisher(publisher.getName());
		if(publisher == null){
			System.out.println("Publisher Not Found!\n Try Again.");
			searchPublisher();
		}
		else{
			System.out.print("Enter new name: ");
			publisher.setName(sc.nextLine());
			service.updatePublisher(publisher);
			System.out.println("Publisher Updated!");
			System.out.println("Enter 1 to exit.");
			sc.next();
			start();
		}
	}
	
	//TODO fp
	private void updatePubAddress(){
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		Publisher publisher = new Publisher();
		publisher.setName(sc.nextLine());
		publisher = service.findPublisher(publisher.getName());
		if(publisher == null){
			System.out.println("Publisher Not Found!\n Try Again.");
			searchPublisher();
		}
		else{
			System.out.print("Enter new address: ");
			publisher.setAddress(sc.nextLine());
			service.updatePublisher(publisher);
			System.out.println("Publisher Updated!");
			System.out.println("Enter 1 to exit.");
			sc.next();
			start();
		}
	}
	
	//TODO fp
	private void updatePubPhone(){
		System.out.print("Please enter name: ");
		Scanner sc = new Scanner(System.in);
		Publisher publisher = new Publisher();
		publisher.setName(sc.nextLine());
		publisher = service.findPublisher(publisher.getName());
		if(publisher == null){
			System.out.println("Publisher Not Found!\n Try Again.");
			searchPublisher();
		}
		else{
			System.out.print("Enter new phone: ");
			publisher.setPhone(sc.nextLine());
			service.updatePublisher(publisher);
			System.out.println("Publisher Updated!");
			System.out.println("Enter 1 to exit.");
			sc.next();
			start();
		}
	}
	
	//TODO fp
	private void deletePublisher(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name: ");
		Publisher publisher = new Publisher();
		publisher.setName(sc.nextLine());
		publisher = service.findPublisher(publisher.getName());
		if(publisher == null){
			System.out.println("Publisher Not Found!\n Try Again.");
			deleteBook();
		}else{
			service.destroyPublisher(publisher);
		}
		System.out.println("Publisher Deleted!");
		System.out.println("Enter 1 to exit.");
		sc.next();
		start();
	}
	
	private void thirdMenu(String action, String item, String proper){
		if(item.equals("book")){
			if(action=="Search")
				searchBook();
			else if(action=="Add")
				addBook();
			else if(action=="Update")
				updateBook();
			else if(action=="Delete")
				deleteBook();
		}else if(item.equals("author")){
			if(action=="Search")
				searchAuthor();
			else if(action=="Add")
				addAuthor();
			else if(action=="Update")
				updateAuthor();
			else if(action=="Delete")
				deleteAuthor();
		}else if(item.equals("publisher")){
			if(action=="Search")
				searchPublisher();
			else if(action=="Add")
				addPublisher();	
			else if(action=="Update")
				updatePublisher();
			else if(action=="Delete")
				deletePublisher();
		}
	}
	
	private void secondMenu(String action){
		System.out.println("What would you like to " + action + " for?");
		Scanner sc = new Scanner(System.in);
		System.out.println("1) Books");
		System.out.println("2) Authors");
		System.out.println("3) Publishers");
		System.out.println("4) Quit"); 
		
		switch(sc.nextLine()){
		case "1":
			thirdMenu(action, "book", "title");
			break;
		case "2":
			thirdMenu(action, "author", "name");
			break;
		case "3":
			thirdMenu(action, "publisher", "name");
			break;
		case "4":
			System.out.println("Have a nice day!");
			break;
			
		default:
			secondMenu(action);
		}	
	}
	
	
	public void start(){
		System.out.println("Welcome! What would you like to do?");
		System.out.println("Select an option (i.e. 1):");
		System.out.println("1) Search");
		System.out.println("2) Add");
		System.out.println("3) Update"); 
		System.out.println("4) Delete"); 
		System.out.println("5) Quit");
		
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		
		while(!Pattern.matches("[1-5]", in)){
			System.out.println("Improper Input. Please enter 1-5.");
			in = sc.nextLine();
		}
		
		switch(in){
		case "1":
			secondMenu("Search");
			break;
		case "2":
			secondMenu("Add");
			break;
		case "3":
			secondMenu("Update");
			break;
		case "4":
			secondMenu("Delete");
			break;
			
		default:
			System.out.println("Have a nice day!");
			break;
		}
		
	}
	
}
