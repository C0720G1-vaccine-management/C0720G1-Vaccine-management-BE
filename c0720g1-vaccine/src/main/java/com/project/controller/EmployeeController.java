package com.project.controller;

import com.project.entity.Employee;
import com.project.entity.Position;
import com.project.entity.Role;
import com.project.service.EmployeeService;
import com.project.service.PositionService;
import com.project.service.RoleService;
import com.project.validation.EmployeeCreateByRequestDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/public/employee")
public class EmployeeController {
  
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmployeeCreateByRequestDtoValidator employeeCreateByRequestDtoValidator;
  
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
    /** LuyenNT code
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createVaccinations(@Valid @RequestBody EmployeeDto employeeDto, BindingResult bindingResult) throws MessagingException {
        employeeCreateByRequestDtoValidator.validate(employeeDto,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.OK);
        }
        employeeService.createNewEmployee(employeeDto);
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
    /** LuyenNT code
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> createVaccinations() {
        List<Position> positionList = positionService.getAllPosition();
        List<Role> roleList = roleService.getAllRoles();
        List<Object> list = Arrays.asList(positionList,roleList);
        return new ResponseEntity<List<Object>>(list,HttpStatus.OK);
    }
}
