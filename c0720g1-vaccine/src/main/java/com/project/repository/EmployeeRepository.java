package com.project.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import com.project.entity.Account;
import com.project.entity.Employee;
import com.project.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

//Luyen
// import javax.transaction.Transactional;
// @Transactional


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
    
        //luyen code
    @Modifying
    @Query(value = "insert into employee(name,date_of_birth,id_card," +
            "address,phone,position_id,account_id,delete_flag) values (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    void createNewEmployee(String name, String dateOfBirth, String idCard,
                           String address, String phone, Integer position_id, Integer account_id,Boolean delete_flag);
}
