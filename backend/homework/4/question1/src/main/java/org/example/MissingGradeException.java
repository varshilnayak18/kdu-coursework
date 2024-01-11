package org.example;

public class MissingGradeException extends Exception{
    private final int studentId ;

    public int getStudentId() {
        return studentId;
    }

    public MissingGradeException(int studentId){
        super("student with id: " + studentId + " is yet to receive a grade for this subject");
        this.studentId = studentId;
    }
}
