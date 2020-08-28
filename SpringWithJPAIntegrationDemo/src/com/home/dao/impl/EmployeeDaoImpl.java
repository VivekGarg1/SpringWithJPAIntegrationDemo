package com.home.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.home.dao.EmployeeDao;
import com.home.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	private  EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	@Transactional
	public void createEmployee(Employee employee) throws SQLException {
		entityManager.persist(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		return employee;
	}

	@Override
	@Transactional
	public void deleteEmployeeById(int employeeId) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		if(employee!=null) {
			entityManager.remove(employee);
		}
	}

	@Override
	@Transactional
	public void updateEmployeeById(int employeeId, String newEmail) {
		Employee employee = entityManager.find(Employee.class, employeeId);
		employee.setEmail(newEmail);
		if(employee!=null) {
			entityManager.merge(employee);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		Query query = entityManager.createQuery("from Employee");
		List<Employee> empList = query.getResultList();
		return empList;
	}

}
