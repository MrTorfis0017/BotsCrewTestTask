package com.crew.university.dto.exceptions;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String name) {
        super("Department with name: " + name + " not found");
    }
}