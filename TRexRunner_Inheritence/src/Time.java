import java.util.Timer;
import java.util.TimerTask;

public class Time {
	
	int seconds = 0; 
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			seconds++; 
			System.out.println("Seconds = "+ seconds);
		}
	};
	
	public void start() {
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	/*
	public static void main(String[] args) {
		Time time = new Time();
		time.start();
	}
	
*/
}
