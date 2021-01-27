package com.project.payload.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignupRequest {
    /**
     * Nguyen Van Linh made it
     */
    @NotBlank
    @Length(min = 6,max = 32)
    private String username;
    @NotBlank
    @Length(min = 8,max = 32)
    private String password;
    @NotBlank
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "wrong format, should be abc@abc.com ")
    private String email;
    /**
     *Nguyen Van Linh
     */
    public SignupRequest() {
    }
    /**
     *Nguyen Van Linh
     */
    public SignupRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    /**
     *Nguyen Van Linh
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
