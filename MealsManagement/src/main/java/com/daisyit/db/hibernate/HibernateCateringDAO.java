package com.daisyit.db.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.abstraction.CateringDAO;
import com.daisyit.db.abstraction.DAOException;
import com.daisyit.entity.Catering;
import com.daisyit.entity.CateringId;
import com.daisyit.utils.Log;

public class HibernateCateringDAO implements CateringDAO {
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
	public Catering getCatering(CateringId cateringId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Catering> getAllCaterings() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Catering deleteCatering(Catering catering) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Catering addCatering(Catering catering) throws DAOException {
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			this.session.saveOrUpdate(catering);
			trans.commit();
		} catch (RuntimeException e) {
			// TODO: handle exception
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return null;
	}

	@Override
	public List<Catering> addMultiCaterings(List<Catering> caterings) throws DAOException {
		Transaction trans = null;
		try {
			trans = this.session.beginTransaction();
			for (int i = 0; i < caterings.size(); i++) {
				this.session.saveOrUpdate(caterings.get(i));
				if (i % 20 == 0) {
					this.session.flush();
					this.session.clear();
				}
			}
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return null;
	}

	@Override
	public List<Catering> getAllCaterings(String mealTime, Date cateringDate, Boolean status) throws DAOException {
		Transaction trans = null;
		String sqlQuery = "FROM Catering c WHERE c.id.caterDate= :cateringDate AND c.id.mealTtme= :mealTime AND catered= :catered";
		List<Catering> cateringList = new ArrayList<Catering>();
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setString("mealTime", mealTime);
			query.setDate("cateringDate", cateringDate);
			query.setBoolean("catered", status);
			cateringList = query.list();
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return cateringList;
	}

}
