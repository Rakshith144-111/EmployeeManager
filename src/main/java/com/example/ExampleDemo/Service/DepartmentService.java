package com.example.ExampleDemo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleDemo.Repository.DepartmentRepository;
import com.example.ExampleDemo.table.Department;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepo;

	public String addDepartment(Department dept) {
		
		if(deptRepo.findByDepartment(dept.getDepartment()).isPresent())
		{
			return "Department already Exists";
		}
		
		deptRepo.save(dept);
		return "Department Added Succesfully";
	}

	public List<Department> listAllEmp() {
		return (List<Department>) deptRepo.findAll();
	}

}
