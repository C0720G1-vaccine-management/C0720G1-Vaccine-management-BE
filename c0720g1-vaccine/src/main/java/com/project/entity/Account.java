package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String userName;
    private String encryptPw;
    private String token;
    @OneToOne(mappedBy = "account")
    private Patient patient;
    @OneToOne(mappedBy = "account")
    private Employee employee;
    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private Set<AccountRole>accountRoleList;
    @OneToMany(mappedBy = "account")
    @JsonBackReference
    private Set<ImportAndExport>importAndExportList;
}
