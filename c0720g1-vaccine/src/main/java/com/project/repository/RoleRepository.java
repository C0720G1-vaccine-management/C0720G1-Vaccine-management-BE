package com.project.repository;

import com.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select name from role join account_role on role.role_id = account_role.role_id join account on account_role.account_id = account.account_id where account.account_id =?1", nativeQuery = true)
    List<String> getAllRoleName(Integer accountId);

    /** LuyenNT code
     * @return
     */
    @Query(value = "select * from role",nativeQuery = true)
    List<Role> finAllRole();


    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) values (?1,?2)", nativeQuery = true)
    void setDefaultRole(int accountId, Integer roleId);

    /*
    * HungDH
     */
}
