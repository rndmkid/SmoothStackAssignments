import java.io.*;

public class HW3_ListDirectory {
	
	public static void main(String args[]){
		File folder = new File(args[0]);
		if(!folder.isDirectory()){
			System.out.println("Sorry, but this file is not a directory.");
		}
		else{
			File[] list = folder.listFiles();
			for(File e: list){
				System.out.println(e.getName());
			}
			
		}
	}

}
