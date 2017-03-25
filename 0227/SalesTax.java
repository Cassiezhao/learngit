import java.util.Scanner;

public class SalesTax {
	public static void main(String[] args){
	
		Scanner input = new Scanner(System.in);
		
		System.out.println("enter purchase amount:");
		
		double purchaseAmount = input.nextDouble();
		
		double tax = purchaseAmount * 0.06;
		
		System.out.println("Sale tax is $ "+ (int)(tax*100)/100.0);

		float f = 12.5f;
		int i = (int)f;
		System.out.println("f"+f +" i :" + i);
		double amount = 5;
		System.out.println(amount / 2);

	}
}
