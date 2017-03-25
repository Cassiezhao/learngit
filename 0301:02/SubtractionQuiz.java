import java.util.Scanner;

public class SubtractionQuiz{

	public static void main(String[] args) {

		int number1 = (int)(Math.random()*10);
		int number2 = (int)(Math.random()*10);

		if (number1 < number2){
		
			int temp = number1;
			number1 = number2;
			number2 = temp;
		}
		System.out.println("what is " + number1 + " - " + number2 + " ? ");
		Scanner in = new Scanner(System.in);
		int answer = in.nextInt();
		if(number1 - number2 == answer)
			System.out.println("you are correct!");
		else {
			System.out.println("your answer is wrong.");
			System.out.println(number1+ " - " + number2 + " should be  " 
				+ (number1 - number2));
		}
	} 

}
