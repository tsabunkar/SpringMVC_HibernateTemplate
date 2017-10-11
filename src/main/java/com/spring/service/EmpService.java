package com.spring.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.spring.model.Employee;

public interface EmpService {
 public int saveEmp(Employee e) throws SQLException;
}
