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
