package org.example;

public class StudentUtil {

    private StudentUtil(){

    }

    /**
     * calculates GPA of each student from the list of students
     * @param studentIdList list containing id of students
     * @param studentsGrades list of grades of each student from the list of students
     * @return double[] list containing grades of each student
     */
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) throws MissingGradeException {
        if(studentIdList.length != studentsGrades.length){
            throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
        }
        double[] studentGPA = new double[studentIdList.length];
        double sum = 0;
        int subjects = 0;
        char grade;
        for(int i = 0; i < studentIdList.length; i++){
            sum = 0;
            subjects = studentsGrades[i].length;
            for(int j = 0; j < subjects; j++){
                grade = studentsGrades[i][j];
                if(grade == 'A'){
                    sum += 4;
                }
                else if(grade == 'B'){
                    sum += 3;
                }
                else if(grade == 'C'){
                    sum += 2;
                }
                else if(grade == ' ') {
                    throw new MissingGradeException(studentIdList[i]);
                }
            }
            studentGPA[i] = sum/subjects;
        }
        return studentGPA;
    }

    /**
      * finds id of students whose GPA falls between lower and higher by taking help of calculateGPA method
     * @param lower lower limit of GPA
     * @param higher upper limit of GPA
     * @param studentIdList list containing id of students
     * @param studentsGrades list of grades of each student from the list of students
     * @return int[] list containing ids of students having GPA in range
     */
    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) throws InvalidDataException {
        if(lower>higher || lower<0 || higher<0){
            return new int[0];
        }
        double[] studentGPA = new double[0];
        try {
            studentGPA = calculateGPA(studentIdList, studentsGrades);
        }
        catch (MissingGradeException e){
            throw new InvalidDataException(e.getMessage());
        }
        int count = 0;
        for (int i = 0; i < studentIdList.length; i++) {
            double gpa = studentGPA[i];
            if (gpa <= higher && gpa >= lower) {
                count++;
            }
        }
        int[] studentWithinRangeOfGPA = new int[count];
        int j = 0;
        for (int i = 0; i < studentIdList.length; i++) {
            double gpa = studentGPA[i];
            if (gpa <= higher && gpa >= lower) {
                studentWithinRangeOfGPA[j++] = studentIdList[i];
            }
        }
        return studentWithinRangeOfGPA;
    }
}