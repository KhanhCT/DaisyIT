package com.daisyit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.hibernate.Session;
import com.daisyit.db.hibernate.*;
import com.daisyit.entity.Staff;

public class ScheduledDownloadTask extends TimerTask {
	private Ftp ftp;
	private List<String> preFiles = new ArrayList<>();
	private String ROOT_PATH = System.getProperty("user.dir");

	@Override
	public void run() {
		String ftpServer = null;
		int ftpPort = ConfigTag.DEFAULT_PORT;
		String ftpUsername = null;
		String ftpPassword = null;
		String hostDir = ConfigTag.DEFAULT_HOSTDIR;
		List<String> currentFiles = new ArrayList<>();
		List<String> tempFiles = new ArrayList<>();
		HibernateSysvarDAO hSysvarDao = new HibernateSysvarDAO();
		Session session = HibetnateUtil.openSession();
		hSysvarDao.setSession(session);
		//System.out.println("444444444444444444" + hSysvarDao.getParameter(ConfigTag.FTP_SERVER));
		String path = null;
		try {
			ftpServer = "117.6.130.185";
			ftpUsername = "daisy";
			ftpPassword = "hanoi";
			ftpPort = 21;
			hostDir = "/khanhct/";
		} catch (NullPointerException ex) {
			if (Common.DEBUG) {
			} else {
				Common.print("ScheduledDownloadTask()", "Oops! System get some problems");
				ex.printStackTrace();
			}
			// System.exit(0);
		}
		ftp = new Ftp(ftpServer, ftpUsername, ftpPassword, ftpPort);

		currentFiles = ftp.listFile(hostDir);
		if (currentFiles.toString() == "[]") {
			if (Common.DEBUG)
				Common.print("ScheduledDownloadTask()", "Not file on FTP Server");
			else
				Common.print("ScheduledDownloadTask()", "Not file on FTP Server");
		} else {
			if (currentFiles.size() != preFiles.size()) {
				if (Common.DEBUG) {
					Common.print("ScheduledDownloadTask()", "New files on FTP server created");
				} else
					Common.print("ScheduledDownloadTask()", "New files on FTP server created");
				tempFiles.clear();
				for (String str : currentFiles)
					tempFiles.add(str);
				currentFiles.removeAll(preFiles);
				if (Common.DEBUG) {
					//Common.print("ScheduledDownloadTask()", "Size preFiles " + preFiles.size());
					//Common.print("ScheduledDownloadTask()", "Size currentFiles " + currentFiles.size());
				}

				for (String file : currentFiles) {
					if (ROOT_PATH.contains("/"))
						path = ROOT_PATH + "/tmp/" + file;
					else
						path = ROOT_PATH + "\\tmp\\" + file;

					ftp.ftpDownloadFile(hostDir + file, path);
					// }
					//
					// for (String file : currentFiles) {

					if (file.contains("NSRF_STAFF") && file.endsWith(".csv")) {
						System.out.println("Read File " + file);
						List<String[]> fileContent = new ArrayList<>();
						// List<Staff> staffs = new ArrayList<>();
						HibernateStaffDAO hStaffDao = new HibernateStaffDAO();
						hStaffDao.setSession(session);
						fileContent = CSVUtils.readCSV(path);
						for (String[] content : fileContent) {
							Staff staff = new Staff();
							// staffs = new ArrayList<>();
							staff.setStaffId(content[0]);
							staff.setCardId(content[0]);
							if (content[2].contains("Mr."))
								staff.setSex('1');
							else
								staff.setSex('0');
							staff.setName(content[3]);
							staff.setTitle(content[5]);
							staff.setDeptId("F");
							staff.setCountry(content[8]);
							staff.setEmail(content[9]);
							staff.setPhone(content[10]);
							staff.setAddress(content[12]);
							staff.setStatus(true);
							staff.setStaffCode(content[0]);
							staff.setAddress2(content[12]);
							// staff.setFax(tmp);
							// staff.setSkype(tmp);
							// staff.setFacebook(tmp);
							// staff.setBankAcc(tmp);
							// staff.setBirthday(date);

							// staffs.add(staff);
							hStaffDao.addStaff(staff);
						}
						// hStaffDao.addStaff(staff);
						// hStaffDao.addMultiStaffs(staffs);
					}
					try {

						File f = new File(path);
						if (f.delete()) {
							Common.print("ScheduledDownloadTask()",f.getName() + " is deleted!");
						} else {
							Common.print("ScheduledDownloadTask()","Delete operation is failed.");
						}

					} catch (Exception e) {

						e.printStackTrace();

					}
				}
				preFiles.clear();
				for (String str : tempFiles)
					preFiles.add(str);
			}
		}
		HibetnateUtil.closeSession(session);
		ftp.disconnect();
	}
}