package com.crew.university.services;

import com.crew.university.dto.DepartmentStats;
import com.crew.university.dto.exceptions.DepartmentNotFoundException;
import com.crew.university.entities.Department;
import com.crew.university.entities.Lector;
import com.crew.university.repositories.DepartmentRepository;
import com.crew.university.repositories.LectorDepartmentRepository;
import com.crew.university.repositories.LectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UniversityServiceTest {

    @InjectMocks
    private UniversityService universityService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private LectorDepartmentRepository lectorDepartmentRepository;

    @Mock
    private LectorRepository lectorRepository;

    @Test
    public void getHeadByNameTest() {
        Department department = new Department();
        Lector head = new Lector();
        head.setFullName("John Doe");
        department.setHead(head);

        when(departmentRepository.getByName("Computer Science")).thenReturn(department);

        StringBuilder result = universityService.getHeadByName("Computer Science");

        assertEquals("Answer: Head of: Computer Science department is John Doe", result.toString());
    }

    @Test
    public void getHeadByNameDepartmentNotFoundTest() {
        when(departmentRepository.getByName("Unknown Department")).thenThrow(new RuntimeException());

        StringBuilder result = universityService.getHeadByName("Unknown Department");

        assertEquals("Wrong name of department", result.toString());
    }

    @Test
    public void getStatisticsTest() {
        Department department = new Department();
        department.setId(1L);
        when(departmentRepository.getByName("Computer Science")).thenReturn(department);
        List<Long> lectorsIds = Arrays.asList(1L, 2L, 3L);

        when(lectorDepartmentRepository.findRelatedToDepartmentLectors(1L)).thenReturn(lectorsIds);
        when(lectorRepository.countAssistants(lectorsIds)).thenReturn(3L);
        when(lectorRepository.countAssociateProfessor(lectorsIds)).thenReturn(2L);
        when(lectorRepository.countProfessors(lectorsIds)).thenReturn(1L);

        DepartmentStats result = universityService.getStatistics("Computer Science");

        assertEquals(3L, result.getAssistantsCount());
        assertEquals(2L, result.getAssociateProfessorsCount());
        assertEquals(1L, result.getProfessorsCount());
    }

    @Test
    public void getStatisticsDepartmentNotFoundTest() {
        when(departmentRepository.getByName("Unknown Department")).thenThrow(new RuntimeException());

        assertThrows(DepartmentNotFoundException.class, () -> universityService.getStatistics("Unknown Department"));
    }

    @Test
    public void getAverageSalaryTest() {
        Department department = new Department();
        department.setId(1L);
        List<Long> lectorsIds = Arrays.asList(1L, 2L, 3L);

        when(departmentRepository.getByName("Computer Science")).thenReturn(department);
        when(lectorDepartmentRepository.findRelatedToDepartmentLectors(1L)).thenReturn(lectorsIds);
        when(lectorRepository.getAverageSalary(lectorsIds)).thenReturn(BigDecimal.valueOf(5000.0));

        StringBuilder result = universityService.getAverageSalary("Computer Science");

        assertEquals("Answer: The average salary of Computer Science is 5000.0", result.toString());
    }

    @Test
    public void getAverageSalaryDepartmentNotFoundTest() {
        when(departmentRepository.getByName("Unknown Department")).thenThrow(new RuntimeException());

        StringBuilder result = universityService.getAverageSalary("Unknown Department");

        assertEquals("Wrong name of department", result.toString());
    }

    @Test
    public void getCountOfEmployeeForDepartmentTest() {
        Department department = new Department();
        department.setId(1L);
        List<Long> lectorsIds = Arrays.asList(1L, 2L, 3L);

        when(departmentRepository.getByName("Computer Science")).thenReturn(department);
        when(lectorDepartmentRepository.findRelatedToDepartmentLectors(1L)).thenReturn(lectorsIds);

        StringBuilder result = universityService.getCountOfEmployeeForDepartment("Computer Science");

        assertEquals("Answer: 3", result.toString());
    }

    @Test
    public void getCountOfEmployeeForDepartmentDepartmentNotFoundTest() {
        when(departmentRepository.getByName("Unknown Department")).thenThrow(new RuntimeException());

        StringBuilder result = universityService.getCountOfEmployeeForDepartment("Unknown Department");

        assertEquals("Wrong name of department", result.toString());
    }

    @Test
    public void globalSearchTest() {
        String[] departmentResults = {"Computer Science", "Mathematics"};
        String[] lectorResults = {"John Doe", "Jane Smith"};

        when(departmentRepository.searchNamesByTemplate("comp")).thenReturn(departmentResults);
        when(lectorRepository.searchFullNamesByTemplate("comp")).thenReturn(lectorResults);

        StringBuilder result = universityService.globalSearch("comp");

        assertEquals("Answer: Computer Science, Mathematics, John Doe, Jane Smith", result.toString().trim());
    }

    @Test
    public void globalSearchNoResultsTest() {
        String template = "nonexistent";

        when(departmentRepository.searchNamesByTemplate(template)).thenThrow(new RuntimeException());

        StringBuilder result = universityService.globalSearch(template);

        assertEquals("Wrong name of department", result.toString().trim());
    }
}