package com.spring.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@Entity
@Table(name="employee_table1")
public class Employee {
	@Id
	private int eid;
	private String ename;
	private double salary;
	
}
