package com.project.service.impl;
import com.project.dto.EmployeeDto;
import com.project.dto.EmployeeEditDTO;
import com.project.dto.EmployeeFindIdDTO;
import com.project.dto.EmployeeListDTO;
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
    /*
    * HungDH
     */
    @Override
    public List<EmployeeListDTO> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }
    /*
     * HungDH
     */
    @Override
    public EmployeeFindIdDTO findById(int id) {
        return employeeRepository.findById(id);
    }
    /*
     * HungDH
     */
    @Override
    public void editEmployee(EmployeeEditDTO employeeEditDTO, int roleId, int accountId) {
        employeeRepository.editAccountRole(roleId, accountId);
        employeeRepository.editEmployee(employeeEditDTO.getName(), employeeEditDTO.getDateOfBirth(), employeeEditDTO.getIdCard(), employeeEditDTO.getAddress(),
                employeeEditDTO.getPhone(), Integer.parseInt(employeeEditDTO.getPosition()), Integer.parseInt(employeeEditDTO.getAccount()), employeeEditDTO.getEmployeeId());
    }
    /*
     * HungDH
     */
    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }
    /*
     * HungDH
     */
    @Override
    public List<EmployeeListDTO> findEmployeeByName(String nameSearch) {
        return employeeRepository.findEmployeeByName(nameSearch);
    }

    @Override
    public List<EmployeeListDTO> findEmployeeByIdCard(String idCardSearch) {
        return employeeRepository.findEmployeeByIdCard(idCardSearch);
    }

    //luyen code
    @Override
    public void createNewEmployee(EmployeeDto employeeDto) {
        employeeRepository.createNewEmployee(employeeDto.getName(),employeeDto.getDateOfBirth(),employeeDto.getIdCard(),employeeDto.getAddress(),employeeDto.getPhone(),employeeDto.getPosition_id(),employeeDto.getAccount_id(),false);
    }
}