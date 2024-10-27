package com.example.ExampleDemo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.ExampleDemo.table.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
	Optional<Employee> findByFirstName(String firstName);

	Optional<Employee> findByEmployeeId(int employeeId);

	List<Employee> findByLastName(String lastName);
}
