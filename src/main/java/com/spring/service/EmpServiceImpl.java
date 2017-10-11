package com.spring.service;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.EmpDAO;
import com.spring.dao.EmpDAOImpl;
import com.spring.model.Employee;

import lombok.Setter;
import lombok.val;

@Service(value = "servimp")

public class EmpServiceImpl implements EmpService {
	@Autowired
	@Qualifier(value = "daoimp")
	private EmpDAO edao;
	

	public int saveEmp(Employee e) throws SQLException {
		System.out.println("EmpService Implem start...");
		return edao.addEmp(e);

	}
}
