package com.crew.university.entities;

import com.crew.university.dto.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "LECTOR")
public class Lector {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DEGREE")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "SALARY", precision = 19, scale = 2)
    private BigDecimal salary;

    @ManyToMany
    @JoinTable(
            name = "DEPARTMENT_LECTOR",
            joinColumns = @JoinColumn(name = "LECTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "DEPARTMENT_ID")
    )
    private List<Department> departments;
}
