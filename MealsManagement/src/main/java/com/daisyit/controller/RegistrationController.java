package com.daisyit.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.daisyit.entity.*;
import com.daisyit.db.hibernate.*;

@ManagedBean(name = "registrationController")
@SuppressWarnings("unused")
public class RegistrationController {
	
	private HashMap<String, String> departments ;
	private String department;
	private String retypePassword;
	private User user;
	private HibernateUserDAO hUserDao;
	private Session session;
	
	@PostConstruct
	public void init() {
		departments = new HashMap<>();
		user = new User();
		session = HibetnateUtil.openSession();
		hUserDao = new HibernateUserDAO();
		hUserDao.setSession(session);
	}
	
	
	
	public HashMap<String, String> getDepartments() {
		return departments;
	}



	public void setDepartments(HashMap<String, String> departments) {
		this.departments = departments;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getRetypePassword() {
		return retypePassword;
	}



	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void register()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(hUserDao.isValidUser(user.getStaffId())) {
			context.addMessage(null, new FacesMessage("User existed"));
			System.out.println("22222222222222222222222" + user.getFullname());
		}else
		{
			user.setStatus(true);
			hUserDao.addUser(user);
			context.addMessage(null, new FacesMessage("Register user successfully!"));
			System.out.println("11111111111111111111111" + user.getFullname());
		}
		context.getExternalContext().getFlash().setKeepMessages(true);

	}
}
