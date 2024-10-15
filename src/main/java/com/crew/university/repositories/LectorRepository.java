package com.crew.university.repositories;

import com.crew.university.entities.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "SELECT COUNT(T.id) " +
            "FROM LECTOR T " +
            "WHERE T.id IN :ids AND T.degree='PROFESSOR'")
    Long countProfessors(@Param("ids") List<Long> ids);

    @Query(value = "SELECT COUNT(T.id) " +
            "FROM LECTOR T " +
            "WHERE T.id IN :ids AND T.degree='ASSOCIATE_PROFESSOR'")
    Long countAssociateProfessor(@Param("ids") List<Long> ids);

    @Query(value = "SELECT COUNT(T.id) " +
            "FROM LECTOR T " +
            "WHERE T.id IN :ids AND T.degree='ASSISTANT'")
    Long countAssistants(@Param("ids") List<Long> ids);

    @Query(value = "SELECT AVG(T.salary) " +
            "FROM LECTOR T " +
            "WHERE T.id IN :ids")
    BigDecimal getAverageSalary(@Param("ids") List<Long> ids);

    @Query("SELECT T.fullName" +
            " FROM LECTOR T " +
            "WHERE LOWER(T.fullName) LIKE LOWER(CONCAT('%', :template, '%'))")
    String[] searchFullNamesByTemplate(@Param("template") String template);
}
