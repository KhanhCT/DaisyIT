package com.daisyit.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.hibernate.HibernateStaffDAO;
import com.daisyit.db.hibernate.HibernateUserDAO;
import com.daisyit.db.hibernate.HibetnateUtil;
import com.daisyit.entity.Staff;
import com.daisyit.entity.User;
import com.daisyit.utils.*;

public class TestingMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Log log = new Log("Main");
		// log.writeLogInfo("Testing...............", "Thom");
		// System.out.println("dsssssssssss");
		// Session sess = null;
		// List<Staff> staffs = new ArrayList<Staff>();
		// List<User> users = new ArrayList<User>();
		// sess = HibetnateUtil.openSession();
		// HibernateStaffDAO dao = new HibernateStaffDAO();
		// HibernateUserDAO uDao = new HibernateUserDAO();
		// dao.setSession(sess);
		// uDao.setSession(sess);
		//
		// //staffs = dao.getAllStaffs();
		//
		// //Staff staff = dao.getStaff("1212");
		// //users = uDao.getAllUsers();
		// //uDao.deleteUser(users.get(0));
		// //users.get(0).setId(1);
		// //uDao.addMultiUsers(users);
		// System.out.println(uDao.isValidUser("1000032ds"));
		//
		// //dao.deleteStaff(staff);
		// //dao.addMultiStaffs(staffs);
		//
		// HibetnateUtil.closeSession(sess);
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		System.out.println(path.substring(0, path.length()-1));
	}
}
