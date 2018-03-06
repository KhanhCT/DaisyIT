package com.daisyit.db.abstraction;

import java.util.List;

import com.daisyit.entity.User;
import com.daisyit.db.abstraction.DAOException;

public interface UserDAO {
	User getUsers(Integer id) throws DAOException;
	
	List<User> getAllUsers() throws DAOException;

	User deleteUser(User user) throws DAOException;
	
	User addUser(User user) throws DAOException;
	
	List<User> addMultiUsers(List<User> users)  throws DAOException;
	
	Boolean isValidUser(String staffId);

}
