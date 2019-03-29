import java.io.*;


public class HW3_TextAppender {
	
	private static boolean exists(String path) throws FileNotFoundException{
		File f = new File(path);
		boolean out = f.exists();
		return out;
	}
	
	public static void main(String args[]) throws IOException{
		if(exists(args[0])){
			BufferedWriter out = new BufferedWriter(new FileWriter(args[0], true));
			out.write(args[1]);
			out.close();
		}
		else{
			System.out.println("File Does Not Exist!");
		}
	}

}
