package com.project.service;
import com.project.dto.EmployeeDto;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;

import javax.mail.MessagingException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    Employee findById(int id);
    void editEmployee(String name, String dateOfBirth, String idCard, String phone, String address, Position position,
                      Account account, int id);
    void deleteEmployee(int id);

    /** LuyenNT code
     * @param employeeDto
     * @throws MessagingException
     */
    void createNewEmployee(EmployeeDto employeeDto) throws MessagingException;

    /** LuyenNT code
     * @param phone
     * @return
     */
    Integer findByPhone(String phone);

    Integer findByIdCard(String idCard);
}
