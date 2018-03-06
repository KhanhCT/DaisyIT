package com.daisyit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.daisyit.utils.Util;

@ManagedBean(name = "orderController")
@ViewScoped
public class OrderController {

	private Date cateringDate;
	private String staffName;
	private String department;
	private List<MealOrder> orderList;

	private List<String> mealTimes;

	private List<String> mealTypes;

	private List<String> localtions;

	@PostConstruct
	public void init() {

		mealTypes = new ArrayList<>();
		mealTimes = new ArrayList<>();
		localtions = new ArrayList<>();

		mealTimes.add("Lunch");
		mealTimes.add("Dinner");
		mealTimes.add("Supper,Breakfast");
		mealTypes.add("VietNam");
		mealTypes.add("Japan/Nihon");
		mealTypes.add("Halah");
		localtions.add("Ha Noi");

		orderList = new ArrayList<>();
		orderList.add(
				new MealOrder("1111", "Chu Trong Khanh", "Lunch", "VietNam", Util.getCurrentDate(), "Ha Noi", false));
	}

	public List<String> getLocaltions() {
		return localtions;
	}

	public void setLocaltions(List<String> localtions) {
		this.localtions = localtions;
	}

	public List<String> getMealTimes() {
		return mealTimes;
	}

	public void setMealTimes(List<String> mealTimes) {
		this.mealTimes = mealTimes;
	}

	public List<String> getMealTypes() {
		return mealTypes;
	}

	public void setMealTypes(List<String> mealTypes) {
		this.mealTypes = mealTypes;
	}

	public Date getCateringDate() {
		return cateringDate;
	}

	public void setCateringDate(Date cateringDate) {
		this.cateringDate = cateringDate;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<MealOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MealOrder> orderList) {
		this.orderList = orderList;
	}

}
