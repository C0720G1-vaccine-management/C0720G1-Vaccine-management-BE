package com.project.controller;

import com.project.entity.Account;
import com.project.jwt.JwtUtility;
import com.project.payload.reponse.JwtResponse;
import com.project.payload.reponse.MessageResponse;
import com.project.payload.request.LoginRequest;
import com.project.payload.request.ResetPassRequest;
import com.project.payload.request.SignupRequest;
import com.project.payload.request.VerifyRequest;
import com.project.service.AccountService;
import com.project.service.RoleService;
import com.project.service.impl.AccountDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {
    /**
     * Nguyen Van Linh made it
     */
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;


    /**
     * Nguyen Van Linh made it
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Account account = accountService.findAccountByUserName(loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles,account.getEmail()));
    }

    /**
     * Nguyen Van Linh made it
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {
        if (accountService.existsByUserName(signUpRequest.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Tên tài khoản đã được sử dụng!!!"));
        }

        if ((accountService.existsByEmail(signUpRequest.getEmail())) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email đã được sử dụng!"));
        }

        // Create new user's account
        Account account = new Account(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        //Add new user's account to database
        accountService.addNew(account.getUserName(), account.getEncryptPw(), signUpRequest.getEmail());
        //Find ID user's account newest after add to database
        Integer idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        //Set default role is "ROLE_USER"
        roleService.setDefaultRole(idAccountAfterCreated, 1);

        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
    }

    /**
     * Nguyen Van Linh made it
     */
    @PostMapping("/verify")
    public ResponseEntity<?> VerifyEmail(@RequestBody VerifyRequest code) {
        Boolean isVerified = accountService.findAccountByVerificationCode(code.getCode());
        if (isVerified) {
            return ResponseEntity.ok(new MessageResponse("activated"));
        } else {
            return ResponseEntity.ok(new MessageResponse("error"));
        }
    }
    /**
     * Nguyen Van Linh made it
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> reset(@RequestBody LoginRequest loginRequest) throws MessagingException, UnsupportedEncodingException {

        if (accountService.existsByUserName(loginRequest.getUsername()) != null) {
            accountService.addVerificationCode(loginRequest.getUsername());
            return ResponseEntity.ok(new MessageResponse("Sent email "));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Tài khoản không đúng"));
    }

    /**
     * Nguyen Van Linh made it
     */
    @PostMapping("/verify-password")
    public ResponseEntity<?> VerifyPassword(@RequestBody VerifyRequest code) {
        Boolean isVerified = accountService.findAccountByVerificationCodeToResetPassword(code.getCode());
        if (isVerified) {
            return ResponseEntity.ok(new MessageResponse("accepted"));
        } else {
            return ResponseEntity.ok(new MessageResponse("error"));
        }
    }

    /**
     * Nguyen Van Linh made it
     */
    @PostMapping("/do-reset-password")
    public ResponseEntity<?> doResetPassword(@RequestBody ResetPassRequest resetPassRequest) {
        accountService.saveNewPassword(encoder.encode(resetPassRequest.getPassword()),resetPassRequest.getCode());
            return ResponseEntity.ok(new MessageResponse("success"));
    }

}
