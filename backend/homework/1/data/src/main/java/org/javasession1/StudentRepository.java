package org.javasession1;

import java.util.ArrayList;

public class StudentRepository {
    ArrayList<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    public Student checkId(int id){
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    public boolean addStudent(Student s){
        Student st = checkId(s.getId());
        if(st == null){
            students.add(s);
            return true;
        }
        else{
            return false;
        }
    }

    public Student getStudent(int id) {
        return checkId(id);
    }

    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public boolean setStudent(int id, String name) {
        Student s = checkId(id);
        if(s == null){
            return false;
        }
        else{
            s.setName(name);
            return true;
        }
    }

    public boolean setStudent(int id, int age) {
        Student s = checkId(id);
        if(s == null){
            return false;
        }
        else{
            s.setAge(age);
            return true;
        }
    }

    public boolean setStudent(String grade,int id) {
        Student s = checkId(id);
        if(s == null){
            return false;
        }
        else{
            s.setGrade(grade);
            return true;
        }
    }
}
