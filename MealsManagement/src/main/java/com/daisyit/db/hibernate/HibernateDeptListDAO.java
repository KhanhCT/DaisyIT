package com.daisyit.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.abstraction.DAOException;
import com.daisyit.db.abstraction.DeptListDAO;
import com.daisyit.entity.Deptlist;
import com.daisyit.entity.User;
import com.daisyit.utils.Log;

public class HibernateDeptListDAO implements DeptListDAO {
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
	public Deptlist getDeptList(String deptId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllDeptLists() throws DAOException {
		Transaction trans = null;
		List<String> deptlist = new ArrayList<>();
		String sqlQuery = "SELECT deptName FROM Deptlist";
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			deptlist = query.list();		
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		} 
		return deptlist;
	}

	@Override
	public Deptlist deleteDeptList(Deptlist Deptlist) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deptlist addDeptList(Deptlist deptlist) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deptlist> addMultiSDeptLists(List<Deptlist> deptLists) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeptListId(String deptName) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		String sqlQuery = "SELECT deptId FROM Deptlist WHERE deptName= :deptName";
		String deptId = null;
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setString("deptName", deptName);
			deptId = (String) query.uniqueResult();
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return deptId;
	}

}
