package com.daisyit.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.abstraction.DAOException;
import com.daisyit.db.abstraction.SysvarDAO;
import com.daisyit.entity.Sysvar;

public class HibernateSysvarDAO implements SysvarDAO {
	/**
	 * Auto generated method comment
	 *
	 * @param sessionFactory
	 */
	private Session session;
	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Sysvar getParameter(String name) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		String sqlQuery = "FROM Sysvar WHERE name= :name";
		Sysvar sysvar = null;
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setString("name", name);
			sysvar = (Sysvar) query.uniqueResult();
			trans.commit();
		} catch (RuntimeException e) {
			//log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return sysvar;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sysvar> getAllSysvars() throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		List<Sysvar> sysvars = new ArrayList<>();
		String sqlQuery = "FROM Sysvar";
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			sysvars = query.list();		
			trans.commit();
		} catch (RuntimeException e) {
			//log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		} 
		return sysvars;
	}

}
