package com.crew.university.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentStats {

    private Long assistantsCount;

    private Long associateProfessorsCount;

    private Long professorsCount;

    @Override
    public String toString() {
        return "Answer: assistans - " + assistantsCount + "\n" +
                "associate professors - " + associateProfessorsCount + "\n" +
                "professors - " + professorsCount;
    }
}
