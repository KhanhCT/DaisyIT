package com.daisyit.db.abstraction;

import java.util.List;

import com.daisyit.entity.Deptlist;
import com.daisyit.db.abstraction.DAOException;
public interface DeptListDAO {
	Deptlist getDeptList(String deptId) throws DAOException;
	
	List<Deptlist> getAllDeptLists() throws DAOException;
	
	Deptlist deleteDeptList(Deptlist Deptlist) throws DAOException;
	
	Deptlist addDeptList(Deptlist deptlist) throws DAOException;
	
	List<Deptlist> addMultiSDeptLists(List<Deptlist> deptLists)  throws DAOException;
}
