package com.example.ExampleDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExampleDemo.Service.DepartmentService;
import com.example.ExampleDemo.table.Department;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService deptService;
	
	@PostMapping("/addDept")
	public String addDepartment(@RequestBody Department dept)
	{
		return deptService.addDepartment(dept);
	}
	
	@GetMapping("/department")
	public List<Department> ShowDept()
	{
		return deptService.listAllEmp();
	}

}
