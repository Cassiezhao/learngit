import java.util.Scanner;

public class ComputeLoan{
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);

		System.out.println("enter the annnual interest rate, e.g.,7.25%:");
	
		double annualInterestRate = in.nextDouble();
		
		double monthInterestRate = annualInterestRate / 1200;

		System.out.println("enter number of years as integer,e.g.,5:");

		int numberOfYear = in.nextInt();

		System.out.println("enter loan amount,e.g,120000.95:");

		double loanAmount = in.nextDouble();
		
		double monthPayment = loanAmount * monthInterestRate /(1 - 1 / Math.pow(1 + monthInterestRate,
			 numberOfYear *12));
	
		double totalPayment = monthPayment * numberOfYear * 12;

		System.out.println("the monthly payment is $ " + (int)(monthPayment * 100)/100.0);

		System.out.println("the total payment is $ " + (int)(totalPayment * 100)/100.0);
	}

}
