package com.daisyit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.daisyit.db.hibernate.HibernateDeptListDAO;
import com.daisyit.db.hibernate.HibernateStaffDAO;
import com.daisyit.db.hibernate.HibetnateUtil;
import com.daisyit.entity.Staff;

@ManagedBean
@ViewScoped
public class StaffRegistrationController {
	private List<String> departments;
	private String department;
	private Session session;
	private HibernateStaffDAO hStaffDao;
	private Staff staff;
	private HibernateDeptListDAO hDeptListDao;
	private String sex;

	@PostConstruct
	public void init() {
		hDeptListDao = new HibernateDeptListDAO();
		departments = new ArrayList<>();
		session = HibetnateUtil.openSession();
		hStaffDao = new HibernateStaffDAO();
		hStaffDao.setSession(session);
		hDeptListDao = new HibernateDeptListDAO();
		hDeptListDao.setSession(session);
		staff = new Staff();

		departments = hDeptListDao.getAllDeptLists();
		

	}

	public void register() {
		System.err.println("111111111111111" + department);
		FacesContext context = FacesContext.getCurrentInstance();
		if (hStaffDao.isValidStaff(staff.getStaffId())) {
			context.addMessage(null, new FacesMessage("User existed"));
		} else {
			String deptId = hDeptListDao.getDeptListId(department);
			staff.setDeptId(deptId);
			hStaffDao.addStaff(staff);
			if(sex.contains("Male"))
				staff.setSex('0');
			else
				staff.setSex('1');
			context.addMessage(null, new FacesMessage("Register user successfully!"));
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		staff = null;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
