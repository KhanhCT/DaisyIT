package com.daisyit.db.abstraction;


import java.util.List;

import com.daisyit.entity.Sysvar;

public interface SysvarDAO {
	Sysvar getParameter(String name) throws DAOException;
	 List<Sysvar> getAllSysvars() throws DAOException ;
}
