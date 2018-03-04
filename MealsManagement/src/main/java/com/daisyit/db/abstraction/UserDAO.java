package com.daisyit.db.abstraction;

import java.util.List;

import com.daisyit.entity.Users;
import com.daisyit.db.abstraction.DAOException;

public interface UserDAO {
	Users getUsers(Integer id) throws DAOException;
	
	List<Users> getAllUsers() throws DAOException;

	Users deleteCatering(Users user) throws DAOException;
	
	Users addCatering(Users user) throws DAOException;
	
	void addMultiUsers(List<Users> Users)  throws DAOException;

}
