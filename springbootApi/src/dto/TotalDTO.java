package com.corona.tracker.api.status.dto;

public class TotalDTO {
	private String status;
	private String total_confirmed;
	private String total_active;
	private String total_recovered;
	private String total_death;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotal_confirmed() {
		return total_confirmed;
	}
	public void setTotal_confirmed(String total_confirmed) {
		this.total_confirmed = total_confirmed;
	}
	public String getTotal_active() {
		return total_active;
	}
	public void setTotal_active(String total_active) {
		this.total_active = total_active;
	}
	public String getTotal_recovered() {
		return total_recovered;
	}
	public void setTotal_recovered(String total_recovered) {
		this.total_recovered = total_recovered;
	}
	public String getTotal_death() {
		return total_death;
	}
	public void setTotal_death(String total_death) {
		this.total_death = total_death;
	}
}
