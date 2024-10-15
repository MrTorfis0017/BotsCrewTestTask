package com.crew.university.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "DEPARTMENT")
public class Department {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "HEAD_ID")
    private Lector head;

    @ManyToMany(mappedBy = "departments")
    private List<Lector> lectors;
}
