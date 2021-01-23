package com.project.controller;

import com.project.entity.Employee;
import com.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.dto.EmployeeDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api-employee")
public class EmployeeController {
  
    @Autowired
    private EmployeeService employeeService;
  
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        if (employeeList.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
    }
  
    @GetMapping("/edit-employee/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable int id){
        Employee employee1 = employeeService.findById(id);
        if (employee1 == null){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee1, HttpStatus.OK);
    }
  
    @PatchMapping("/save-employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        employeeService.editEmployee(employee.getName(), employee.getDateOfBirth(), employee.getIdCard(), employee.getPhone(),
                employee.getAddress(), employee.getPosition(), employee.getAccount(), employee.getEmployeeId());
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
  
      // luyen code
//     Xem lại tên method, chú ý RequestMapping ở trên để có đường dẫn đúng
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<Void> createVaccinations(@RequestBody EmployeeDto employeeDto) {
        employeeService.createNewEmployee(employeeDto);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
