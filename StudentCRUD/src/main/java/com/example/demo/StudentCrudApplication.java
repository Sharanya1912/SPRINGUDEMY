package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@SpringBootApplication
public class StudentCrudApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentCrudApplication.class, args);
        System.out.println("STUDENT CRUD RUNNING...");
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student By Id");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:

                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Test Score: ");
                Integer score = sc.nextInt();

                Student student = new Student(name, score);

                studentRepository.save(student);

                System.out.println("Student Added Successfully!");
                break;

            case 2:

                List<Student> students = studentRepository.findAll();

                if (students.isEmpty()) {
                    System.out.println("No Students Found");
                } else {
                    students.forEach(System.out::println);
                }

                break;

            case 3:

                System.out.print("Enter Student Id: ");
                Long id = sc.nextLong();

                Optional<Student> studentById = studentRepository.findById(id);

                if (studentById.isPresent()) {
                    System.out.println(studentById.get());
                } else {
                    System.out.println("Student Not Found");
                }

                break;

            case 4:

                System.out.print("Enter Student Id To Update: ");
                Long updateId = sc.nextLong();

                Optional<Student> existingStudent = studentRepository.findById(updateId);

                if (existingStudent.isPresent()) {

                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Test Score: ");
                    Integer newScore = sc.nextInt();

                    Student s = existingStudent.get();
                    s.setName(newName);
                    s.setTestscore(newScore);

                    studentRepository.save(s);

                    System.out.println("Student Updated Successfully!");
                } else {
                    System.out.println("Student Not Found");
                }

                break;

            case 5:

                System.out.print("Enter Student Id To Delete: ");
                Long deleteId = sc.nextLong();

                if (studentRepository.existsById(deleteId)) {

                    studentRepository.deleteById(deleteId);

                    System.out.println("Student Deleted Successfully!");
                } else {
                    System.out.println("Student Not Found");
                }

                break;

            case 6:

                System.out.println("Exiting Application...");
                sc.close();
                System.exit(0);
                break;

            default:

                System.out.println("Invalid Choice");
            }
        }
    }
}