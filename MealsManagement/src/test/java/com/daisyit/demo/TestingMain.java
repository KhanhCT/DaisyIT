package com.daisyit.demo;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.hibernate.HibernateStaffDAO;
import com.daisyit.db.hibernate.HibetnateUtil;
import com.daisyit.entity.Staff;
import com.daisyit.utils.*;

public class TestingMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log log = new Log("Main");
		log.writeLogInfo("Testing...............", "Thom");
		System.out.println("dsssssssssss");
		Session sess = null;
		List<Staff> staffs = new ArrayList<Staff>();
		sess = HibetnateUtil.openSession();
		HibernateStaffDAO dao =  new HibernateStaffDAO();
		dao.setSession(sess);
		staffs = dao.getAllStaffs();
		Staff staff  = dao.getStaff("1212");
		System.out.println(staff.getName());
		
		dao.deleteStaff(staff);
		dao.addMultiStaffs(staffs);
		
		HibetnateUtil.closeSession(sess);
	}
}
