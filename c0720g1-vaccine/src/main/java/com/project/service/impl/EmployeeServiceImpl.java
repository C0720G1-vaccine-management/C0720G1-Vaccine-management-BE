package com.project.service.impl;
import com.project.dto.EmployeeDto;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;
import com.project.repository.EmployeeRepository;
import com.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
      
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void editEmployee(String name, String dateOfBirth, String idCard, String phone, String address, Position position, Account account, int id) {
        employeeRepository.editEmployee(name, dateOfBirth, idCard, phone, address, position, account, id);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }
  
  //luyen code
    @Override
    public void createNewEmployee(EmployeeDto employeeDto) {
        employeeRepository.createNewEmployee(employeeDto.getName(),employeeDto.getDateOfBirth(),employeeDto.getIdCard(),employeeDto.getAddress(),employeeDto.getPhone(),employeeDto.getPosition_id(),employeeDto.getAccount_id(),false);
    }
}