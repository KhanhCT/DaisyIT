package com.daisyit.demo;

import java.util.Date;
import java.util.Timer;

import org.hibernate.Session;

import com.daisyit.controller.Common;
import com.daisyit.controller.ConfigTag;
import com.daisyit.controller.ScheduledDownloadTask;
import com.daisyit.db.hibernate.HibernateSysvarDAO;
import com.daisyit.db.hibernate.HibetnateUtil;
import com.daisyit.entity.Sysvar;
import com.daisyit.utils.Log;

public class FileImportService {

	public static void main(String[] args) {
		
		System.setProperty("file.encoding", "UTF-8");
		HibernateSysvarDAO hSysvarDao = new HibernateSysvarDAO();
		Session session = HibetnateUtil.openSession();
		hSysvarDao.setSession(session);
		Log log = new Log("FileImportService");
		long timePoll = 10000;
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledDownloadTask st = new ScheduledDownloadTask(); // Instantiate
		
		// SheduledTask
		Date alarm = new Date();
		try {
			Sysvar sysvar = hSysvarDao.getParameter(ConfigTag.TIME_SCHEDULER);
			Common.print("FileImportService()","Starting.....");
			timePoll = Long.parseLong(sysvar.getValue());
		} catch (Exception ex) {
			Common.print("FileImportService()","Oops! Please make sure that the PERIOD TIME is correct!");
			System.err.println(ex.getMessage());
			System.exit(0);
		}
		time.schedule(st, alarm, timePoll);
		HibetnateUtil.closeSession(session);
	}

}
