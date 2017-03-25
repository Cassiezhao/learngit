import java.util.Scanner;
public class ComputeArea{
	public static void main(String[] args){
	
		double radius;
		double area;
		final double PI = 3.1415926;
		Scanner input = new Scanner(System.in);
		System.out.println("enter a number for radius:");
		radius = input.nextDouble();
		
		//radius = 20 ;
		area = radius * radius * PI;
		System.out.println("The area for the circle of radius" + radius + " is " + area);
	}
}
