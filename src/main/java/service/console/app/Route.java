package service.console.app;

import java.time.LocalTime;

public class Route {
	private String busCompany;
	private BusTime start;
	private BusTime finish;
	public Route(String busCompany, BusTime start, BusTime finish) {
		super();
		this.busCompany = busCompany;
		this.start = start;
		this.finish = finish;
	}
	public Route() {
		super();
	}
	public String getBusCompany() {
		return busCompany;
	}
	public BusTime getStart() {
		return start;
	}
	public BusTime getFinish() {
		return finish;
	}
	public void setBusCompany(String busCompany) {
		this.busCompany = busCompany;
	}
	public void setStart(BusTime start) {
		this.start = start;
	}
	public void setFinish(BusTime finish) {
		this.finish = finish;
	}
	public int getRouteTime() {
		LocalTime b = LocalTime.of(finish.getHour(), finish.getMinutes());
		LocalTime time = b.minusHours(start.getHour()).minusMinutes(start.getMinutes());
		int timeImMinutes = time.getHour()*60+time.getMinute();
		if(timeImMinutes > 60) {
			return Integer.MAX_VALUE;
		}
		return a;
	}
	@Override
	public String toString() {
		//return "Route [start=" + start.getBusTime() + ", finish=" + finish.getBusTime() + " time" + getRouteTime()+ "]";
		return "Route [busCompany=" + busCompany + ", start=" + start.getBusTime() + ", finish=" + finish.getBusTime() + "]";
	}
	public String toOutput() {
		String min_s = "0";
		if(start.getMinutes()<10) {
			min_s = min_s + start.getMinutes();
		} else {
			min_s = Integer.toString(start.getMinutes());
		}
		String min_f = "0";
		if(finish.getMinutes()<10) {
			min_f = min_f + finish.getMinutes();
		} else {
			min_f = Integer.toString(finish.getMinutes());
		}
		String value = busCompany + " " + start.getHour() + ":" + min_s + " " + finish.getHour() + ":" + min_f + "\r\n";
		return value;
	}
	// Great day!

}
