import java.util.Scanner;

public class ComputeAndInterpretBMI {

	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("enter weight in pounds:");
		double weight = in.nextDouble();

		System.out.println("enter height in inches:");
		double height = in.nextDouble();
		
		final double KILOGRAMS_PER_POUND = 0.45359237;
		final double METERS_PER_INCH = 0.0254;

		double weightInKilograms = weight * KILOGRAMS_PER_POUND;
		double heightInMeters = height * METERS_PER_INCH;

		double bmi = weightInKilograms / (heightInMeters * heightInMeters);
		System.out.println("BMI is " + bmi + " .");
		if(bmi < 18.5)
			System.out.println("Underweight.");
		else if (bmi < 25.0)
			System.out.println("Normal.");
		else if (bmi < 30.0)
			System.out.println("Overweight.");
		else 
			System.out.println("Obese.");
	
	}


}
