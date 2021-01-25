package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @NotBlank(message = "Please provide a userName")
    private String userName;

    @NotBlank(message = "Please provide a encryptPw")
    private String encryptPw;
    private String token;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Patient patient;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Employee employee;

    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private Set<AccountRole> accountRoleList;

    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private Set<ImportAndExport> importAndExportList;

    public Account(String userName, String encryptPw) {
        this.userName = userName;
        this.encryptPw = encryptPw;
    }
}
