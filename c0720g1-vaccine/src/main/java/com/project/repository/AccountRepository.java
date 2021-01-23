package com.project.repository;

import com.project.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByUserName(String username);

    @Query(value = "select account_id from  vaccine_management.account where user_name = ?1", nativeQuery = true)
    Integer findIdUserByUserName(String username);

    @Query(value = "SELECT user_name from  vaccine_management.account where user_name = ?1", nativeQuery = true)
    String existsByUserName(String username);

    @Query(value = "SELECT email FROM vaccine_management.patient where email= ?1", nativeQuery = true)
    String existsByEmail(String email);

    @Modifying
    @Query(value = "insert into account(user_name,encrypt_pw) values (?1,?2)", nativeQuery = true)
    void addNew(String username, String password);
}
