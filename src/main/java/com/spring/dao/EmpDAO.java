package com.spring.dao;

import java.sql.SQLException;

import com.spring.model.Employee;

public interface EmpDAO {
	public int addEmp(Employee e) throws SQLException;
}
