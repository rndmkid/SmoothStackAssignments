
public class HW2_AddAll {

	public static void main(String[] args) {
		double sum = 0;
		for(String e: args){
			sum += Double.parseDouble(e);
		}
		System.out.print(sum);

	}

}
