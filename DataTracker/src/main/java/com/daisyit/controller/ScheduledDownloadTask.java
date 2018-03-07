package com.daisyit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.hibernate.Session;
import com.daisyit.db.hibernate.*;
import com.daisyit.entity.Staff;
import com.daisyit.entity.Sysvar;
import com.daisyit.utils.Log;

public class ScheduledDownloadTask extends TimerTask {
	private Ftp ftp;
	private List<String> preFiles = new ArrayList<>();
	private String ROOT_PATH = System.getProperty("user.dir");
	private Log log = new Log(this.getClass().toString());
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
		List<Sysvar> params = hSysvarDao.getAllSysvars();
		String path = null;
		try {
			ftpServer = "117.6.130.185";
			ftpUsername = "daisy";
			ftpPassword = "hanoi";
			ftpPort = 21;
			hostDir = "/khanhct/";
			for (Sysvar var : params) {
				switch (var.getName()) {
				case ConfigTag.FTP_SERVER:
					ftpServer = var.getValue();
					break;
				case ConfigTag.FTP_USERNAME:
					ftpUsername = var.getValue();
					break;
				case ConfigTag.FTP_PASSWORD:
					ftpPassword = var.getValue();
					break;
				case ConfigTag.FTP_PORT:
					ftpPort = Integer.parseInt(var.getValue());
					break;
				case ConfigTag.FTP_INDIR:
					hostDir = var.getValue();
					break;
				default:
					break;
				}
			}
		} catch (NullPointerException ex) {
			if (Common.DEBUG) {
			} else {
				Common.print("ScheduledDownloadTask()", "Oops! System get some problems");
				ex.printStackTrace();
				log.writeLogError(this.getClass().getMethods().toString(), ex.getMessage());
			}
		}
		ftp = new Ftp(ftpServer, ftpUsername, ftpPassword, ftpPort);
		if (ftp.isConnected()) {
			currentFiles = ftp.listFile(hostDir);
			if (currentFiles.toString() == "[]") {
				if (Common.DEBUG)
					Common.print("ScheduledDownloadTask()", "Not file on FTP Server");
				else
					Common.print("ScheduledDownloadTask()", "Not file on FTP Server");
			} else {
				if (currentFiles.size() != preFiles.size()) {
					if (Common.DEBUG) {
						Common.print("ScheduledDownloadTask()", "New files on FTP server created or deleted");
					} else
						Common.print("ScheduledDownloadTask()", "New files on FTP server created or deleted");
					tempFiles.clear();
					for (String str : currentFiles)
						tempFiles.add(str);
					currentFiles.removeAll(preFiles);

					for (String file : currentFiles) {
						if (ROOT_PATH.contains("/"))
							path = ROOT_PATH + "/" + file;
						else
							path = ROOT_PATH + "\\" + file;

						ftp.ftpDownloadFile(hostDir + file, path);
						if (file.contains("NSRF_STAFF") && file.endsWith(".csv")) {
							System.out.println("Read File " + file);
							List<String[]> fileContent = new ArrayList<>();
							List<Staff> staffs = null;
							HibernateStaffDAO hStaffDao = new HibernateStaffDAO();
							hStaffDao.setSession(session);
							fileContent = CSVUtils.readCSV(path);
							for (String[] content : fileContent) {
								Staff staff = new Staff();
								staffs = new ArrayList<>();
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

								staffs.add(staff);
								 hStaffDao.addStaff(staff);
							}
							hStaffDao.addMultiStaffs(staffs);
						}
						try {

							File f = new File(path);
							if (f.delete()) {
								
								Common.print("ScheduledDownloadTask()", f.getName() + " is deleted!");
							} else {
								Common.print("ScheduledDownloadTask()", "Delete operation is failed.");
							}

						} catch (Exception e) {
							log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
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
}