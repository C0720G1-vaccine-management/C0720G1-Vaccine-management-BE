package com.project.service.impl;

import com.project.entity.Account;
import com.project.repository.AccountRepository;
import com.project.service.AccountService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JavaMailSender javaMailSender;

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
    public void addNew(String username, String password, String email) throws MessagingException {
        String randomCode = RandomString.make(64);
        accountRepository.addNew(username, password, false, randomCode);
        sendVerificationEmail(username, randomCode, email);
    }

    /** LuyenNT
     * @param username
     * @param password
     */
    @Override
    public void addNew(String username, String password) {
        accountRepository.addNew(username,password,true,null);
    }

    @Override
    public Boolean findAccountByVerificationCode(String code) {
        Account account = accountRepository.findAccountByVerificationCode(code);
        if(account ==null||account.getEnabled()){
            return false;
        }else {
            account.setEnabled(true);
            account.setVerificationCode(null);
            accountRepository.save(account);
            return true;
        }
    }

    private void sendVerificationEmail(String userName, String randomCode, String email) throws MessagingException {
        String subject = "Hãy xác thực email của bạn";
        String senderName = "Cục tiêm chủng Thành Phố";
        String mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>";
        String confirmUrl = "http://localhost:4200/verification?code=" + randomCode;
        mailContent += "<p> Nhấn vào link sau để xác thực email của bạn:</p>";
        mailContent += "<h3><a href='" + confirmUrl + "'>Link Xác thực( nhấn vào đây)!</a></h3>";
        mailContent += "<p>Cục tiêm chủng thành Phố</p>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }
}
