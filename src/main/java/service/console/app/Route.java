package service.console.app;

import java.time.LocalTime;

public class Route {
	private String busCompany;
	private LocalTime start;
	private LocalTime finish;
	public Route(String busCompany, LocalTime start, LocalTime finish) {
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
	public LocalTime getStart() {
		return start;
	}
	public LocalTime getFinish() {
		return finish;
	}
	public void setBusCompany(String busCompany) {
		this.busCompany = busCompany;
	}
	public void setStart(LocalTime start) {
		this.start = start;
	}
	public void setFinish(LocalTime finish) {
		this.finish = finish;
	}


}
