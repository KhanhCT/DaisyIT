package com.daisyit.db.abstraction;

import java.util.List;

public interface MealDAO {
	String getMealName(String mealId) throws DAOException;
	String getMealId(String mealName) throws DAOException ;
	List<String> getMealNames() throws DAOException ;
	
}
