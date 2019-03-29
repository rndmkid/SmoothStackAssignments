
public class HW1 {
	
	//Ascending
	private static void print_mode_1(int rows){
		int i = 1;
		while(rows - i >= 0){
			int count = i;
			while(count > 0){
				System.out.print("*");
				count--;
			}
			System.out.println();
			i++;
		}
	}
	//Descending
	private static void print_mode_2(int rows){
		while(rows > 0){
			int i = rows;
			while(i > 0){
				System.out.print("*");
				i--;
			}
			System.out.println();
			rows--;
		}
	}
	//Ascending Tree
	private static void print_mode_3(int rows){
		int skip = rows - 1;
		int r_count = 1;
		while(rows > 0){
			int total = r_count * 2 - 1;
				while(total > 0){
					if(skip > 0){
						System.out.print(" ");
						skip--;
					}
					else{
						System.out.print("*");
						total--;
					}
				}
			System.out.println();
			r_count++;
			rows--;
			skip = rows - 1;
		}
	}
	
	//Descending Tree
	private static void print_mode_4(int rows){
		int skip = 0;
		int row_count = 1;
		while(rows >= 0){
			int total = rows * 2 - 1;
				while(total > 0){
					if(skip > 0){
						System.out.print(" ");
						skip--;
					}
					else{
						System.out.print("*");
						total--;
					}
				}
			System.out.println();
			skip = row_count;
			row_count++;
			rows--;
		}
	}
	
	private static void print(int mode, int rows){
		switch(mode){
			
			case 1:
				print_mode_1(rows);
				break;
			
			case 2:
				print_mode_2(rows);
				break;

			case 3:
				print_mode_3(rows);
				break;
			
			case 4:
				print_mode_4(rows);
				break;
		}
			
	}
	
	public static void main(String[] args){
		
		int mode = Integer.parseInt(args[0]);
		int rows = Integer.parseInt(args[1]);
		print(mode, rows);
	}
}
