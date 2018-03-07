package com.daisyit.db.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.abstraction.DAOException;
import com.daisyit.db.abstraction.StaffDAO;
import com.daisyit.entity.Staff;
import com.daisyit.utils.*;

public class HibernateStaffDAO implements StaffDAO  {
	private Session session;
	private Log log = new Log(this.getClass().toString());

	/**
	 * Auto generated method comment
	 *
	 * @param sessionFactory
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	
	@Override
	public Staff getStaff(String staffId) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		String sqlQuery = "FROM Staff WHERE staffId= :staffId";
		Staff staff = null;
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setString("staffId", staffId);
			staff = (Staff) query.uniqueResult();
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return staff;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> getAllStaffs() throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		List<Staff> staffs = new ArrayList<>();
		String sqlQuery = "FROM Staff";
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			staffs = query.list();		
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		} 
		return staffs;
	}

	@Override
	public Staff deleteStaff(Staff staff) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			this.session.delete(staff);
			trans.commit();
		}catch (RuntimeException e) {
			// TODO: handle exception
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return null;
	}

	@Override
	public Staff addStaff(Staff staff) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			this.session.saveOrUpdate(staff);
			trans.commit();
		}catch (RuntimeException e) {
			// TODO: handle exception
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return null;
	}

	@Override
	public List<Staff> addMultiStaffs(List<Staff> staffs) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			for (int i = 0; i < staffs.size(); i++) {
				this.session.saveOrUpdate(staffs.get(i));
				if (i % 20 == 0) {
					this.session.flush();
					this.session.clear();
				}
			}
			trans.commit();
		}catch (RuntimeException e) {
			// TODO: handle exception
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return null;		
	}
}