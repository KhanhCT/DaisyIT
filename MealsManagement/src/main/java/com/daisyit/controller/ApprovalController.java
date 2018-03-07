package com.daisyit.controller;

import java.util.ArrayList;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import com.daisyit.db.hibernate.HibernateCateringDAO;
import com.daisyit.db.hibernate.HibernateDeptListDAO;
import com.daisyit.db.hibernate.HibernateMealDAO;
import com.daisyit.db.hibernate.HibernateStaffDAO;
import com.daisyit.db.hibernate.HibetnateUtil;
import com.daisyit.entity.Catering;
import com.daisyit.entity.CateringId;
import com.daisyit.entity.Staff;
import com.daisyit.utils.Util;

@ManagedBean(name = "approvalController")
@ViewScoped
public class ApprovalController {
	private List<MealOrder> orderList;

	private HibernateStaffDAO hStaffDao;
	private HibernateDeptListDAO hDeptListDAO;
	private Session session;

	private String department;
	private List<String> departments;

	private List<String> mealTimes;
	private String mealTime;

	private java.util.Date cateringDate;

	@PostConstruct
	public void init() {
		session = HibetnateUtil.openSession();
		hStaffDao = new HibernateStaffDAO();
		hDeptListDAO = new HibernateDeptListDAO();
		hStaffDao.setSession(session);
		hDeptListDAO.setSession(session);

		orderList = new ArrayList<>();
		departments = hDeptListDAO.getAllDeptLists();

		mealTimes = new ArrayList<>();
		mealTimes.add("Lunch");
		mealTimes.add("Dinner");
		mealTimes.add("Supper,Breakfast");
	}

	public void onFilterListener() {
		if (department != null && mealTime != null && cateringDate != null) {
			String deptId = hDeptListDAO.getDeptListId(department);
			HibernateCateringDAO hCateringDao = new HibernateCateringDAO();
			HibernateMealDAO hMealDao = new HibernateMealDAO();
			List<Catering> cateringList;
			String mealType;
			String staffName;
			String localtion = "Ha Noi";

			hCateringDao.setSession(session);
			hMealDao.setSession(session);

			cateringList = hCateringDao.getAllCaterings(mealTime, Util.getCurrentDate());
			if (cateringList != null) {
				orderList.clear();
				for (Catering c : cateringList) {
					mealType = hMealDao.getMealName(c.getMealId());
					staffName = hStaffDao.getStaffName(c.getId().getStaffId());
					orderList.add(new MealOrder(c.getMealId(), staffName, c.getId().getMealTtme(), mealType,
							c.getCaterTime(), localtion, false));
					// orderList.add(new MealOrder(c.getId().getStaffId(), hStaffDao., deptId,
					// mealType, cateringDat, location, status))
				}

			}
			if (deptId != null) {

				// // staffs = hStaffDao.getAllStaffs(deptId);
				// System.out.println(staffs.size());
				// for (Staff staff : staffs) {
				// MealOrder mealOrder = new MealOrder();
				// mealOrder.setStaffId(staff.getStaffId());
				// mealOrder.setStaffName(staff.getName());
				// mealOrder.setLocation(staff.getCountry());
				// mealOrder.setStatus(false);
				// orderList.add(mealOrder);
				// }
			}
		}
	}

	public void onApproveListener() {

		if (session.isConnected()) {
			Date date = Util.getCurrentDate();
			List<Catering> cateringList = new ArrayList<>();
			HibernateCateringDAO hCateringDao = new HibernateCateringDAO();
			hCateringDao.setSession(session);
			for (MealOrder mealOrder : orderList) {
				String mealId = "AC";
				cateringList.add(new Catering(new CateringId(mealOrder.getStaffId(), mealId, date), mealId, "12", "12",
						mealOrder.getStatus(), "12", true, "12", "12", date, date));
			}
			if (cateringList != null)
				hCateringDao.addMultiCaterings(cateringList);
			// HibetnateUtil.closeSession(session);
		}
	}

	public List<String> getMealTimes() {
		return mealTimes;
	}

	public void setMealTimes(List<String> mealTimes) {
		this.mealTimes = mealTimes;
	}

	public String getMealTime() {
		return mealTime;
	}

	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}

	public java.util.Date getCateringDate() {
		return cateringDate;
	}

	public void setCateringDate(java.util.Date cateringDate) {
		this.cateringDate = cateringDate;
	}

	public List<MealOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<MealOrder> orderList) {
		this.orderList = orderList;
	}

	public HibernateStaffDAO gethStaffDao() {
		return hStaffDao;
	}

	public void sethStaffDao(HibernateStaffDAO hStaffDao) {
		this.hStaffDao = hStaffDao;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

}
