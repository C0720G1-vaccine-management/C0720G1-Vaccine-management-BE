package com.project.repository;
import org.springframework.data.jpa.repository.Modifying;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "select * from employee;", nativeQuery = true)
    List<Employee> getAllEmployee();

    @Query(value = "select * from employee where employee_id = ?;", nativeQuery = true)
    Employee findById(int id);

    @Query(value = "update employee e set e.name = ?1, e.date_of_birth = ?2, e.id_card = ?3, e.phone = ?4, e.address = ?5,\n" +
            "e.position_id = ?6, e.account_id = ?7 where employee_id = ?8;", nativeQuery = true)
    void editEmployee(String name, String dateOfBirth, String idCard, String phone, String address, Position position,
                      Account account, int id);

    @Query(value = "delete from employee where employee_id = ?;", nativeQuery = true)
    void deleteEmployee(int id);

    /** LuyenNT code
     * @param name
     * @param dateOfBirth
     * @param idCard
     * @param address
     * @param phone
     * @param position
     * @param account
     * @param delete_flag
     */
    @Modifying
    @Query(value = "insert into employee(name,date_of_birth,id_card," +
            "address,phone,position_id,account_id,delete_flag) values (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    void createNewEmployee(String name, String dateOfBirth, String idCard,
                           String address, String phone, Integer position, Integer account,Boolean delete_flag);

    /** LuyenNT code
     * @param phone
     * @return
     */
    @Query(value = "select count(phone) from employee where phone = ?", nativeQuery = true)
    Integer findByPhone(String phone);

    /** LuyenNT code
     * @param idCard
     * @return
     */
    @Query(value = "select count(id_card) from employee where id_card = ?", nativeQuery = true)
    Integer finByIdCard(String idCard);
}
