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
@Entity(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storageId;
    private Long quantity;
    @OneToMany(mappedBy = "storage")
    @JsonBackReference
    private Set<ImportAndExport>importAndExportList;
    @ManyToOne
    @JoinColumn(name = "vaccine_id",nullable = false)
    private Vaccine vaccine;
}
