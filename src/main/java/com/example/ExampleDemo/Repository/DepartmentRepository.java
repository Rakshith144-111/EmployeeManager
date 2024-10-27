package com.example.ExampleDemo.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ExampleDemo.table.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

	Optional<Department> findByDepartment(String department);

}