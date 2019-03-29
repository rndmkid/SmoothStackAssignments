import java.util.Scanner;
import java.io.*;

public class HW3_CharCounter {

	public static void main(String[] args) throws FileNotFoundException{
		char search = 'a';
		int count = 0;
		if(args[1].length() == 1){
			search = args[1].charAt(0);
			File f = new File(args[0]);
			if(f.exists()){
				Scanner sc = new Scanner(f);
				while(sc.hasNext()){
					for(char c: sc.next().toCharArray()){
						if(Character.compare(c, search) == 0){
							count++;
						}
					}
				}
				System.out.println("There are " + count + " " + search + "'s");
			}
			else{
				System.out.println("File Does Not Exist!");
			}
		}
		else{
			System.out.println("Not A Character!");
		}
		
		
	}

}
