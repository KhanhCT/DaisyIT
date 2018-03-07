package com.daisyit.db.abstraction;

import java.util.List;

import com.daisyit.entity.Staff;
import com.daisyit.db.abstraction.DAOException;

public interface StaffDAO {

	Staff getStaff(String staffId) throws DAOException;

	String getStaffName(String staffId) throws DAOException;

	List<Staff> getAllStaffs() throws DAOException;

	Staff deleteStaff(Staff staff) throws DAOException;

	Staff addStaff(Staff staff) throws DAOException;

	List<Staff> addMultiStaffs(List<Staff> staffs) throws DAOException;

	List<Staff> getAllStaffs(String deptId) throws DAOException;

	Boolean isValidStaff(String staffId);

}
