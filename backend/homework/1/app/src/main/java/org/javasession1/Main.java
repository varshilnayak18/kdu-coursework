package org.javasession1;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StudentRepository school = new StudentRepository();
        Logging logger = new Logging();

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int choice;
        int id,age;
        String name,grade;
        Student retrievedStudent;

        while(flag){
            System.out.println("\n1. Add new student");
            System.out.println("2. Get student details using id");
            System.out.println("3. Get student details using name");
            System.out.println("4. Update student name");
            System.out.println("5. Update student age");
            System.out.println("6. Update student grade");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter name: ");
                    name = scanner.nextLine();

                    System.out.println("Enter age: ");
                    age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter grade: ");
                    grade = scanner.nextLine();

                    Student newStudent = new Student(id,name,age,grade);
                    if(school.addStudent(newStudent)){
                        System.out.println("Student added successfully");
                        logger.logAddStudent(id);
                    }
                    else{
                        System.out.println("Student with id :" + id + "already exits");
                    }
                    break;

                case 2:
                    System.out.println("Enter id: ");
                    id = scanner.nextInt();
                    retrievedStudent = school.getStudent(id);
                    if(retrievedStudent == null){
                        logger.logGetStudentByIdError(id);
                    }
                    else {
                        logger.logGetStudentById(id);
                        retrievedStudent.printStudent();
                    }
                    break;

                case 3:
                    System.out.println("Enter name: ");
                    name = scanner.nextLine();
                    retrievedStudent = school.getStudent(name);
                    if(retrievedStudent == null){
                        logger.logGetStudentByNameError(name);
                    }
                    else {
                        logger.logGetStudentByName(name);
                        retrievedStudent.printStudent();
                    }
                    break;

                case 4:
                    System.out.println("Enter id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter name to be updated: ");
                    name = scanner.nextLine();
                    if(school.setStudent(id,name)){
                        logger.logSetStudent(id);
                        System.out.println("Details updated successfully");
                    }
                    else{
                        logger.logSetStudentError(id);
                    }
                    break;

                case 5:
                    System.out.println("Enter id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter age to be updated: ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    if(school.setStudent(id,age)){
                        logger.logSetStudent(id);
                        System.out.println("Details updated successfully");
                    }
                    else{
                        logger.logSetStudentError(id);
                    }
                    break;

                case 6:
                    System.out.println("Enter id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter grade to be updated: ");
                    grade = scanner.nextLine();
                    if(school.setStudent(grade,id)){
                        logger.logSetStudent(id);
                        System.out.println("Details updated successfully");
                    }
                    else{
                        logger.logSetStudentError(id);
                    }
                    break;

                case 7:
                    flag = false;
                    break;

                default:
                    System.out.println("Enter valid choice");
            }
        }
    }
}