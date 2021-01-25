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
    * HungDH - Hien thi danh sach nhan vien
     */
    List<EmployeeListDTO> getAllEmployee();
    /*
     * HungDH - tim kiem nhan vien theo id
     */
    EmployeeFindIdDTO findById(int id);
    /*
     * HungDH - chinh sua thong tin nhan vien
     */
    void editEmployee(EmployeeEditDTO employeeEditDTO, int roleId, int accountId);
    /*
     * HungDH - xoa nhan vien
     */
    void deleteEmployee(int id);
    /*
     * HungDH - tim kiem nhan vien theo ten
     */
    List<EmployeeListDTO> findEmployeeByName(String nameSearch);

    /*
     * HungDH - tim kiem nhan vien theo CMND
     */
    List<EmployeeListDTO> findEmployeeByIdCard(String idCardSearch);

        // luyen code
    void createNewEmployee(EmployeeDto employeeDto);
}
