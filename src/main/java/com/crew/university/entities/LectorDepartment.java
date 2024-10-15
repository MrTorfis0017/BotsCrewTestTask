package com.crew.university.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "LECTOR_DEPARTMENT")
public class LectorDepartment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LECTOR_ID")
    private Long lectorId;

    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;
}
