package com.crew.university.repositories;

import com.crew.university.entities.LectorDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectorDepartmentRepository extends JpaRepository<LectorDepartment, Long> {

    @Query(value = "SELECT T.lectorId " +
            "FROM LECTOR_DEPARTMENT T " +
            "WHERE T.departmentId=:id")
    List<Long> findRelatedToDepartmentLectors(@Param("id") Long id);
}
