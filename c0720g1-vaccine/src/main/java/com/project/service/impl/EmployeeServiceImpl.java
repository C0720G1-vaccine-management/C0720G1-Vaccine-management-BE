package com.project.service.impl;
import com.project.dto.EmployeeDto;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;
import com.project.repository.EmployeeRepository;
import com.project.service.AccountService;
import com.project.service.EmployeeService;
import com.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    /** LuyenNT code
     * @return
     */
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
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

    /**
     * LuyenNT code
     *
     * @param employeeDto
     */
    @Override
    public void createNewEmployee(EmployeeDto employeeDto) throws MessagingException {

        employeeRepository.createNewEmployee(employeeDto.getName(), employeeDto.getDateOfBirth(), employeeDto.getIdCard(), employeeDto.getAddress(), employeeDto.getPhone(), employeeDto.getPosition(), employeeDto.getAccount(), false);
        Account account = new Account();
        account.setUserName(employeeDto.getIdCard());
        account.setEncryptPw(encoder.encode("123"));
        account.setEnabled(true);
        accountService.addNew(employeeDto.getIdCard(),encoder.encode("123"));
        int id = accountService.findIdUserByUserName(employeeDto.getIdCard());
        roleService.setDefaultRole(id,employeeDto.getAccount());
    }

    /**
     * LuyenNT code
     * @param phone
     * @return
     */
    @Override
    public Integer findByPhone(String phone){
        return employeeRepository.findByPhone(phone);
    }

    /** LuyenNT code
     * @param idCard
     * @return
     */
    @Override
    public Integer findByIdCard(String idCard) {
        return employeeRepository.finByIdCard(idCard);
    }
}
