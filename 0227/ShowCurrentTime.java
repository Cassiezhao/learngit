public class ShowCurrentTime {
	public static void main(String[] args){
		
		long totalMilliSeconds = System.currentTimeMillis();
		System.out.println(totalMilliSeconds);		
		long totalSeconds = totalMilliSeconds / 1000;
		System.out.println(totalSeconds);	
		long currentSecond = totalSeconds % 60	;
		
		long totalMinutes = currentSecond / 60;
		
		long currentMinute = totalMinutes % 60;
		
		long totalHours = totalMinutes / 60 ;

		long currentHour = totalHours % 24;
		
		System.out.println("Current time is " + currentHour + ":" + currentMinute + ":" +
			 currentSecond + "GMT");	
		
		
	}

}
