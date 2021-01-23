package com.project.service.impl;

import com.project.entity.Account;
import com.project.repository.AccountRepository;
import com.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(Integer id) {
        return this.accountRepository.findById(id).orElse(null);
    }
}
