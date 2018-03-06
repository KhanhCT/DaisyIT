package com.daisyit.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.daisyit.controller.*;
import com.daisyit.db.hibernate.HibernateSysvarDAO;
import com.daisyit.db.hibernate.HibetnateUtil;

public class TestingMain {

	public static void main(String[] args) {
		HibernateSysvarDAO hSysvarDao = new HibernateSysvarDAO();
		Session session = HibetnateUtil.openSession();
		hSysvarDao.setSession(session);
		System.out.println("444444444444444444" + hSysvarDao.getAllSysvars().size());
			
				

	}

}
