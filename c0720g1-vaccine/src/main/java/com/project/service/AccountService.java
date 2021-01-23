package com.project.service;

import com.project.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;

public interface AccountService {

    Account findById(Integer id);
}
