package com.crew.university.services;

import com.crew.university.dto.DepartmentStats;
import com.crew.university.dto.exceptions.DepartmentNotFoundException;
import com.crew.university.repositories.DepartmentRepository;
import com.crew.university.repositories.LectorDepartmentRepository;
import com.crew.university.repositories.LectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityService {

    private final DepartmentRepository departmentRepository;

    private final LectorDepartmentRepository lectorDepartmentRepository;

    private final LectorRepository lectorRepository;

    public StringBuilder getHeadByName(String departmentName) {
        try {
            return new StringBuilder().append("Answer: Head of: ").append(departmentName).append(" department is ").append(departmentRepository.getByName(departmentName).getHead().getFullName());
        } catch (Exception e) {
            return new StringBuilder().append("Wrong name of department");
        }
    }

    public DepartmentStats getStatistics(String departmentName) {
        try {
            List<Long> lectorsRelatedToDepartment = lectorDepartmentRepository.findRelatedToDepartmentLectors(departmentRepository.getByName(departmentName).getId());
            return new DepartmentStats(lectorRepository.countAssistants(lectorsRelatedToDepartment),
                    lectorRepository.countAssociateProfessor(lectorsRelatedToDepartment), lectorRepository.countProfessors(lectorsRelatedToDepartment));
        } catch (Exception e) {
            throw new DepartmentNotFoundException(departmentName);//I added exception just for variety
        }
    }

    public StringBuilder getAverageSalary(String departmentName) {
        try {
            List<Long> lectorsRelatedToDepartment = lectorDepartmentRepository.findRelatedToDepartmentLectors(departmentRepository.getByName(departmentName).getId());
            return new StringBuilder().append("Answer: The average salary of ").append(departmentName).append(" is ").append(lectorRepository.getAverageSalary(lectorsRelatedToDepartment));
        } catch (Exception e) {
            return new StringBuilder().append("Wrong name of department");
        }
    }

    public StringBuilder getCountOfEmployeeForDepartment(String departmentName) {
        try {
            return new StringBuilder().append("Answer: ").append(lectorDepartmentRepository.findRelatedToDepartmentLectors(departmentRepository.getByName(departmentName).getId()).size());
        } catch (Exception e) {
            return new StringBuilder().append("Wrong name of department");
        }
    }

    public StringBuilder globalSearch(String template) {
        try {
            StringBuilder stringBuilder = new StringBuilder().append("Answer: ");
            appendResult(stringBuilder, departmentRepository.searchNamesByTemplate(template));
            appendResult(stringBuilder, lectorRepository.searchFullNamesByTemplate(template));
            stringBuilder.setCharAt(stringBuilder.length() - 2, ' ');
            return stringBuilder;
        } catch (Exception e) {
            return new StringBuilder().append("Wrong name of department");
        }
    }

    private void appendResult(StringBuilder stringBuilder, String[] strings) {
        for (String item : strings) {
            stringBuilder.append(item).append(", ");
        }
    }
}