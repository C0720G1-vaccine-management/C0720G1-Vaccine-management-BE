package com.project.service;

import com.project.entity.Account;
import com.project.payload.request.VerifyRequest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;


public interface AccountService {
    /**
     *Nguyen Van Linh
     */
    Account findAccountByUserName(String username);
    /**
     *Nguyen Van Linh
     */
    Integer findIdUserByUserName(String username);
    /**
     *Nguyen Van Linh
     */
    String existsByUserName(String username);
    /**
     *Nguyen Van Linh
     */
    String existsByEmail(String email);
    /**
     *Nguyen Van Linh
     */
    void addNew(String username, String password, String email) throws MessagingException, UnsupportedEncodingException;
    /**
     *Nguyen Van Linh
     */
    Boolean findAccountByVerificationCode(String code);

    /**
     *Nguyen Van Linh
     */
    Boolean findAccountByVerificationCodeToResetPassword(String code);
    /**
     *Nguyen Van Linh
     */
    void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException;

    /**
     * Hung DH - hien thi list
     */
    List<Account> getAllAccount();


    /**
     * LuyenNT code
     */
    void addNew(String username, String password);

    /**
     *Nguyen Van Linh
     */
    void saveNewPassword(String password,String code);
}
