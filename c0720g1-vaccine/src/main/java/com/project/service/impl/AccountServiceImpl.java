package com.project.service.impl;

import com.project.entity.Account;
import com.project.repository.AccountRepository;
import com.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<Account> findAccountByUserName(String username) {
        return accountRepository.findAccountByUserName(username);
    }

    @Override
    public Integer findIdUserByUserName(String username) {
        return accountRepository.findIdUserByUserName(username);
    }

    @Override
    public String existsByUserName(String username) {
        return accountRepository.existsByUserName(username);
    }

    @Override
    public String existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public void addNew(String username, String password) {
        accountRepository.addNew(username, password);
    }
/*
* HungDH - hien thi list
 */
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }
}
