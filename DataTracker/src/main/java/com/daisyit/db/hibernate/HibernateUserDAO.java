package com.daisyit.db.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.abstraction.DAOException;
import com.daisyit.db.abstraction.UserDAO;
import com.daisyit.entity.Staff;
import com.daisyit.entity.User;
import com.daisyit.utils.Log;

public class HibernateUserDAO implements UserDAO{
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
	public User getUsers(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		String sqlQuery = "FROM User WHERE id= :id";
		User user = null;
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setInteger("id", id);
			user = (User) query.uniqueResult();
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		List<User> user = new ArrayList<>();
		String sqlQuery = "FROM User";
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			user = query.list();		
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		} 
		return user;
	}

	@Override
	public User deleteUser(User user) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			this.session.delete(user);
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
	public User addUser(User user) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			this.session.save(user);
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
	public List<User> addMultiUsers(List<User> users) throws DAOException {
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			for (int i = 0; i < users.size(); i++) {
				this.session.saveOrUpdate(users.get(i));
				if (i % 20 == 0) {
					//this.session.flush();
					//this.session.clear();
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
	@Override
	public Boolean isValidUser(String staffId) {
		Transaction trans = null;
		String sqlQuery = "FROM User WHERE staffId= :staffId";
		User user = null;
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setString("staffId", staffId);
			user = (User) query.uniqueResult();
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return user != null ? true : false;
	}

}
