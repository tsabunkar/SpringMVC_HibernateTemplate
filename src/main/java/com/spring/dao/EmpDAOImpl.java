package com.spring.dao;

import java.sql.SQLException;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Employee;

@Repository(value = "daoimp")
@Transactional
public class EmpDAOImpl implements EmpDAO {

	@Autowired
	@Qualifier(value = "hiberTemp")
	
	private HibernateTemplate ht;
	
	
	public int addEmp(Employee e) throws SQLException {
		int i = (Integer) ht.save(e);

		return i;
	}

}
