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
        if (emp.getEmployeeId() == 0) {
            throw new EmployeeIdMissingException("Employee Id needs to be provided");
        }
        if (empRepo.findByFirstName(emp.getFirstName()).isPresent()) {
            return "Employee Already Exists";
        }

        Employee employee = new Employee();
        employee.setAge(emp.getAge());
        employee.setEmployeeId(emp.getEmployeeId());
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());

        // Use departmentId from EmployeeDTo to fetch the department
        Optional<Department> optionalDepartment = deptRepo.findByDepartmentId(emp.getDepartmentId());
        
        if (optionalDepartment.isPresent()) {
            employee.setDepartment(optionalDepartment.get());
        } else {
            return "Department not found"; // Return message if the department does not exist
        }

        empRepo.save(employee);
        return "Employee Added Successfully";
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

	  public String AddMultipleEmployees(List<EmployeeDTo> employeeDtos) {
	        StringBuilder str = new StringBuilder();
	        
	        for (EmployeeDTo empDto : employeeDtos) {
	            if (empDto.getEmployeeId() == 0) {
	                str.append("Please provide the Employee ID for " + empDto.getFirstName() + "\n");
	                continue;
	            }
	            
	            if (empRepo.findByEmployeeId(empDto.getEmployeeId()).isPresent()) {
	                str.append("User with Employee ID " + empDto.getEmployeeId() + " already exists. Please register with a different Employee ID\n");
	                continue;
	            }

	            // Map EmployeeDTo to Employee and set department
	            Employee employee = new Employee();
	            employee.setAge(empDto.getAge());
	            employee.setEmployeeId(empDto.getEmployeeId());
	            employee.setFirstName(empDto.getFirstName());
	            employee.setLastName(empDto.getLastName());
	            
	            Optional<Department> optionalDepartment = deptRepo.findByDepartmentId(empDto.getDepartmentId());
	            if (optionalDepartment.isPresent()) {
	                employee.setDepartment(optionalDepartment.get());
	                empRepo.save(employee);
	                str.append("Employee with ID " + employee.getEmployeeId() + " added successfully\n");
	            } else {
	                str.append("Department for employee " + employee.getFirstName() + " not found\n");
	            }
	        }
	        
	        return str.toString();
	    }
}
