import java.util.Scanner;

public class DisplayTime {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("enter an integer for seconds:");
		
		int seconds = in.nextInt();
		int minutes = seconds / 60;
		int remainingSeconds = seconds % 60 ;
		System.out.println(seconds + " seconds is " + minutes + " minutes and " + remainingSeconds +
		  " seconds");
		System.out.println(5%1);
		System.out.println(Math.pow(2,3.5));
		System.out.println("25/4 is " + 25 / 4);
	}

}
