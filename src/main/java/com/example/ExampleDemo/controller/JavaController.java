package com.example.ExampleDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExampleDemo.DTO.EmployeeDTo;
import com.example.ExampleDemo.Service.EmployeeService;
import com.example.ExampleDemo.table.Employee;

@RestController
public class JavaController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		return employeeService.getAllEmp();
	}
	
	@PostMapping("/addEmployee")
	public String AddEmployee(@RequestBody EmployeeDTo emp)
	{
		return employeeService.addEmployee(emp);
	}
	
	 @DeleteMapping("/deleteById/{emplId}")
	    public String deleteEmployee(@PathVariable int emplId) {
	        return employeeService.deleteTheEmployee(emplId);
	    }
	 
	 @DeleteMapping("/deleteByLastname/{lastName}")
	 public String deleteEmployeeByLastName(@PathVariable String lastName)
	 {
		 return employeeService.deleteEmployeeByLastName(lastName);
	 }
	 
	 @PostMapping("/addMultipleEmployees")
	 public String addingMulEmployees(@RequestBody List<EmployeeDTo> employeeDtos) {
	     return employeeService.AddMultipleEmployees(employeeDtos);
	 }
	

}
