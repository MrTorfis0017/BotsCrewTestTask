package com.crew.university.repositories;


import com.crew.university.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT T " +
            " FROM DEPARTMENT T " +
            "WHERE LOWER(T.name) LIKE LOWER(:name)")
    Department getByName(@Param("name") String name);

    @Query("SELECT T.name" +
            " FROM DEPARTMENT T " +
            "WHERE LOWER(T.name) LIKE LOWER(CONCAT('%', :template, '%'))")
    String[] searchNamesByTemplate(@Param("template") String template);
}
