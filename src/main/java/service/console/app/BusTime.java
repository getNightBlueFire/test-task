package service.console.app;

public class BusTime {
	private int hour;
	private int minutes;
	public BusTime() {

	}
	public BusTime(int hour, int minutes) {
		super();
		this.hour = hour;
		this.minutes = minutes;
	}
	public int getHour() {
		return hour;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getBusTime() {
		return hour+":"+minutes;
	}

}
class Result{
	public static void main(String[] args) {
		double x = (100 *Math.random());
		if(x < 50){
			System.out.println("Wait for your bus");
		}
		else if(x >= 50){
			System.out.println("Your bus has arrived");
		}
		else System.out.println("Unknown error");
	}
}