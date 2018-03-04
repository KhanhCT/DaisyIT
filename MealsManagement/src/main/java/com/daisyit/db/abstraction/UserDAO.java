package com.daisyit.db.abstraction;

import java.util.List;

import com.daisyit.entity.Users;
import com.daisyit.db.abstraction.DAOException;

public interface UserDAO {
	Users getUsers(Integer id) throws DAOException;
	
	List<Users> getAllUsers() throws DAOException;

	Users deleteUser(Users user) throws DAOException;
	
	Users addUser(Users user) throws DAOException;
	
	List<Users> addMultiUsers(List<Users> users)  throws DAOException;

}
