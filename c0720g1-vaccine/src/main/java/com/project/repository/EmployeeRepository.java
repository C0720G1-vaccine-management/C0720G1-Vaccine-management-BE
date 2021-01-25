package com.project.repository;
import com.project.dto.EmployeeFindIdDTO;
import com.project.dto.EmployeeListDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

//Luyen
// import javax.transaction.Transactional;
// @Transactional


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    /*
    * Hung DH - hien thi list Employee va ten quyen truy cap cua tung nhan vien
     */
    @Query(value = "select employee.employee_id as employeeId, employee.name, employee.date_of_birth as dateOfBirth,\n" +
            "employee.id_card as idCard, employee.address, employee.phone, position.name as position, role.name\n" +
            "as account from employee \n" +
            "join position on position.position_id = employee.position_id \n" +
            "join account on account.account_id = employee.account_id \n" +
            "join account_role on account_role.account_id = account.account_id \n" +
            "join role on role.role_id = account_role.role_id where employee.delete_flag = 0", nativeQuery = true)
    List<EmployeeListDTO> getAllEmployee();

//    @Query(value = "select * from employee", nativeQuery = true)
//    Page<Employee> findAllByNameContainingAndDateOfBirthContainingAndIdCardContaining(String name, String dateOfBirth, String idCard, Pageable pageable);

    /*
     * Hung DH - tim kiem id nhan vien theo ten
     */
    @Query(value = "select employee.employee_id as employeeId, employee.name, employee.date_of_birth as dateOfBirth, \n" +
            "employee.id_card as idCard, employee.address, employee.phone, position.name as position, role.name \n" +
            "as account from employee \n" +
            "join position on position.position_id = employee.position_id \n" +
            "join account on account.account_id = employee.account_id \n" +
            "join account_role on account_role.account_id = account.account_id \n" +
            "join role on role.role_id = account_role.role_id where employee.name like ?1 and employee.delete_flag = false", nativeQuery = true)
    List<EmployeeListDTO> findEmployeeByName(String nameSearch);

    /*
     * Hung DH - tim kiem id nhan vien theo CMND
     */
    @Query(value = "select employee.employee_id as employeeId, employee.name, employee.date_of_birth as dateOfBirth, \n" +
            "employee.id_card as idCard, employee.address, employee.phone, position.name as position, role.name \n" +
            "as account from employee \n" +
            "join position on position.position_id = employee.position_id \n" +
            "join account on account.account_id = employee.account_id \n" +
            "join account_role on account_role.account_id = account.account_id \n" +
            "join role on role.role_id = account_role.role_id where employee.id_card like ?1", nativeQuery = true)
    List<EmployeeListDTO> findEmployeeByIdCard(String idCardSearch);

    /*
     * Hung DH - tim kiem id nhan vien theo id
     */
    @Query(value = "select employee.employee_id as employeeId, employee.name, employee.date_of_birth as dateOfBirth, \n" +
            "employee.id_card as idCard, employee.address, employee.phone, position.position_id as position, account.account_id as account, role.role_id as role from employee \n" +
            "join position on position.position_id = employee.position_id \n" +
            "join account on account.account_id = employee.account_id \n" +
            "join account_role on account_role.account_id = account.account_id \n" +
            "join role on role.role_id = account_role.role_id where employee.employee_id = ?1", nativeQuery = true)
    EmployeeFindIdDTO findById(int id);
    /*
     * Hung DH - chinh sua thong tin nhan vien
     */
    @Transactional
    @Modifying
    @Query(value = "update employee as e set e.name = ?1, e.date_of_birth = ?2, e.id_card = ?3, e.address = ?4, " +
            "e.phone = ?5, e.position_id = ?6, e.account_id = ?7 where e.employee_id = ?8", nativeQuery = true)
    void editEmployee(String name, String dateOfBirth, String idCard, String address, String phone, Integer positionId, Integer accountId, Integer id);
    /*
     * Hung DH - chinh sua id_role voi id account = ? va truyen vao ham eidtEmployee
     */
    @Transactional
    @Modifying
    @Query(value = "update account_role as ar set ar.role_id = ?1 where ar.account_id = ?2", nativeQuery = true)
    void editAccountRole(int roleId, int accountId);
    /*
     * Hung DH
     */
    @Transactional
    @Modifying
    @Query(value = "update employee set employee.delete_flag = 1 where employee.employee_id = ?1", nativeQuery = true)
    void deleteEmployee(int id);
    
        //luyen code
    @Modifying
    @Query(value = "insert into employee(name,date_of_birth,id_card," +
            "address,phone,position_id,account_id,delete_flag) values (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    void createNewEmployee(String name, String dateOfBirth, String idCard,
                           String address, String phone, Integer position_id, Integer account_id,Boolean delete_flag);
}
