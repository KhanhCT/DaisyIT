package com.daisyit.demo;

import java.util.Date;
import java.util.Timer;

import com.daisyit.controller.Common;
import com.daisyit.controller.ScheduledDownloadTask;

public class FileImportService {

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");

		//SysvarDAO sysDAO = new SysvarDAO();
		//Log log = new Log("FileImportService");
	
		long timePoll = 10000;
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledDownloadTask st = new ScheduledDownloadTask(); // Instantiate
	
		// SheduledTask
		Date alarm = new Date();
		try {
			Common.print("FileImportService()","Starting.....");
			//timePoll = Long.parseLong(sysDAO.getValue(ConfigTag.TIME_SCHEDULER));

		} catch (Exception ex) {
			 System.exit(0);
		}
		time.schedule(st, alarm, timePoll);
	}

}
