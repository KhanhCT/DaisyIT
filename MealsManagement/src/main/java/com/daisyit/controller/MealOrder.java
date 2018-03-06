package com.daisyit.controller;

import java.util.Date;

public class MealOrder {
	
	private String staffId;
	private String staffName;
	private String mealTime;
	private String mealType;
	private Date cateringDat;
	private String location;
	private Boolean status;
	

	
	public MealOrder(String staffId, String staffName, String mealTime, String mealType, Date cateringDat,
			String location, Boolean status) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.mealTime = mealTime;
		this.mealType = mealType;
		this.cateringDat = cateringDat;
		this.location = location;
		this.status = status;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getMealTime() {
		return mealTime;
	}
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public Date getCateringDat() {
		return cateringDat;
	}
	public void setCateringDat(Date cateringDat) {
		this.cateringDat = cateringDat;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	

}
