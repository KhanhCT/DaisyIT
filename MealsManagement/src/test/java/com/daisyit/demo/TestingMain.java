package com.daisyit.demo;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.hibernate.HibernateCateringDAO;
import com.daisyit.db.hibernate.HibernateDeptListDAO;
import com.daisyit.db.hibernate.HibernateStaffDAO;
import com.daisyit.db.hibernate.HibernateUserDAO;
import com.daisyit.db.hibernate.HibetnateUtil;
import com.daisyit.entity.Catering;
import com.daisyit.entity.CateringId;
import com.daisyit.entity.Staff;
import com.daisyit.entity.User;
import com.daisyit.utils.*;

public class TestingMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Log log = new Log("Main");
		// log.writeLogInfo("Testing...............", "Thom");
		System.out.println("dsssssssssss");
		Session sess = null;
		// List<Staff> staffs = new ArrayList<Staff>();
		// List<User> users = new ArrayList<User>();
		sess = HibetnateUtil.openSession();
		HibernateStaffDAO dao = new HibernateStaffDAO();
		HibernateCateringDAO cDao = new HibernateCateringDAO();
		HibernateDeptListDAO dpDAO = new HibernateDeptListDAO();
		dpDAO.setSession(sess);
		cDao.setSession(sess);
		// HibernateUserDAO uDao = new HibernateUserDAO();
		dao.setSession(sess);
		// uDao.setSession(sess);
		//
		// //staffs = dao.getAllStaffs();
		//
		// //Staff staff = dao.getStaff("1212");
		// //users = uDao.getAllUsers();
		// //uDao.deleteUser(users.get(0));
		// //users.get(0).setId(1);
		// //uDao.addMultiUsers(users);
		// Date date = Util.getCurrentDate();
		// System.out.println(date);
		// cDao.addCatering( new Catering(new CateringId("111111", "LC", date), "HA",
		// "12", "12", false, "12", true, "12", "12", date, date));
		// System.out.println("11111111111111111" + dao.getAllStaffs("D001").size());
		//
		// //dao.deleteStaff(staff);
		// //dao.addMultiStaffs(staffs);
		// //
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		// Date date = new Date();
		// System.out.println(dateFormat.format(date));

		java.util.Date date = new java.util.Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);

		System.out.println(cDao.getAllCaterings("AC", Util.getCurrentDate()).size());
		HibetnateUtil.closeSession(sess);

		// File currDir = new File(".");
		// String path = currDir.getAbsolutePath();
		// System.out.println(path.substring(0, path.length()-1));
	}
}
