package com.daisyit.db.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.daisyit.db.abstraction.DAOException;
import com.daisyit.db.abstraction.MealDAO;
import com.daisyit.entity.Meal;
import com.daisyit.utils.Log;

public class HibernateMealDAO implements MealDAO {
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
	public String getMealName(String mealId) throws DAOException {
		// TODO Auto-generated method stub
		Transaction trans = null;
		String sqlQuery = "SELECT mealName FROM Meal WHERE mealId= :mealId";
		String meal = null;
		try {
			trans = this.session.beginTransaction();
			Query query = this.session.createQuery(sqlQuery);
			query.setString("mealId", mealId);
			meal = (String) query.uniqueResult();
			trans.commit();
		} catch (RuntimeException e) {
			log.writeLogError(this.getClass().getMethods().toString(), e.getMessage());
			if (trans != null) {
				trans.rollback();
			}
		}
		return meal;
	}

}
