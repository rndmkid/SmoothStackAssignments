import java.util.Arrays;

public class HW2_MaxArray {
	
	public static void max(int[][] arr){
		int max = arr[0][0];
		int[] pos = {0, 0};
		int i = 0;
		int j = 0;
		while(i < arr.length){
			while(j < arr[0].length){
				if (max < arr[i][j]){
					max = arr[i][j];
					pos[0] = i;
					pos[1] = j;
				}
				j++;
			}
			j = 0;
			i++;
		}
		System.out.printf("Max Value in Array: %s\n", max);
		System.out.printf("Position of Max: %s,%s\n", pos[0], pos[1]);
	}
	
	public static void max(double[][] arr){
		double max = arr[0][0];
		int[] pos = {0, 0};
		int i = 0;
		int j = 0;
		while(i < arr.length){
			while(j < arr[0].length){
				if (max < arr[i][j]){
					max = arr[i][j];
					pos[0] = i;
					pos[1] = j;
				}
				j++;
			}
			j = 0;
			i++;
		}
		System.out.printf("Max Value in Array: %s\n", max);
		System.out.printf("Position of Max: %s,%s\n", pos[0], pos[1]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = {{1, 2, 3},{4, 5, 6}, {3, 2, 0}};
		max(arr);
		
	}

}
