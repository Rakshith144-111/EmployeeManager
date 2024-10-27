package com.example.ExampleDemo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ExampleDemo.DTO.EmployeeDTo;
import com.example.ExampleDemo.Exception.EmployeeIdMissingException;
import com.example.ExampleDemo.Repository.DepartmentRepository;
import com.example.ExampleDemo.Repository.EmployeeRepository;
import com.example.ExampleDemo.table.Department;
import com.example.ExampleDemo.table.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	public List<Employee> getAllEmp()
	{
		 return (List<Employee>) empRepo.findAll();
	}

	public String addEmployee(EmployeeDTo emp) {
		// TODO Auto-generated method stub
		if(emp.getEmployeeId()== 0)
		{
			throw new EmployeeIdMissingException("Employee Id need to be provided");
		}
		if(empRepo.findByFirstName(emp.getFirstName()).isPresent())
			return "Employee Already Exists";
		
		Employee employee = new Employee();
		employee.setAge(emp.getAge());
		employee.setEmployeeId(emp.getEmployeeId());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		
		//EmployeeDTo eDto = new EmployeeDTo();
		
		// Associate the Employee with a Department
		Optional<Department> optionalDepartment = deptRepo.findById(emp.getDepartmentId());

		if (optionalDepartment.isPresent()) {
		    Department department = optionalDepartment.get();
		    
		    // Check if any specific field of the department is null, if needed
		    if (department.getDepartment() == null) { // Assuming 'getDepartmentName()' is a method in your Department class
		        return "Department found, but the name is missing.";
		    }
		    
		    // Associate the Employee with the Department
		    employee.setDepartment(department);
		} else {
		    return "Department not found"; // Return message if the department does not exist
		}
		
	    empRepo.save(employee);
		return "Employee Added Succesfully";
	}

	public String deleteTheEmployee(int emplId) {
        Optional<Employee> employee = empRepo.findByEmployeeId(emplId);
        if (employee.isPresent()) {
            empRepo.delete(employee.get());
            return "Employee deleted successfully";
        }
        return "Provided employee not found in the database";
    }

	  public String deleteEmployeeByLastName(String lastName) {
	        List<Employee> employees = empRepo.findByLastName(lastName); // Get all employees with the last name
	        
	        if (employees.isEmpty()) {
	            return "No employees found with last name: " + lastName; // Handle case where no employees are found
	        }

	        for (Employee employee : employees) {
	            empRepo.delete(employee); // Delete each employee found
	        }

	        return employees.size() + " employees deleted with last name: " + lastName;
	    }

	public String AddMultipleEmployees(List<Employee> employees) {
		
		StringBuilder str = new StringBuilder();
		
		for(Employee employee : employees)
		{
			if(employee.getEmployeeId() == 0)
			{
				str.append("Please provide the Employement id for"+employee.getFirstName()+"\n");
				continue;
			}
			
			if(empRepo.findByEmployeeId(employee.getEmployeeId()).isPresent())
			{
				str.append("User with Employee Id"+employee.getEmployeeId()+"already exists .PLease regoster with diffrent Employee Id"+"\n");
				continue;
			}
			
			empRepo.save(employee);
			str.append("Employee with Id"+employee.getEmployeeId()+" added Succesfully"+"\n");
		}
		
		return str.toString();
	}
}
