package com.project.service.impl;

import com.project.entity.Account;
import com.project.payload.request.VerifyRequest;
import com.project.repository.AccountRepository;
import com.project.service.AccountService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

/**
 * Nguyen Van Linh
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * Nguyen Van Linh
     */
    @Override
    public Account findAccountByUserName(String username) {
        return accountRepository.findAccountByUserName(username);
    }

    /**
     * Nguyen Van Linh
     */
    @Override
    public Integer findIdUserByUserName(String username) {
        return accountRepository.findIdUserByUserName(username);
    }

    /**
     * Nguyen Van Linh
     */
    @Override
    public String existsByUserName(String username) {
        return accountRepository.existsByUserName(username);
    }

    /**
     * Nguyen Van Linh
     */
    @Override
    public String existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }


    /**
     * LuyenNT
     */
    @Override
    public void addNew(String username, String password) {
        accountRepository.addNewAccount(username, password);
    }

    @Override
    public void saveNewPassword(String password,String code) {
        accountRepository.saveNewPassword(password,code);
    }


    /**
     * Nguyen Van Linh
     */
    @Override
    public void addNew(String username, String password, String email) throws MessagingException, UnsupportedEncodingException {
        String randomCode = RandomString.make(64);
        accountRepository.addNew(username, password, false, randomCode, email);
        sendVerificationEmail(username, randomCode, email);
    }

    /**
     * Nguyen Van Linh
     */
    @Override
    public Boolean findAccountByVerificationCode(String code) {
        Account account = accountRepository.findAccountByVerificationCode(code);
        if (account == null || account.getEnabled()) {
            return false;
        } else {
            account.setEnabled(true);
            account.setVerificationCode(null);
            accountRepository.save(account);
            return true;
        }
    }

    /**
     * Nguyen Van Linh
     */
    @Override
    public Boolean findAccountByVerificationCodeToResetPassword(String code) {
        Account account = accountRepository.findAccountByVerificationCode(code);
        return account != null;
    }

    /**
     * Nguyen Van Linh
     */
    @Override
    public void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException {
        String code = RandomString.make(64);
        accountRepository.addVerificationCode(code, username);
        Account account = accountRepository.findAccountByVerificationCode(code);
        this.sendVerificationEmailForResetPassWord(account.getUserName(), code, account.getEmail());
    }

    /**
     * Nguyen Van Linh
     */
    public void sendVerificationEmail(String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:4200/verification?code=" + randomCode;


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(email);
        helper.setFrom("vanlinh12b5@gmail.com","TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    public void sendVerificationEmailForResetPassWord(String userName, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";
        String confirmUrl = "http://localhost:4200/verify-reset-password?code=" + randomCode;


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("vanlinh12b5@gmail.com","TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
        helper.setTo(email);
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>" + "<p> Nhấn vào link sau để xác thực email của bạn:</p>" +
                "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>" +
                "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }


    /**
     * HungDH - hien thi list
     */
    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }
}
