package com.daisyit.db.hibernate;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibetnateUtil {
	private static SessionFactory sessionFactory = null;
	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				//String workingDir = System.getProperty("user.dir");
				File currDir = new File(".");
				String workingDir = currDir.getAbsolutePath();
				workingDir = workingDir.substring(0, workingDir.length()-1);
				File file = null;
				System.out.print("woking dir" + workingDir);
				if (workingDir.contains("\\")) {
					file = new File(workingDir + "\\hibernate.cfg.xml");
				}
				if (workingDir.contains("/")) {
					System.err.println("11111111111111111" + workingDir);
					file = new File("/home/khanhct/eclipse-workspace/DaisyIT/MealsManagement" + "/hibernate.cfg.xml");
				}
				sessionFactory = new Configuration().configure(file).buildSessionFactory();
				System.err.println("Connect to database successfully");
			} catch (Exception ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("sessionFactory error " + ex);
				System.err.println("Cannot connect to database. Connect refuse!!!");
				System.exit(1);
			}
		}

		return sessionFactory;
	}

	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	public static Session openSession() {
		return getSessionFactory().openSession();
	}
	public static void closeSession(Session session)
	{
		session.flush();
		session.close();
		
	}
}