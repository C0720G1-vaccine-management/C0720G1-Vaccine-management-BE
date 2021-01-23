package com.project.service;

import com.project.entity.Account;

import java.util.Optional;


public interface AccountService {
    Optional<Account> findAccountByUserName(String username);

    Integer findIdUserByUserName(String username);

    String existsByUserName(String username);

    String existsByEmail(String email);

    void addNew(String username, String password);
}
