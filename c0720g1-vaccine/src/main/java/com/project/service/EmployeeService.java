package com.project.service;
import com.project.dto.EmployeeDto;
import com.project.dto.EmployeeEditDTO;
import com.project.dto.EmployeeFindIdDTO;
import com.project.dto.EmployeeListDTO;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;
import java.util.List;

public interface EmployeeService {
    /*
    * HungDH
     */
    List<EmployeeListDTO> getAllEmployee();
    EmployeeFindIdDTO findById(int id);
    void editEmployee(EmployeeEditDTO employeeEditDTO, int roleId, int accountId);
    void deleteEmployee(int id);
    
        // luyen code
    void createNewEmployee(EmployeeDto employeeDto);
}
