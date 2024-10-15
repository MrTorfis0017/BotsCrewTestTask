package com.crew.university;


import com.crew.university.services.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class MainApplication implements CommandLineRunner {

    private final UniversityService departmentService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose a command: (using number of needed command)");
            System.out.println("1. Who is head of department {department_name}");
            System.out.println("2. Show {department_name} statistics");
            System.out.println("3. Show the average salary for the department {department_name}");
            System.out.println("4. Show count of employee for {department_name}");
            System.out.println("5. Global search by {template}");
            System.out.println("6. Exit");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Please enter the department name:");
                    String departmentNameForHead = scanner.nextLine();
                    System.out.println(departmentService.getHeadByName(departmentNameForHead.trim()));
                    break;

                case "2":
                    System.out.println("Please enter the department name:");
                    String departmentNameForStats = scanner.nextLine();
                    System.out.println(departmentService.getStatistics(departmentNameForStats.trim()));
                    break;

                case "3":
                    System.out.println("Please enter the department name:");
                    String departmentNameForSalary = scanner.nextLine();
                    System.out.println(departmentService.getAverageSalary(departmentNameForSalary.trim()));
                    break;

                case "4":
                    System.out.println("Please enter the department name:");
                    String departmentNameForEmployeeCount = scanner.nextLine();
                    System.out.println(departmentService.getCountOfEmployeeForDepartment(departmentNameForEmployeeCount.trim()));
                    break;

                case "5":
                    System.out.println("Please enter the search template:");
                    String searchTemplate = scanner.nextLine();
                    System.out.println(departmentService.globalSearch(searchTemplate.trim()));
                    break;

                case "6":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid command. Please choose a valid option.");
                    break;
            }
        }
    }
}